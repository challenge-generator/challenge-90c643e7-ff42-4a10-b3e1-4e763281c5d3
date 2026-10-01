package com.bank.digital.aiassistant.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AiAssistantConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
            .setConnectTimeout(Duration.ofSeconds(30))
            .setReadTimeout(Duration.ofSeconds(60))
            .requestFactory(HttpComponentsClientHttpRequestFactory.class)
            .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Integración de Asistentes de IA")
                .version("1.0")
                .description(""
                    + "API para la integración y evaluación de asistentes de IA "
                    + "en el desarrollo backend de la plataforma de banca digital. "
                    + "Soporta Amazon Q Dev, Kiro y GitHub Copilot."
                )
                .contact(new Contact()
                    .name("Equipo de Desarrollo Backend")
                    .email("backend-team@bankdigital.com")
                )
            )
            .schemaRequirement("bearerAuth", new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Token JWT para autenticación")
            );
    }

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(200);
        executor.setThreadNamePrefix("ai-assistant-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }

    @Bean
    public AiAssistantAwsConfig awsConfig() {
        return new AiAssistantAwsConfig();
    }

    public static class AiAssistantAwsConfig {
        private String region = "us-east-1";
        private String sqsEvaluationQueue = "ai-assistant-evaluation-queue";
        private String sqsIntegrationQueue = "ai-assistant-integration-queue";
        private int maxConnections = 100;
        private int connectionTimeout = 5000;
        private int socketTimeout = 30000;

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getSqsEvaluationQueue() {
            return sqsEvaluationQueue;
        }

        public void setSqsEvaluationQueue(String sqsEvaluationQueue) {
            this.sqsEvaluationQueue = sqsEvaluationQueue;
        }

        public String getSqsIntegrationQueue() {
            return sqsIntegrationQueue;
        }

        public void setSqsIntegrationQueue(String sqsIntegrationQueue) {
            this.sqsIntegrationQueue = sqsIntegrationQueue;
        }

        public int getMaxConnections() {
            return maxConnections;
        }

        public void setMaxConnections(int maxConnections) {
            this.maxConnections = maxConnections;
        }

        public int getConnectionTimeout() {
            return connectionTimeout;
        }

        public void setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }

        public int getSocketTimeout() {
            return socketTimeout;
        }

        public void setSocketTimeout(int socketTimeout) {
            this.socketTimeout = socketTimeout;
        }
    }
}