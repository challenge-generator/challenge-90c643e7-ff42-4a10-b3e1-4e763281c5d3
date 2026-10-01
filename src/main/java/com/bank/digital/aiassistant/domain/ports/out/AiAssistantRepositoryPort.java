package com.bank.digital.aiassistant.domain.ports.out;





import com.bank.digital.aiassistant.domain.model.AssistantStatus;
import com.bank.digital.aiassistant.domain.model.Capability;
import com.bank.digital.aiassistant.domain.model.SuggestionStatus;
import com.bank.digital.aiassistant.domain.model.Provider;
import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface AiAssistantRepositoryPort {

    CompletableFuture<AiAssistant> saveAssistant(AiAssistant assistant);

    CompletableFuture<Optional<AiAssistant>> findById(UUID assistantId);

    CompletableFuture<List<AiAssistant>> findAllActive();

    CompletableFuture<List<AiAssistant>> findByProvider(AiAssistant.Provider provider);

    CompletableFuture<AiAssistant> updateAssistant(AiAssistant assistant);

    CompletableFuture<Void> deleteAssistant(UUID assistantId);

    CompletableFuture<CodeSuggestion> saveSuggestion(CodeSuggestion suggestion);

    CompletableFuture<List<CodeSuggestion>> findSuggestionsByAssistantId(UUID assistantId);

    CompletableFuture<List<CodeSuggestion>> findSuggestionsByStatus(CodeSuggestion.SuggestionStatus status);

    CompletableFuture<CodeSuggestion> updateSuggestion(CodeSuggestion suggestion);

    CompletableFuture<Void> deleteSuggestion(UUID suggestionId);

    CompletableFuture<Long> countSuggestionsByAssistantAndStatus(UUID assistantId, CodeSuggestion.SuggestionStatus status);

    CompletableFuture<Double> calculateAcceptanceRate(UUID assistantId);

    CompletableFuture<List<AiAssistant>> findAssistantsByCapability(AiAssistant.Capability capability);

    CompletableFuture<Boolean> existsByNameAndProvider(String name, AiAssistant.Provider provider);

    CompletableFuture<Void> batchSaveSuggestions(List<CodeSuggestion> suggestions);
}// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCase.java ===
package com.bank.digital.aiassistant.application.usecases;

import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;
import com.bank.digital.aiassistant.domain.ports.in.AiAssistantServicePort;
import com.bank.digital.aiassistant.domain.ports.out.AiAssistantRepositoryPort;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class EvaluateAiAssistantUseCase {

    private final AiAssistantRepositoryPort repositoryPort;
    private final AiAssistantServicePort servicePort;

    public EvaluateAiAssistantUseCase(AiAssistantRepositoryPort repositoryPort,
                                       AiAssistantServicePort servicePort) {
        this.repositoryPort = repositoryPort;
        this.servicePort = servicePort;
    }

    public CompletableFuture<EvaluationResult> evaluateAssistant(UUID assistantId) {
        return repositoryPort.findById(assistantId)
            .thenCompose(optAssistant -> {
                if (optAssistant.isEmpty()) {
                    return CompletableFuture.completedFuture(
                        EvaluationResult.failure("Asistente no encontrado con ID: " + assistantId));
                }

                AiAssistant assistant = optAssistant.get();
                return performComprehensiveEvaluation(assistant);
            });
    }

    private CompletableFuture<EvaluationResult> performComprehensiveEvaluation(AiAssistant assistant) {
        CompletableFuture<List<CodeSuggestion>> suggestionsFuture =
            repositoryPort.findSuggestionsByAssistantId(assistant.id());

        CompletableFuture<Long> acceptedCountFuture =
            repositoryPort.countSuggestionsByAssistantAndStatus(
                assistant.id(), CodeSuggestion.SuggestionStatus.ACCEPTED);

        CompletableFuture<Long> rejectedCountFuture =
            repositoryPort.countSuggestionsByAssistantAndStatus(
                assistant.id(), CodeSuggestion.SuggestionStatus.REJECTED);

        CompletableFuture<Long> pendingCountFuture =
            repositoryPort.countSuggestionsByAssistantAndStatus(
                assistant.id(), CodeSuggestion.SuggestionStatus.PENDING);

        return CompletableFuture.allOf(
                suggestionsFuture, acceptedCountFuture, rejectedCountFuture, pendingCountFuture)
            .thenApply(v -> {
                List<CodeSuggestion> allSuggestions = suggestionsFuture.join();
                long accepted = acceptedCountFuture.join();
                long rejected = rejectedCountFuture.join();
                long pending = pendingCountFuture.join();
                long total = accepted + rejected + pending;

                double acceptanceRate = total > 0 ? (double) accepted / total * 100 : 0.0;
                double rejectionRate = total > 0 ? (double) rejected / total * 100 : 0.0;

                List<String> strengths = new ArrayList<>();
                List<String> weaknesses = new ArrayList<>();
                List<String> recommendations = new ArrayList<>();

                if (acceptanceRate >= 70.0) {
                    strengths.add("Alta tasa de aceptación (" + String.format("%.2f", acceptanceRate) + "%)");
                } else if (acceptanceRate < 50.0) {
                    weaknesses.add("Baja tasa de aceptación (" + String.format("%.2f", acceptanceRate) + "%)");
                    recommendations.add("Revisar la calidad de las sugerencias generadas");
                    recommendations.add("Evaluar el contexto de código proporcionado al asistente");
                }

                if (assistant.supportsCapability(AiAssistant.Capability.CODE_GENERATION)) {
                    strengths.add("Soporta generación de código");
                }

                if (assistant.supportsCapability(AiAssistant.Capability.CODE_REFACTORING)) {
                    strengths.add("Soporta refactorización de código");
                }

                if (assistant.supportsCapability(AiAssistant.Capability.SECURITY_ANALYSIS)) {
                    strengths.add("Incluye análisis de seguridad");
                }

                if (assistant.isDeprecated()) {
                    weaknesses.add("Asistente marcado como deprecado");
                    recommendations.add("Considerar migración a una versión actualizada");
                }

                if (!assistant.isActive()) {
                    weaknesses.add("Asistente actualmente inactivo");
                    recommendations.add("Activar el asistente para comenzar a evaluar");
                }

                List<CodeSuggestion> highRelevanceSuggestions = allSuggestions.stream()
                    .filter(CodeSuggestion::isHighRelevance)
                    .collect(Collectors.toList());

                if (!highRelevanceSuggestions.isEmpty()) {
                    long highRelevanceAccepted = highRelevanceSuggestions.stream()
                        .filter(CodeSuggestion::isAccepted)
                        .count();
                    double highRelevanceRate = (double) highRelevanceAccepted / highRelevanceSuggestions.size() * 100;
                    recommendations.add("Sugerencias de alta relevancia: " + highRelevanceAccepted + "/" +
                        highRelevanceSuggestions.size() + " aceptadas (" + String.format("%.2f", highRelevanceRate) + "%)");
                }

                return EvaluationResult.success(
                    assistant,
                    total,
                    accepted,
                    rejected,
                    pending,
                    acceptanceRate,
                    rejectionRate,
                    strengths,
                    weaknesses,
                    recommendations
                );
            });
    }

    public CompletableFuture<List<EvaluationResult>> evaluateAllActiveAssistants() {
        return repositoryPort.findAllActive()
            .thenApply(assistants -> {
                List<CompletableFuture<EvaluationResult>> evaluationFutures = assistants.stream()
                    .map(this::performComprehensiveEvaluation)
                    .collect(Collectors.toList());

                return evaluationFutures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());
            });
    }

    public CompletableFuture<EvaluationResult> compareAssistants(List<UUID> assistantIds) {
        List<CompletableFuture<EvaluationResult>> evaluations = assistantIds.stream()
            .map(this::evaluateAssistant)
            .collect(Collectors.toList());

        return CompletableFuture.allOf(evaluations.toArray(new CompletableFuture[0]))
            .thenApply(v -> {
                List<EvaluationResult> results = evaluations.stream()
                    .map(CompletableFuture::join)
                    .filter(EvaluationResult::isSuccess)
                    .collect(Collectors.toList());

                if (results.isEmpty()) {
                    return EvaluationResult.failure("No se pudieron evaluar los asistentes especificados");
                }

                EvaluationResult bestPerforming = results.stream()
                    .max((r1, r2) -> Double.compare(r1.acceptanceRate(), r2.acceptanceRate()))
                    .orElse(results.get(0));

                return EvaluationResult.comparison(results, bestPerforming.assistantId());
            });
    }

    public record EvaluationResult(
        boolean success,
        String errorMessage,
        AiAssistant assistant,
        long totalSuggestions,
        long acceptedSuggestions,
        long rejectedSuggestions,
        long pendingSuggestions,
        double acceptanceRate,
        double rejectionRate,
        List<String> strengths,
        List<String> weaknesses,
        List<String> recommendations,
        UUID assistantId,
        boolean isComparison,
        List<EvaluationResult> comparedResults
    ) {
        public static EvaluationResult success(
                AiAssistant assistant,
                long total,
                long accepted,
                long rejected,
                long pending,
                double acceptanceRate,
                double rejectionRate,
                List<String> strengths,
                List<String> weaknesses,
                List<String> recommendations) {
            return new EvaluationResult(
                true, null, assistant, total, accepted, rejected, pending,
                acceptanceRate, rejectionRate, strengths, weaknesses, recommendations,
                assistant.id(), false, null
            );
        }

        public static EvaluationResult failure(String message) {
            return new EvaluationResult(
                false, message, null, 0, 0, 0, 0,
                0.0, 0.0, List.of(), List.of(), List.of(),
                null, false, null
            );
        }

        public static EvaluationResult comparison(List<EvaluationResult> results, UUID bestAssistantId) {
            return new EvaluationResult(
                true, null, null, 0, 0, 0, 0,
                0.0, 0.0, List.of(), List.of(), List.of(),
                bestAssistantId, true, results
            );
        }
    }
}// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/application/usecases/IntegrateAiAssistantUseCase.java ===
package com.bank.digital.aiassistant.application.usecases;

import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;
import com.bank.digital.aiassistant.domain.ports.in.AiAssistantServicePort;
import com.bank.digital.aiassistant.domain.ports.out.AiAssistantRepositoryPort;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class IntegrateAiAssistantUseCase {

    private final AiAssistantRepositoryPort repositoryPort;
    private final AiAssistantServicePort servicePort;

    public IntegrateAiAssistantUseCase(AiAssistantRepositoryPort repositoryPort,
                                        AiAssistantServicePort servicePort) {
        this.repositoryPort = repositoryPort;
        this.servicePort = servicePort;
    }

    public CompletableFuture<IntegrationResult> integrateAssistant(IntegrationRequest request) {
        return validateIntegrationRequest(request)
            .thenCompose(validationError -> {
                if (validationError != null) {
                    return CompletableFuture.completedFuture(
                        IntegrationResult.failure(validationError));
                }
                return performIntegration(request);
            });
    }

    private CompletableFuture<String> validateIntegrationRequest(IntegrationRequest request) {
        if (request.name() == null || request.name().isBlank()) {
            return CompletableFuture.completedFuture("El nombre del asistente es requerido");
        }

        if (request.provider() == null) {
            return CompletableFuture.completedFuture("El proveedor del asistente es requerido");
        }

        if (request.apiKey() == null || request.apiKey().isBlank()) {
            return CompletableFuture.completedFuture("La API key es requerida para la integración");
        }

        return repositoryPort.existsByNameAndProvider(request.name(), request.provider())
            .thenApply(exists -> exists ? "Ya existe un asistente con el nombre '" + request.name() +
                "' para el proveedor " + request.provider() : null);
    }

    private CompletableFuture<IntegrationResult> performIntegration(IntegrationRequest request) {
        Set<AiAssistant.Capability> capabilities = determineCapabilities(request);

        AiAssistant newAssistant = new AiAssistant(
            UUID.randomUUID(),
            request.name(),
            request.provider(),
            AiAssistant.AssistantStatus.ACTIVE,
            capabilities,
            request.endpoint(),
            request.apiKey(),
            request.configuration(),
            Instant.now(),
            Instant.now(),
            false
        );

        return repositoryPort.saveAssistant(newAssistant)
            .thenApply(savedAssistant -> {
                List<String> integrationSteps = new ArrayList<>();
                integrationSteps.add("Asistente registrado en el sistema");
                integrationSteps.add("Credenciales configuradas de forma segura");
                integrationSteps.add("Capacidades habilitadas: " + capabilities.size());

                if (capabilities.contains(AiAssistant.Capability.CODE_GENERATION)) {
                    integrationSteps.add("Integración de generación de código completada");
                }
                if (capabilities.contains(AiAssistant.Capability.CODE_REFACTORING)) {
                    integrationSteps.add("Integración de refactorización completada");
                }
                if (capabilities.contains(AiAssistant.Capability.SECURITY_ANALYSIS)) {
                    integrationSteps.add("Integración de análisis de seguridad completada");
                }
                if (capabilities.contains(AiAssistant.Capability.TEST_GENERATION)) {
                    integrationSteps.add("Integración de generación de pruebas completada");
                }
                if (capabilities.contains(AiAssistant.Capability.DOCUMENTATION)) {
                    integrationSteps.add("Integración de documentación completada");
                }

                return IntegrationResult.success(savedAssistant, integrationSteps);
            })
            .exceptionally(ex -> IntegrationResult.failure(
                "Error durante la integración: " + ex.getMessage()));
    }

    private Set<AiAssistant.Capability> determineCapabilities(IntegrationRequest request) {
        Set<AiAssistant.Capability> capabilities = request.capabilities();

        if (capabilities == null || capabilities.isEmpty()) {
            capabilities = Set.of(AiAssistant.Capability.CODE_GENERATION);
        }

        return capabilities;
    }

    public CompletableFuture<IntegrationResult> updateIntegration(UUID assistantId,
                                                                    IntegrationUpdateRequest updateRequest) {
        return repositoryPort.findById(assistantId)
            .thenCompose(optAssistant -> {
                if (optAssistant.isEmpty()) {
                    return CompletableFuture.completedFuture(
                        IntegrationResult.failure("Asistente no encontrado"));
                }

                AiAssistant existing = optAssistant.get();
                AiAssistant updated = new AiAssistant(
                    existing.id(),
                    updateRequest.name() != null ? updateRequest.name() : existing.name(),
                    existing.provider(),
                    updateRequest.status() != null ? updateRequest.status() : existing.status(),
                    updateRequest.capabilities() != null ? updateRequest.capabilities() : existing.capabilities(),
                    updateRequest.endpoint() != null ? updateRequest.endpoint() : existing.endpoint(),
                    updateRequest.apiKey() != null ? updateRequest.apiKey() : existing.apiKey(),
                    updateRequest.configuration() != null ? updateRequest.configuration() : existing.configuration(),
                    existing.createdAt(),
                    Instant.now(),
                    existing.deprecated()
                );

                return repositoryPort.updateAssistant(updated)
                    .thenApply(saved -> IntegrationResult.success(saved,
                        List.of("Configuración actualizada", "Estado sincronizado")));
            });
    }

    public CompletableFuture<IntegrationResult> deactivateAssistant(UUID assistantId) {
        return repositoryPort.findById(assistantId)
            .thenCompose(optAssistant -> {
                if (optAssistant.isEmpty()) {
                    return CompletableFuture.completedFuture(
                        IntegrationResult.failure("Asistente no encontrado"));
                }

                AiAssistant existing = optAssistant.get();
                AiAssistant deactivated = new AiAssistant(
                    existing.id(),
                    existing.name(),
                    existing.provider(),
                    AiAssistant.AssistantStatus.INACTIVE,
                    existing.capabilities(),
                    existing.endpoint(),
                    existing.apiKey(),
                    existing.configuration(),
                    existing.createdAt(),
                    Instant.now(),
                    existing.deprecated()
                );

                return repositoryPort.updateAssistant(deactivated)
                    .thenApply(saved -> IntegrationResult.success(saved,
                        List.of("Asistente desactivado", "Integración deshabilitada")));
            });
    }

    public CompletableFuture<List<IntegrationResult>> batchIntegrate(List<IntegrationRequest> requests) {
        List<CompletableFuture<IntegrationResult>> integrationFutures = requests.stream()
            .map(this::integrateAssistant)
            .collect(Collectors.toList());

        return CompletableFuture.allOf(integrationFutures.toArray(new CompletableFuture[0]))
            .thenApply(v -> integrationFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
    }

    public record IntegrationRequest(
        String name,
        AiAssistant.Provider provider,
        String apiKey,
        String endpoint,
        java.util.Map<String, Object> configuration,
        Set<AiAssistant.Capability> capabilities
    ) {}

    public record IntegrationUpdateRequest(
        String name,
        AiAssistant.AssistantStatus status,
        String endpoint,
        String apiKey,
        java.util.Map<String, Object> configuration,
        Set<AiAssistant.Capability> capabilities
    ) {}

    public record IntegrationResult(
        boolean success,
        String errorMessage,
        AiAssistant assistant,
        List<String> integrationSteps
    ) {
        public static IntegrationResult success(AiAssistant assistant, List<String> steps) {
            return new IntegrationResult(true, null, assistant, steps);
        }

        public static IntegrationResult failure(String message) {
            return new IntegrationResult(false, message, null, List.of());
        }
    }
}