package com.bank.digital.aiassistant.infrastructure.adapters;





import com.bank.digital.aiassistant.domain.model.AssistantStatus;
import com.bank.digital.aiassistant.domain.model.Capability;
import com.bank.digital.aiassistant.domain.model.Provider;
import com.bank.digital.aiassistant.domain.model.SuggestionStatus;
import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;
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
@DisplayName("AiAssistantServiceAdapter - Tests Unitarios")
class AiAssistantServiceAdapterTest {

    @Mock
    private AiAssistantRepositoryPort aiAssistantRepositoryPort;

    private AiAssistantServiceAdapter aiAssistantServiceAdapter;

    @BeforeEach
    void setUp() {
        aiAssistantServiceAdapter = new AiAssistantServiceAdapter(aiAssistantRepositoryPort);
    }

    @Test
    @DisplayName("Debe generar sugerencia de código exitosamente")
    void generateCodeSuggestion_WhenValidRequest_ShouldReturnSuggestion() throws ExecutionException, InterruptedException {
        UUID assistantId = UUID.randomUUID();
        String context = "Implementar autenticación JWT";
        String language = "JAVA";

        CodeSuggestion expectedSuggestion = new CodeSuggestion(
                UUID.randomUUID(),
                assistantId,
                context,
                "public String generateToken(User user) { ... }",
                language,
                CodeSuggestion.SuggestionStatus.PENDING,
                true,
                Instant.now()
        );

        when(aiAssistantRepositoryPort.saveSuggestion(any(CodeSuggestion.class)))
                .thenReturn(CompletableFuture.completedFuture(expectedSuggestion));

        CompletableFuture<CodeSuggestion> result = aiAssistantServiceAdapter.generateCodeSuggestion(
                assistantId, context, language
        );

        assertNotNull(result.get());
        assertEquals(context, result.get().context());
        assertEquals(language, result.get().programmingLanguage());
        verify(aiAssistantRepositoryPort, times(1)).saveSuggestion(any(CodeSuggestion.class));
    }

    @Test
    @DisplayName("Debe evaluar calidad de sugerencia correctamente")
    void evaluateSuggestionQuality_WhenSuggestionExists_ShouldReturnScore() throws ExecutionException, InterruptedException {
        UUID suggestionId = UUID.randomUUID();
        CodeSuggestion suggestion = new CodeSuggestion(
                suggestionId,
                UUID.randomUUID(),
                "Contexto de prueba",
                "código de prueba",
                "JAVA",
                CodeSuggestion.SuggestionStatus.PENDING,
                true,
                Instant.now()
        );

        when(aiAssistantRepositoryPort.findSuggestionById(suggestionId))
                .thenReturn(CompletableFuture.completedFuture(suggestion));

        CompletableFuture<Double> result = aiAssistantServiceAdapter.evaluateSuggestionQuality(suggestionId);

        assertNotNull(result.get());
        assertTrue(result.get() >= 0.0 && result.get() <= 1.0);
    }

    @Test
    @DisplayName("Debe obtener sugerencias por asistente correctamente")
    void getSuggestionsByAssistant_WhenAssistantExists_ShouldReturnSuggestions() throws ExecutionException, InterruptedException {
        UUID assistantId = UUID.randomUUID();
        List<CodeSuggestion> suggestions = List.of(
                new CodeSuggestion(UUID.randomUUID(), assistantId, "ctx1", "code1", "JAVA", CodeSuggestion.SuggestionStatus.PENDING, true, Instant.now()),
                new CodeSuggestion(UUID.randomUUID(), assistantId, "ctx2", "code2", "JAVA", CodeSuggestion.SuggestionStatus.ACCEPTED, true, Instant.now())
        );

        when(aiAssistantRepositoryPort.findSuggestionsByAssistantId(assistantId))
                .thenReturn(CompletableFuture.completedFuture(suggestions));

        CompletableFuture<List<CodeSuggestion>> result = aiAssistantServiceAdapter.getSuggestionsByAssistant(assistantId);

        assertEquals(2, result.get().size());
        verify(aiAssistantRepositoryPort, times(1)).findSuggestionsByAssistantId(assistantId);
    }

    @Test
    @DisplayName("Debe registrar nuevo asistente de IA exitosamente")
    void registerAiAssistant_WhenValidData_ShouldRegisterAssistant() throws ExecutionException, InterruptedException {
        AiAssistant assistant = new AiAssistant(
                null,
                AiAssistant.Provider.AMAZON_Q_DEV,
                "Amazon Q Dev",
                "2.0",
                List.of(AiAssistant.Capability.CODE_GENERATION, AiAssistant.Capability.CODE_REVIEW),
                AiAssistant.AssistantStatus.ACTIVE,
                null,
                null
        );

        AiAssistant savedAssistant = new AiAssistant(
                UUID.randomUUID(),
                assistant.provider(),
                assistant.name(),
                assistant.version(),
                assistant.capabilities(),
                assistant.status(),
                assistant.config(),
                Instant.now()
        );

        when(aiAssistantRepositoryPort.saveAssistant(any(AiAssistant.class)))
                .thenReturn(CompletableFuture.completedFuture(savedAssistant));

        CompletableFuture<AiAssistant> result = aiAssistantServiceAdapter.registerAiAssistant(assistant);

        assertNotNull(result.get());
        assertNotNull(result.get().id());
        assertEquals(AiAssistant.Provider.AMAZON_Q_DEV, result.get().provider());
        verify(aiAssistantRepositoryPort, times(1)).saveAssistant(any(AiAssistant.class));
    }

    @Test
    @DisplayName("Debe actualizar estado del asistente correctamente")
    void updateAssistantStatus_WhenAssistantExists_ShouldUpdateStatus() throws ExecutionException, InterruptedException {
        UUID assistantId = UUID.randomUUID();
        AiAssistant existingAssistant = new AiAssistant(
                assistantId,
                AiAssistant.Provider.KIRO,
                "Kiro",
                "1.5",
                List.of(AiAssistant.Capability.CODE_GENERATION),
                AiAssistant.AssistantStatus.ACTIVE,
                null,
                Instant.now()
        );

        when(aiAssistantRepositoryPort.findAssistantById(assistantId))
                .thenReturn(CompletableFuture.completedFuture(existingAssistant));
        when(aiAssistantRepositoryPort.saveAssistant(any(AiAssistant.class)))
                .thenAnswer(invocation -> CompletableFuture.completedFuture(invocation.getArgument(0)));

        CompletableFuture<AiAssistant> result = aiAssistantServiceAdapter.updateAssistantStatus(
                assistantId, AiAssistant.AssistantStatus.DEPRECATED
        );

        assertEquals(AiAssistant.AssistantStatus.DEPRECATED, result.get().status());
    }

    @Test
    @DisplayName("Debe listar solo asistentes activos")
    void listActiveAssistants_WhenCalled_ShouldReturnOnlyActiveAssistants() throws ExecutionException, InterruptedException {
        List<AiAssistant> activeAssistants = List.of(
                new AiAssistant(UUID.randomUUID(), AiAssistant.Provider.GITHUB_COPILOT, "GitHub Copilot", "1.0", List.of(AiAssistant.Capability.CODE_COMPLETION), AiAssistant.AssistantStatus.ACTIVE, null, Instant.now()),
                new AiAssistant(UUID.randomUUID(), AiAssistant.Provider.AMAZON_Q_DEV, "Amazon Q", "1.0", List.of(AiAssistant.Capability.CODE_GENERATION), AiAssistant.AssistantStatus.ACTIVE, null, Instant.now())
        );

        when(aiAssistantRepositoryPort.findActiveAssistants())
                .thenReturn(CompletableFuture.completedFuture(activeAssistants));

        CompletableFuture<List<AiAssistant>> result = aiAssistantServiceAdapter.listActiveAssistants();

        assertEquals(2, result.get().size());
        result.get().forEach(assistant -> assertEquals(AiAssistant.AssistantStatus.ACTIVE, assistant.status()));
    }

    @Test
    @DisplayName("Debe actualizar estado de sugerencia correctamente")
    void updateSuggestionStatus_WhenSuggestionExists_ShouldUpdateStatus() throws ExecutionException, InterruptedException {
        UUID suggestionId = UUID.randomUUID();
        CodeSuggestion existingSuggestion = new CodeSuggestion(
                suggestionId,
                UUID.randomUUID(),
                "Contexto",
                "Código",
                "JAVA",
                CodeSuggestion.SuggestionStatus.PENDING,
                true,
                Instant.now()
        );

        when(aiAssistantRepositoryPort.findSuggestionById(suggestionId))
                .thenReturn(CompletableFuture.completedFuture(existingSuggestion));
        when(aiAssistantRepositoryPort.saveSuggestion(any(CodeSuggestion.class)))
                .thenAnswer(invocation -> CompletableFuture.completedFuture(invocation.getArgument(0)));

        CompletableFuture<CodeSuggestion> result = aiAssistantServiceAdapter.updateSuggestionStatus(
                suggestionId, CodeSuggestion.SuggestionStatus.ACCEPTED
        );

        assertEquals(CodeSuggestion.SuggestionStatus.ACCEPTED, result.get().status());
    }
}