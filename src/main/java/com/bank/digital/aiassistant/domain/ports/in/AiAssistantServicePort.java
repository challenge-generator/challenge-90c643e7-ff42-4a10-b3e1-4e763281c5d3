package com.bank.digital.aiassistant.domain.ports.in;



import com.bank.digital.aiassistant.domain.model.AssistantStatus;
import com.bank.digital.aiassistant.domain.model.SuggestionStatus;
import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface AiAssistantServicePort {
    CompletableFuture<CodeSuggestion> generateCodeSuggestion(
        UUID aiAssistantId,
        String context,
        String prompt
    );

    CompletableFuture<Boolean> evaluateSuggestionQuality(
        UUID suggestionId,
        int qualityScore
    );

    CompletableFuture<List<CodeSuggestion>> getSuggestionsByAssistant(
        UUID aiAssistantId,
        CodeSuggestion.SuggestionStatus status,
        int limit
    );

    CompletableFuture<AiAssistant> registerAiAssistant(
        AiAssistant aiAssistant
    );

    CompletableFuture<AiAssistant> updateAssistantStatus(
        UUID aiAssistantId,
        AiAssistant.AssistantStatus newStatus
    );

    CompletableFuture<List<AiAssistant>> listActiveAssistants();

    CompletableFuture<CodeSuggestion> updateSuggestionStatus(
        UUID suggestionId,
        CodeSuggestion.SuggestionStatus newStatus
    );
}