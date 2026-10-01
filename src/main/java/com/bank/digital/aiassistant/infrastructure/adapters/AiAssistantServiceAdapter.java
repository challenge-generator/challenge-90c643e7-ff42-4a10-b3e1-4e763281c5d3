package com.bank.digital.aiassistant.infrastructure.adapters;





import com.bank.digital.aiassistant.domain.model.SuggestionStatus;
import com.bank.digital.aiassistant.domain.model.AssistantStatus;
import com.bank.digital.aiassistant.domain.model.Capability;
import com.bank.digital.aiassistant.domain.model.Provider;
import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;
import com.bank.digital.aiassistant.domain.ports.in.AiAssistantServicePort;
import com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase;
import com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AiAssistantServiceAdapter implements AiAssistantServicePort {

    private static final Logger logger = LoggerFactory.getLogger(AiAssistantServiceAdapter.class);

    private final EvaluateAiAssistantUseCase evaluateUseCase;
    private final IntegrateAiAssistantUseCase integrateUseCase;

    public AiAssistantServiceAdapter(
            EvaluateAiAssistantUseCase evaluateUseCase,
            IntegrateAiAssistantUseCase integrateUseCase) {
        this.evaluateUseCase = evaluateUseCase;
        this.integrateUseCase = integrateUseCase;
    }

    @Override
    public CompletableFuture<CodeSuggestion> generateCodeSuggestion(
            String assistantId, String context, String programmingLanguage) {
        logger.info("Generando sugerencia de código con asistente: {}, lenguaje: {}",
                   assistantId, programmingLanguage);
        return integrateUseCase.generateSuggestion(assistantId, context, programmingLanguage);
    }

    @Override
    public CompletableFuture<Boolean> evaluateSuggestionQuality(String suggestionId) {
        logger.info("Evaluando calidad de sugerencia: {}", suggestionId);
        return evaluateUseCase.evaluateQuality(suggestionId)
            .thenApply(result -> result != null && result);
    }

    @Override
    public CompletableFuture<List<CodeSuggestion>> getSuggestionsByAssistant(String assistantId) {
        logger.info("Obteniendo sugerencias para asistente: {}", assistantId);
        return integrateUseCase.getSuggestionsByAssistant(assistantId);
    }

    @Override
    public CompletableFuture<AiAssistant> registerAiAssistant(
            String name, AiAssistant.Provider provider, List<AiAssistant.Capability> capabilities) {
        logger.info("Registrando nuevo asistente de IA: {}, proveedor: {}", name, provider);
        return integrateUseCase.registerAssistant(name, provider, capabilities);
    }

    @Override
    public CompletableFuture<AiAssistant> updateAssistantStatus(
            String assistantId, AiAssistant.AssistantStatus newStatus) {
        logger.info("Actualizando estado del asistente: {} a {}", assistantId, newStatus);
        return integrateUseCase.updateAssistantStatus(assistantId, newStatus);
    }

    @Override
    public CompletableFuture<List<AiAssistant>> listActiveAssistants() {
        logger.info("Listando asistentes activos");
        return integrateUseCase.listActiveAssistants();
    }

    @Override
    public CompletableFuture<CodeSuggestion> updateSuggestionStatus(
            String suggestionId, CodeSuggestion.SuggestionStatus newStatus) {
        logger.info("Actualizando estado de sugerencia: {} a {}", suggestionId, newStatus);
        return evaluateUseCase.updateSuggestionStatus(suggestionId, newStatus);
    }

    public CompletableFuture<CodeSuggestion> acceptSuggestion(String suggestionId) {
        logger.info("Aceptando sugerencia: {}", suggestionId);
        return updateSuggestionStatus(suggestionId, CodeSuggestion.SuggestionStatus.ACCEPTED);
    }

    public CompletableFuture<CodeSuggestion> rejectSuggestion(String suggestionId) {
        logger.info("Rechazando sugerencia: {}", suggestionId);
        return updateSuggestionStatus(suggestionId, CodeSuggestion.SuggestionStatus.REJECTED);
    }

    public CompletableFuture<Double> calculateAssistantEffectiveness(String assistantId) {
        logger.info("Calculando efectividad del asistente: {}", assistantId);
        return evaluateUseCase.calculateEffectiveness(assistantId);
    }
}