package com.bank.digital.aiassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import com.bank.digital.aiassistant.domain.ports.out.AiAssistantRepositoryPort;
import com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase;
import com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase;
import com.bank.digital.aiassistant.infrastructure.config.AiAssistantConfig;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableConfigurationProperties(AiAssistantConfig.class)
@EnableAsync
@EnableSqs
public class AiAssistantApplication {
    private static final Logger logger = LoggerFactory.getLogger(AiAssistantApplication.class);

    private final AiAssistantRepositoryPort aiAssistantRepositoryPort;
    private final EvaluateAiAssistantUseCase evaluateAiAssistantUseCase;
    private final IntegrateAiAssistantUseCase integrateAiAssistantUseCase;
    private final AiAssistantConfig aiAssistantConfig;

    public AiAssistantApplication(AiAssistantRepositoryPort aiAssistantRepositoryPort,
                                 EvaluateAiAssistantUseCase evaluateAiAssistantUseCase,
                                 IntegrateAiAssistantUseCase integrateAiAssistantUseCase,
                                 AiAssistantConfig aiAssistantConfig) {
        this.aiAssistantRepositoryPort = aiAssistantRepositoryPort;
        this.evaluateAiAssistantUseCase = evaluateAiAssistantUseCase;
        this.integrateAiAssistantUseCase = integrateAiAssistantUseCase;
        this.aiAssistantConfig = aiAssistantConfig;
        validateConfiguration();
    }

    private void validateConfiguration() {
        if (aiAssistantConfig.getEvaluationThreshold() <= 0) {
            throw new IllegalArgumentException("Evaluation threshold must be positive");
        }
        if (aiAssistantConfig.getMaxConcurrentEvaluations() <= 0) {
            throw new IllegalArgumentException("Max concurrent evaluations must be positive");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AiAssistantApplication.class, args);
        logger.info("AI Assistant Application started successfully");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AI Assistant API")
                        .version("1.0")
                        .description("API for evaluating and integrating AI assistants in backend development")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(aiAssistantConfig.getCorePoolSize());
        executor.setMaxPoolSize(aiAssistantConfig.getMaxPoolSize());
        executor.setQueueCapacity(aiAssistantConfig.getQueueCapacity());
        executor.setThreadNamePrefix("AIAssistantExecutor-");
        executor.initialize();
        return executor;
    }

    @SqsListener(value = "${ai-assistant.evaluation-queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listenEvaluationQueue(String message) {
        try {
            logger.info("Processing evaluation message: {}", message);
            evaluateAiAssistantUseCase.evaluateFromQueue(message);
        } catch (Exception e) {
            logger.error("Error processing evaluation message: ", e);
            throw new RuntimeException("Failed to process evaluation message", e);
        }
    }

    @SqsListener(value = "${ai-assistant.integration-queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listenIntegrationQueue(String message) {
        try {
            logger.info("Processing integration message: {}", message);
            integrateAiAssistantUseCase.integrateFromQueue(message);
        } catch (Exception e) {
            logger.error("Error processing integration message: ", e);
            throw new RuntimeException("Failed to process integration message", e);
        }
    }
}