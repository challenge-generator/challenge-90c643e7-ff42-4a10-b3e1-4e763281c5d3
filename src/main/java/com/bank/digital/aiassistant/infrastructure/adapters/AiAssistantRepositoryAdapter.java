package com.bank.digital.aiassistant.infrastructure.adapters;




import com.bank.digital.aiassistant.domain.model.SuggestionStatus;
import com.bank.digital.aiassistant.domain.model.Provider;
import com.bank.digital.aiassistant.domain.model.AssistantStatus;
import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;
import com.bank.digital.aiassistant.domain.ports.out.AiAssistantRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Repository
@Transactional
public class AiAssistantRepositoryAdapter implements AiAssistantRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CompletableFuture<AiAssistant> save(AiAssistant assistant) {
        return CompletableFuture.supplyAsync(() -> {
            if (assistant.id() == null || assistant.id().isEmpty()) {
                UUID newId = UUID.randomUUID();
                AiAssistant assistantWithId = new AiAssistant(
                    newId.toString(),
                    assistant.name(),
                    assistant.provider(),
                    assistant.status(),
                    assistant.capabilities(),
                    assistant.configuration(),
                    assistant.createdAt() != null ? assistant.createdAt() : LocalDateTime.now(),
                    assistant.updatedAt() != null ? assistant.updatedAt() : LocalDateTime.now()
                );
                entityManager.persist(assistantWithId);
                return assistantWithId;
            }
            AiAssistant merged = entityManager.merge(assistant);
            return merged;
        });
    }

    @Override
    public CompletableFuture<Optional<AiAssistant>> findById(String id) {
        return CompletableFuture.supplyAsync(() -> {
            AiAssistant assistant = entityManager.find(AiAssistant.class, id);
            return Optional.ofNullable(assistant);
        });
    }

    @Override
    public CompletableFuture<List<AiAssistant>> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            Query query = entityManager.createQuery("SELECT a FROM AiAssistant a", AiAssistant.class);
            return query.getResultList();
        });
    }

    @Override
    public CompletableFuture<List<AiAssistant>> findByStatus(AiAssistant.AssistantStatus status) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = entityManager.createQuery(
                "SELECT a FROM AiAssistant a WHERE a.status = :status", AiAssistant.class);
            query.setParameter("status", status);
            return query.getResultList();
        });
    }

    @Override
    public CompletableFuture<List<AiAssistant>> findByProvider(AiAssistant.Provider provider) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = entityManager.createQuery(
                "SELECT a FROM AiAssistant a WHERE a.provider = :provider", AiAssistant.class);
            query.setParameter("provider", provider);
            return query.getResultList();
        });
    }

    @Override
    public CompletableFuture<Void> deleteById(String id) {
        return CompletableFuture.runAsync(() -> {
            AiAssistant assistant = entityManager.find(AiAssistant.class, id);
            if (assistant != null) {
                entityManager.remove(assistant);
            }
        });
    }

    @Override
    public CompletableFuture<CodeSuggestion> saveSuggestion(CodeSuggestion suggestion) {
        return CompletableFuture.supplyAsync(() -> {
            if (suggestion.id() == null || suggestion.id().isEmpty()) {
                UUID newId = UUID.randomUUID();
                CodeSuggestion suggestionWithId = new CodeSuggestion(
                    newId.toString(),
                    suggestion.assistantId(),
                    suggestion.code(),
                    suggestion.explanation(),
                    suggestion.relevanceScore(),
                    suggestion.status(),
                    suggestion.createdAt() != null ? suggestion.createdAt() : LocalDateTime.now(),
                    suggestion.updatedAt() != null ? suggestion.updatedAt() : LocalDateTime.now()
                );
                entityManager.persist(suggestionWithId);
                return suggestionWithId;
            }
            return entityManager.merge(suggestion);
        });
    }

    @Override
    public CompletableFuture<List<CodeSuggestion>> findSuggestionsByAssistantId(String assistantId) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = entityManager.createQuery(
                "SELECT s FROM CodeSuggestion s WHERE s.assistantId = :assistantId ORDER BY s.createdAt DESC",
                CodeSuggestion.class);
            query.setParameter("assistantId", assistantId);
            return query.getResultList();
        });
    }

    @Override
    public CompletableFuture<List<CodeSuggestion>> findSuggestionsByStatus(
            CodeSuggestion.SuggestionStatus status) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = entityManager.createQuery(
                "SELECT s FROM CodeSuggestion s WHERE s.status = :status", CodeSuggestion.class);
            query.setParameter("status", status);
            return query.getResultList();
        });
    }

    @Override
    public CompletableFuture<Optional<CodeSuggestion>> findSuggestionById(String id) {
        return CompletableFuture.supplyAsync(() -> {
            CodeSuggestion suggestion = entityManager.find(CodeSuggestion.class, id);
            return Optional.ofNullable(suggestion);
        });
    }

    @Override
    public CompletableFuture<Double> calculateAverageRelevanceByAssistant(String assistantId) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = entityManager.createQuery(
                "SELECT AVG(s.relevanceScore) FROM CodeSuggestion s WHERE s.assistantId = :assistantId",
                Double.class);
            query.setParameter("assistantId", assistantId);
            Double result = (Double) query.getSingleResult();
            return result != null ? result : 0.0;
        });
    }

    @Override
    public CompletableFuture<Long> countSuggestionsByAssistantAndStatus(
            String assistantId, CodeSuggestion.SuggestionStatus status) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = entityManager.createQuery(
                "SELECT COUNT(s) FROM CodeSuggestion s WHERE s.assistantId = :assistantId AND s.status = :status",
                Long.class);
            query.setParameter("assistantId", assistantId);
            query.setParameter("status", status);
            return (Long) query.getSingleResult();
        });
    }
}