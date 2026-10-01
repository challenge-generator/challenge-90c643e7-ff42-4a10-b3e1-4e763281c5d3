package com.bank.digital.aiassistant.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record AiAssistant(
    @NotNull(message = "El ID del asistente no puede ser nulo")
    UUID id,

    @NotBlank(message = "El nombre del asistente no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    String name,

    @NotBlank(message = "La descripción del asistente no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    String description,

    @NotNull(message = "El proveedor del asistente no puede ser nulo")
    Provider provider,

    @NotNull(message = "La fecha de creación no puede ser nula")
    LocalDateTime createdAt,

    @NotNull(message = "La versión no puede ser nula")
    @Size(max = 20, message = "La versión no puede exceder los 20 caracteres")
    String version,

    @NotNull(message = "El estado no puede ser nulo")
    AssistantStatus status,

    List<Capability> capabilities
) {
    public enum Provider {
        AMAZON_Q_DEV,
        KIRO,
        GITHUB_COPILOT,
        CUSTOM
    }

    public enum AssistantStatus {
        ACTIVE,
        INACTIVE,
        DEPRECATED
    }

    public enum Capability {
        CODE_GENERATION,
        CODE_REVIEW,
        TEST_GENERATION,
        DOCUMENTATION,
        PERFORMANCE_OPTIMIZATION,
        SECURITY_ANALYSIS
    }

    public AiAssistant {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public boolean supportsCapability(Capability capability) {
        return capabilities != null && capabilities.contains(capability);
    }

    public boolean isActive() {
        return status == AssistantStatus.ACTIVE;
    }

    public boolean isDeprecated() {
        return status == AssistantStatus.DEPRECATED;
    }
}