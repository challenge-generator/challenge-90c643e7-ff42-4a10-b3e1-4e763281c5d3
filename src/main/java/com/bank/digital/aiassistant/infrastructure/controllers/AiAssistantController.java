package com.bank.digital.aiassistant.infrastructure.controllers;





import com.bank.digital.aiassistant.domain.model.SuggestionStatus;
import com.bank.digital.aiassistant.domain.model.Capability;
import com.bank.digital.aiassistant.domain.model.Provider;
import com.bank.digital.aiassistant.domain.model.AssistantStatus;
import com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase;
import com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase;
import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;
import com.bank.digital.aiassistant.domain.ports.in.AiAssistantServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/ai-assistants")
@RequiredArgsConstructor
@Tag(name = "AI Assistants", description = "Endpoints para gestión y evaluación de asistentes de IA")
public class AiAssistantController {

    private final AiAssistantServicePort aiAssistantServicePort;
    private final EvaluateAiAssistantUseCase evaluateAiAssistantUseCase;
    private final IntegrateAiAssistantUseCase integrateAiAssistantUseCase;

    @PostMapping
    @Operation(summary = "Registrar nuevo asistente de IA", description = "Registra un nuevo asistente de IA en el sistema")
    public CompletableFuture<ResponseEntity<AiAssistantResponse>> registerAiAssistant(
            @Valid @RequestBody RegisterAssistantRequest request) {
        return aiAssistantServicePort.registerAiAssistant(
                new AiAssistant(
                        null,
                        request.provider(),
                        request.name(),
                        request.version(),
                        request.capabilities(),
                        AiAssistant.AssistantStatus.ACTIVE,
                        request.config(),
                        null
                )
        ).thenApply(assistant -> ResponseEntity.status(HttpStatus.CREATED)
                .body(AiAssistantResponse.fromDomain(assistant)));
    }

    @GetMapping
    @Operation(summary = "Listar asistentes activos", description = "Retorna todos los asistentes de IA activos en el sistema")
    public CompletableFuture<ResponseEntity<List<AiAssistantResponse>>> listActiveAssistants() {
        return aiAssistantServicePort.listActiveAssistants()
                .thenApply(assistants -> ResponseEntity.ok(
                        assistants.stream()
                                .map(AiAssistantResponse::fromDomain)
                                .toList()
                ));
    }

    @PutMapping("/{assistantId}/status")
    @Operation(summary = "Actualizar estado del asistente", description = "Cambia el estado de un asistente de IA")
    public CompletableFuture<ResponseEntity<AiAssistantResponse>> updateAssistantStatus(
            @PathVariable String assistantId,
            @Valid @RequestBody UpdateStatusRequest request) {
        return aiAssistantServicePort.updateAssistantStatus(
                        UUID.fromString(assistantId),
                        request.status()
                ).thenApply(assistant -> ResponseEntity.ok(
                        AiAssistantResponse.fromDomain(assistant)
                ));
    }

    @PostMapping("/{assistantId}/suggestions")
    @Operation(summary = "Generar sugerencia de código", description = "Solicita una sugerencia de código al asistente de IA")
    public CompletableFuture<ResponseEntity<CodeSuggestionResponse>> generateCodeSuggestion(
            @PathVariable String assistantId,
            @Valid @RequestBody GenerateSuggestionRequest request) {
        return aiAssistantServicePort.generateCodeSuggestion(
                        UUID.fromString(assistantId),
                        request.context(),
                        request.programmingLanguage()
                ).thenApply(suggestion -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(CodeSuggestionResponse.fromDomain(suggestion)));
    }

    @GetMapping("/{assistantId}/suggestions")
    @Operation(summary = "Obtener sugerencias del asistente", description = "Recupera las sugerencias generadas por un asistente específico")
    public CompletableFuture<ResponseEntity<List<CodeSuggestionResponse>>> getSuggestionsByAssistant(
            @PathVariable String assistantId) {
        return aiAssistantServicePort.getSuggestionsByAssistant(UUID.fromString(assistantId))
                .thenApply(suggestions -> ResponseEntity.ok(
                        suggestions.stream()
                                .map(CodeSuggestionResponse::fromDomain)
                                .toList()
                ));
    }

    @PutMapping("/suggestions/{suggestionId}/status")
    @Operation(summary = "Actualizar estado de sugerencia", description = "Marca una sugerencia como aceptada o rechazada")
    public CompletableFuture<ResponseEntity<CodeSuggestionResponse>> updateSuggestionStatus(
            @PathVariable String suggestionId,
            @Valid @RequestBody UpdateSuggestionStatusRequest request) {
        return aiAssistantServicePort.updateSuggestionStatus(
                        UUID.fromString(suggestionId),
                        request.status()
                ).thenApply(suggestion -> ResponseEntity.ok(
                        CodeSuggestionResponse.fromDomain(suggestion)
                ));
    }

    @PostMapping("/suggestions/{suggestionId}/evaluate")
    @Operation(summary = "Evaluar calidad de sugerencia", description = "Evalúa la calidad de una sugerencia de código generada")
    public CompletableFuture<ResponseEntity<EvaluationResponse>> evaluateSuggestionQuality(
            @PathVariable String suggestionId) {
        return evaluateAiAssistantUseCase.execute(UUID.fromString(suggestionId))
                .thenApply(result -> ResponseEntity.ok(
                        new EvaluationResponse(result.evaluationId(), result.score(), result.feedback())
                ));
    }

    @PostMapping("/integrate")
    @Operation(summary = "Integrar asistente externo", description = "Inicia el proceso de integración de un asistente de IA externo")
    public CompletableFuture<ResponseEntity<IntegrationResponse>> integrateAssistant(
            @Valid @RequestBody IntegrateAssistantRequest request) {
        return integrateAiAssistantUseCase.execute(request.provider(), request.credentials())
                .thenApply(result -> ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body(new IntegrationResponse(result.assistantId(), result.status(), result.message())));
    }

    public record RegisterAssistantRequest(
            @NotNull(message = "El proveedor es obligatorio")
            AiAssistant.Provider provider,
            @NotBlank(message = "El nombre es obligatorio")
            String name,
            @NotBlank(message = "La versión es obligatoria")
            String version,
            @NotNull(message = "Las capacidades son obligatorias")
            List<AiAssistant.Capability> capabilities,
            Map<String, Object> config
    ) {}

    public record UpdateStatusRequest(
            @NotNull(message = "El estado es obligatorio")
            AiAssistant.AssistantStatus status
    ) {}

    public record GenerateSuggestionRequest(
            @NotBlank(message = "El contexto es obligatorio")
            String context,
            @NotBlank(message = "El lenguaje de programación es obligatorio")
            String programmingLanguage
    ) {}

    public record UpdateSuggestionStatusRequest(
            @NotNull(message = "El estado es obligatorio")
            CodeSuggestion.SuggestionStatus status
    ) {}

    public record IntegrateAssistantRequest(
            @NotNull(message = "El proveedor es obligatorio")
            AiAssistant.Provider provider,
            @NotBlank(message = "Las credenciales son obligatorias")
            String credentials
    ) {}

    public record AiAssistantResponse(
            String id,
            String provider,
            String name,
            String version,
            List<String> capabilities,
            String status,
            Map<String, Object> config,
            String createdAt
    ) {
        public static AiAssistantResponse fromDomain(AiAssistant assistant) {
            return new AiAssistantResponse(
                    assistant.id() != null ? assistant.id().toString() : null,
                    assistant.provider().name(),
                    assistant.name(),
                    assistant.version(),
                    assistant.capabilities().stream().map(AiAssistant.Capability::name).toList(),
                    assistant.status().name(),
                    assistant.config(),
                    assistant.createdAt() != null ? assistant.createdAt().toString() : null
            );
        }
    }

    public record CodeSuggestionResponse(
            String id,
            String assistantId,
            String context,
            String suggestedCode,
            String programmingLanguage,
            String status,
            boolean highRelevance,
            String createdAt
    ) {
        public static CodeSuggestionResponse fromDomain(CodeSuggestion suggestion) {
            return new CodeSuggestionResponse(
                    suggestion.id() != null ? suggestion.id().toString() : null,
                    suggestion.assistantId() != null ? suggestion.assistantId().toString() : null,
                    suggestion.context(),
                    suggestion.suggestedCode(),
                    suggestion.programmingLanguage(),
                    suggestion.status().name(),
                    suggestion.isHighRelevance(),
                    suggestion.createdAt() != null ? suggestion.createdAt().toString() : null
            );
        }
    }

    public record EvaluationResponse(UUID evaluationId, Double score, String feedback) {}

    public record IntegrationResponse(UUID assistantId, String status, String message) {}
}