package com.bank.digital.aiassistant.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CodeSuggestion(
    @NotNull(message = "El ID de la sugerencia no puede ser nulo")
    UUID id,

    @NotNull(message = "El ID del asistente no puede ser nulo")
    UUID aiAssistantId,

    @NotBlank(message = "El contenido de la sugerencia no puede estar vacío")
    @Size(max = 2000, message = "El contenido de la sugerencia no puede exceder los 2000 caracteres")
    String content,

    @NotBlank(message = "El contexto de la sugerencia no puede estar vacío")
    @Size(max = 1000, message = "El contexto no puede exceder los 1000 caracteres")
    String context,

    @NotNull(message = "La relevancia de la sugerencia no puede ser nula")
    Integer relevanceScore,

    @NotNull(message = "La fecha de creación no puede ser nula")
    LocalDateTime createdAt,

    @NotNull(message = "El estado de la sugerencia no puede ser nulo")
    SuggestionStatus status,

    List<String> tags
) {
    public enum SuggestionStatus {
        PENDING,
        ACCEPTED,
        REJECTED,
        IMPLEMENTED
    }

    public CodeSuggestion {
        if (relevanceScore < 0 || relevanceScore > 100) {
            throw new IllegalArgumentException("La relevancia debe estar entre 0 y 100");
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public boolean isHighRelevance() {
        return relevanceScore >= 80;
    }

    public boolean isAccepted() {
        return status == SuggestionStatus.ACCEPTED;
    }

    public boolean isRejected() {
        return status == SuggestionStatus.REJECTED;
    }
}