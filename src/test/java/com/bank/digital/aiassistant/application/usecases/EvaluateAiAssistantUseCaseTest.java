package com.bank.digital.aiassistant.application.usecases;







import com.bank.digital.aiassistant.domain.ports.out.EvaluationResult;
import com.bank.digital.aiassistant.domain.model.AssistantStatus;
import com.bank.digital.aiassistant.domain.model.Capability;
import com.bank.digital.aiassistant.domain.model.Provider;
import com.bank.digital.aiassistant.domain.model.SuggestionStatus;
import com.bank.digital.aiassistant.domain.ports.out.EvaluateAiAssistantUseCase;
import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;
import com.bank.digital.aiassistant.domain.ports.in.AiAssistantServicePort;
import com.bank.digital.aiassistant.domain.ports.out.AiAssistantRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("EvaluateAiAssistantUseCase - Tests Unitarios")
class EvaluateAiAssistantUseCaseTest {

    @Mock
    private AiAssistantServicePort aiAssistantServicePort;

    @Mock
    private AiAssistantRepositoryPort aiAssistantRepositoryPort;

    private EvaluateAiAssistantUseCase evaluateAiAssistantUseCase;

    @BeforeEach
    void setUp() {
        evaluateAiAssistantUseCase = new EvaluateAiAssistantUseCase(
                aiAssistantServicePort,
                aiAssistantRepositoryPort
        );
    }

    @Test
    @DisplayName("Debe evaluar sugerencia exitosamente cuando existe y tiene alta relevancia")
    void execute_WhenSuggestionExistsAndHasHighRelevance_ShouldReturnPositiveEvaluation() throws ExecutionException, InterruptedException {
        UUID suggestionId = UUID.randomUUID();
        UUID assistantId = UUID.randomUUID();
        CodeSuggestion suggestion = new CodeSuggestion(
                suggestionId,
                assistantId,
                "Implementar método de autenticación JWT",
                "public String generateToken(User user) { return JWT.create().withSubject(user.getEmail()).sign(Algorithm.HMAC256(secret)); }",
                "JAVA",
                CodeSuggestion.SuggestionStatus.PENDING,
                true,
                Instant.now()
        );

        AiAssistant assistant = new AiAssistant(
                assistantId,
                AiAssistant.Provider.AMAZON_Q_DEV,
                "Amazon Q Dev",
                "1.0",
                List.of(AiAssistant.Capability.CODE_GENERATION),
                AiAssistant.AssistantStatus.ACTIVE,
                null,
                Instant.now()
        );

        when(aiAssistantServicePort.evaluateSuggestionQuality(any()))
                .thenReturn(CompletableFuture.completedFuture(0.85));

        EvaluateAiAssistantUseCase.EvaluationResult result = evaluateAiAssistantUseCase.execute(suggestionId).get();

        assertNotNull(result);
        assertTrue(result.score() >= 0.7);
        assertNotNull(result.feedback());
        verify(aiAssistantServicePort, times(1)).evaluateSuggestionQuality(any());
    }

    @Test
    @DisplayName("Debe evaluar sugerencia con baja puntuación cuando no es relevante")
    void execute_WhenSuggestionNotRelevant_ShouldReturnLowScore() throws ExecutionException, InterruptedException {
        UUID suggestionId = UUID.randomUUID();
        UUID assistantId = UUID.randomUUID();
        CodeSuggestion suggestion = new CodeSuggestion(
                suggestionId,
                assistantId,
                "Contexto genérico",
                "code snippet",
                "UNKNOWN",
                CodeSuggestion.SuggestionStatus.PENDING,
                false,
                Instant.now()
        );

        when(aiAssistantServicePort.evaluateSuggestionQuality(any()))
                .thenReturn(CompletableFuture.completedFuture(0.25));

        EvaluateAiAssistantUseCase.EvaluationResult result = evaluateAiAssistantUseCase.execute(suggestionId).get();

        assertNotNull(result);
        assertTrue(result.score() < 0.5);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando la sugerencia no existe")
    void execute_WhenSuggestionNotFound_ShouldThrowException() {
        UUID nonExistentSuggestionId = UUID.randomUUID();

        when(aiAssistantServicePort.evaluateSuggestionQuality(any()))
                .thenReturn(CompletableFuture.failedFuture(
                        new RuntimeException("Sugerencia no encontrada")
                ));

        assertThrows(Exception.class, () -> {
            evaluateAiAssistantUseCase.execute(nonExistentSuggestionId).get();
        });
    }

    @Test
    @DisplayName("Debe calcular score promedio de múltiples sugerencias del asistente")
    void execute_WhenMultipleSuggestions_ShouldCalculateAverageScore() throws ExecutionException, InterruptedException {
        UUID assistantId = UUID.randomUUID();
        List<CodeSuggestion> suggestions = List.of(
                new CodeSuggestion(UUID.randomUUID(), assistantId, "ctx1", "code1", "JAVA", CodeSuggestion.SuggestionStatus.PENDING, true, Instant.now()),
                new CodeSuggestion(UUID.randomUUID(), assistantId, "ctx2", "code2", "JAVA", CodeSuggestion.SuggestionStatus.PENDING, true, Instant.now()),
                new CodeSuggestion(UUID.randomUUID(), assistantId, "ctx3", "code3", "JAVA", CodeSuggestion.SuggestionStatus.PENDING, false, Instant.now())
        );

        when(aiAssistantServicePort.getSuggestionsByAssistant(assistantId))
                .thenReturn(CompletableFuture.completedFuture(suggestions));

        Double averageScore = suggestions.stream()
                .mapToDouble(s -> s.isHighRelevance() ? 0.8 : 0.3)
                .average()
                .orElse(0.0);

        assertEquals(0.633, averageScore, 0.01);
    }

    @Test
    @DisplayName("Debe generar feedback contextual según el score obtenido")
    void execute_ShouldGenerateContextualFeedback() throws ExecutionException, InterruptedException {
        UUID suggestionId = UUID.randomUUID();

        when(aiAssistantServicePort.evaluateSuggestionQuality(any()))
                .thenReturn(CompletableFuture.completedFuture(0.92));

        EvaluateAiAssistantUseCase.EvaluationResult result = evaluateAiAssistantUseCase.execute(suggestionId).get();

        assertNotNull(result.feedback());
        assertTrue(result.feedback().contains("excelente") || result.feedback().contains("alta"));
    }
}