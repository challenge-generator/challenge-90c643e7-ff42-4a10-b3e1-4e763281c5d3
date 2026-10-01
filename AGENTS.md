# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Optimización de Flujo de Trabajo con Asistentes de IA en Desarrollo Backend**.

| | |
|---|---|
| Tema | desarrollo |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5.6 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 2 semanas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0 2.5.0
- org.postgresql:postgresql n/a
- org.projectlombok:lombok n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.mockito:mockito-core n/a
- io.awspring.cloud:spring-cloud-aws-starter-sqs 3.1.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Evaluación de Asistentes de IA**: Documento de evaluación con propuestas de integración y mejoras.
- **Fase 2 — Integración y Prueba de Asistentes**: Reporte de integración y prueba con resultados y recomendaciones.
- **Fase 3 — Optimización y Recomendaciones**: Documento de optimización y recomendaciones para el uso de asistentes de IA.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Archivos que la arquitectura declara (2 de 15)

La propuesta arquitectonica del reto los lista y no llegaron al repo. Crealos con implementacion real, respetando la capa en la que viven:

- [ ] `src/main/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCase.java`
- [ ] `src/main/java/com/bank/digital/aiassistant/application/usecases/IntegrateAiAssistantUseCase.java`

### 2. Referencias colgando (103)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase`
      El import com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase`
      El import com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase`
      El import com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase`
      El import com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase`
      El import com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase`
      El import com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.OpenAPI pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/config/AiAssistantConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.OpenAPI pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `AiAssistantConfig.getEvaluationThreshold`
      Se invoca `getEvaluationThreshold` sobre `AiAssistantConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `AiAssistantConfig.getMaxConcurrentEvaluations`
      Se invoca `getMaxConcurrentEvaluations` sobre `AiAssistantConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `AiAssistantConfig.getCorePoolSize`
      Se invoca `getCorePoolSize` sobre `AiAssistantConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `AiAssistantConfig.getMaxPoolSize`
      Se invoca `getMaxPoolSize` sobre `AiAssistantConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `AiAssistantConfig.getQueueCapacity`
      Se invoca `getQueueCapacity` sobre `AiAssistantConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `EvaluateAiAssistantUseCase.evaluateFromQueue`
      Se invoca `evaluateFromQueue` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `IntegrateAiAssistantUseCase.integrateFromQueue`
      Se invoca `integrateFromQueue` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.id`
      Se invoca `id` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `EvaluationResult.assistantId`
      Se invoca `assistantId` sobre `EvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.name`
      Se invoca `name` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.provider`
      Se invoca `provider` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.apiKey`
      Se invoca `apiKey` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.endpoint`
      Se invoca `endpoint` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.configuration`
      Se invoca `configuration` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.capabilities`
      Se invoca `capabilities` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.name`
      Se invoca `name` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.name`
      Se invoca `name` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.provider`
      Se invoca `provider` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.status`
      Se invoca `status` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.status`
      Se invoca `status` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.capabilities`
      Se invoca `capabilities` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.capabilities`
      Se invoca `capabilities` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.endpoint`
      Se invoca `endpoint` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.endpoint`
      Se invoca `endpoint` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.apiKey`
      Se invoca `apiKey` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.apiKey`
      Se invoca `apiKey` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.configuration`
      Se invoca `configuration` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.configuration`
      Se invoca `configuration` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.createdAt`
      Se invoca `createdAt` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.deprecated`
      Se invoca `deprecated` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.id`
      Se invoca `id` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.name`
      Se invoca `name` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.provider`
      Se invoca `provider` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.status`
      Se invoca `status` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.capabilities`
      Se invoca `capabilities` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.configuration`
      Se invoca `configuration` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.createdAt`
      Se invoca `createdAt` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.updatedAt`
      Se invoca `updatedAt` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.id`
      Se invoca `id` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.assistantId`
      Se invoca `assistantId` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.code`
      Se invoca `code` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.explanation`
      Se invoca `explanation` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.relevanceScore`
      Se invoca `relevanceScore` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.status`
      Se invoca `status` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.createdAt`
      Se invoca `createdAt` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.updatedAt`
      Se invoca `updatedAt` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `IntegrateAiAssistantUseCase.generateSuggestion`
      Se invoca `generateSuggestion` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `EvaluateAiAssistantUseCase.evaluateQuality`
      Se invoca `evaluateQuality` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `IntegrateAiAssistantUseCase.getSuggestionsByAssistant`
      Se invoca `getSuggestionsByAssistant` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `IntegrateAiAssistantUseCase.registerAssistant`
      Se invoca `registerAssistant` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `IntegrateAiAssistantUseCase.updateAssistantStatus`
      Se invoca `updateAssistantStatus` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `IntegrateAiAssistantUseCase.listActiveAssistants`
      Se invoca `listActiveAssistants` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `EvaluateAiAssistantUseCase.updateSuggestionStatus`
      Se invoca `updateSuggestionStatus` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `EvaluateAiAssistantUseCase.calculateEffectiveness`
      Se invoca `calculateEffectiveness` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.provider`
      Se invoca `provider` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.name`
      Se invoca `name` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.version`
      Se invoca `version` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.capabilities`
      Se invoca `capabilities` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.config`
      Se invoca `config` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.status`
      Se invoca `status` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.context`
      Se invoca `context` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.programmingLanguage`
      Se invoca `programmingLanguage` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `EvaluateAiAssistantUseCase.execute`
      Se invoca `execute` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAiAssistantUseCase.execute`
      Se invoca `execute` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.credentials`
      Se invoca `credentials` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.id`
      Se invoca `id` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.provider`
      Se invoca `provider` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.name`
      Se invoca `name` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.version`
      Se invoca `version` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.capabilities`
      Se invoca `capabilities` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.status`
      Se invoca `status` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.config`
      Se invoca `config` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.createdAt`
      Se invoca `createdAt` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.id`
      Se invoca `id` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.assistantId`
      Se invoca `assistantId` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.context`
      Se invoca `context` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.suggestedCode`
      Se invoca `suggestedCode` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.programmingLanguage`
      Se invoca `programmingLanguage` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.status`
      Se invoca `status` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.createdAt`
      Se invoca `createdAt` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCaseTest.java` — `EvaluateAiAssistantUseCase.execute`
      Se invoca `execute` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCaseTest.java` — `EvaluationResult.score`
      Se invoca `score` sobre `EvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCaseTest.java` — `EvaluationResult.feedback`
      Se invoca `feedback` sobre `EvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistantRepositoryPort.findSuggestionById`
      Se invoca `findSuggestionById` sobre `AiAssistantRepositoryPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.provider`
      Se invoca `provider` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.name`
      Se invoca `name` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.version`
      Se invoca `version` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.capabilities`
      Se invoca `capabilities` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.status`
      Se invoca `status` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.config`
      Se invoca `config` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistantRepositoryPort.findAssistantById`
      Se invoca `findAssistantById` sobre `AiAssistantRepositoryPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistantRepositoryPort.findActiveAssistants`
      Se invoca `findActiveAssistants` sobre `AiAssistantRepositoryPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (16)

- `pom.xml`
- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/bank/digital/aiassistant/domain/model/CodeSuggestion.java`
- `src/main/java/com/bank/digital/aiassistant/domain/model/AiAssistant.java`
- `src/main/java/com/bank/digital/aiassistant/domain/ports/in/AiAssistantServicePort.java`
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java`
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java`
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java`
- `src/main/java/com/bank/digital/aiassistant/infrastructure/config/AiAssistantConfig.java`
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java`
- `src/test/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCaseTest.java`
- `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java`
- `docs/evaluacion-asistentes-ia.md`
- `docs/reporte-integracion-pruebas.md`
- `docs/optimizacion-recomendaciones.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/bank/digital/aiassistant`
- `src/main/java/com/bank/digital/aiassistant/application`
- `src/main/java/com/bank/digital/aiassistant/application/usecases`
- `src/main/java/com/bank/digital/aiassistant/domain`
- `src/main/java/com/bank/digital/aiassistant/domain/model`
- `src/main/java/com/bank/digital/aiassistant/domain/ports`
- `src/main/java/com/bank/digital/aiassistant/infrastructure`
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters`
- `src/main/java/com/bank/digital/aiassistant/infrastructure/config`
- `src/main/resources`
- `src/test/java/com/bank/digital/aiassistant`
- `docs`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior
- Brecha que el reto ataca: Ha trabajado con algún asistente AI en desarrollo (Amazon Q Dev, Kiro, Github Copilot) y lo utiliza en su día a día como desarrollador
- Mision: Candidato con experiencia en Backend, buscando dominar herramientas de IA para mejorar su productividad y calidad de código

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
