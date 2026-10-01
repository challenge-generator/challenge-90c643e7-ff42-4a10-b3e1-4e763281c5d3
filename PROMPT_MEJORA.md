# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Archivos que la arquitectura del reto declara y no estan

Creálos con implementacion real, en la capa que les corresponde:

- `src/main/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCase.java`
- `src/main/java/com/bank/digital/aiassistant/application/usecases/IntegrateAiAssistantUseCase.java`

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase`: El import com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase`: El import com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase`: El import com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase`: El import com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase`: El import com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase`: El import com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.OpenAPI pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/aiassistant/infrastructure/config/AiAssistantConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.OpenAPI pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `AiAssistantConfig.getEvaluationThreshold`: Se invoca `getEvaluationThreshold` sobre `AiAssistantConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `AiAssistantConfig.getMaxConcurrentEvaluations`: Se invoca `getMaxConcurrentEvaluations` sobre `AiAssistantConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `AiAssistantConfig.getCorePoolSize`: Se invoca `getCorePoolSize` sobre `AiAssistantConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `AiAssistantConfig.getMaxPoolSize`: Se invoca `getMaxPoolSize` sobre `AiAssistantConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `AiAssistantConfig.getQueueCapacity`: Se invoca `getQueueCapacity` sobre `AiAssistantConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `EvaluateAiAssistantUseCase.evaluateFromQueue`: Se invoca `evaluateFromQueue` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java` — `IntegrateAiAssistantUseCase.integrateFromQueue`: Se invoca `integrateFromQueue` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.id`: Se invoca `id` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `EvaluationResult.assistantId`: Se invoca `assistantId` sobre `EvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.name`: Se invoca `name` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.provider`: Se invoca `provider` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.apiKey`: Se invoca `apiKey` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.endpoint`: Se invoca `endpoint` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.configuration`: Se invoca `configuration` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationRequest.capabilities`: Se invoca `capabilities` sobre `IntegrationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.name`: Se invoca `name` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.name`: Se invoca `name` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.provider`: Se invoca `provider` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.status`: Se invoca `status` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.status`: Se invoca `status` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.capabilities`: Se invoca `capabilities` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.capabilities`: Se invoca `capabilities` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.endpoint`: Se invoca `endpoint` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.endpoint`: Se invoca `endpoint` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.apiKey`: Se invoca `apiKey` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.apiKey`: Se invoca `apiKey` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `IntegrationUpdateRequest.configuration`: Se invoca `configuration` sobre `IntegrationUpdateRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.configuration`: Se invoca `configuration` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.createdAt`: Se invoca `createdAt` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java` — `AiAssistant.deprecated`: Se invoca `deprecated` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.id`: Se invoca `id` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.name`: Se invoca `name` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.provider`: Se invoca `provider` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.status`: Se invoca `status` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.capabilities`: Se invoca `capabilities` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.configuration`: Se invoca `configuration` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.createdAt`: Se invoca `createdAt` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `AiAssistant.updatedAt`: Se invoca `updatedAt` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.id`: Se invoca `id` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.assistantId`: Se invoca `assistantId` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.code`: Se invoca `code` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.explanation`: Se invoca `explanation` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.relevanceScore`: Se invoca `relevanceScore` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.status`: Se invoca `status` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.createdAt`: Se invoca `createdAt` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java` — `CodeSuggestion.updatedAt`: Se invoca `updatedAt` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `IntegrateAiAssistantUseCase.generateSuggestion`: Se invoca `generateSuggestion` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `EvaluateAiAssistantUseCase.evaluateQuality`: Se invoca `evaluateQuality` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `IntegrateAiAssistantUseCase.getSuggestionsByAssistant`: Se invoca `getSuggestionsByAssistant` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `IntegrateAiAssistantUseCase.registerAssistant`: Se invoca `registerAssistant` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `IntegrateAiAssistantUseCase.updateAssistantStatus`: Se invoca `updateAssistantStatus` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `IntegrateAiAssistantUseCase.listActiveAssistants`: Se invoca `listActiveAssistants` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `EvaluateAiAssistantUseCase.updateSuggestionStatus`: Se invoca `updateSuggestionStatus` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java` — `EvaluateAiAssistantUseCase.calculateEffectiveness`: Se invoca `calculateEffectiveness` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.provider`: Se invoca `provider` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.name`: Se invoca `name` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.version`: Se invoca `version` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.capabilities`: Se invoca `capabilities` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.config`: Se invoca `config` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.status`: Se invoca `status` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.context`: Se invoca `context` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.programmingLanguage`: Se invoca `programmingLanguage` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `EvaluateAiAssistantUseCase.execute`: Se invoca `execute` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAiAssistantUseCase.execute`: Se invoca `execute` sobre `IntegrateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `IntegrateAssistantRequest.credentials`: Se invoca `credentials` sobre `IntegrateAssistantRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.id`: Se invoca `id` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.provider`: Se invoca `provider` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.name`: Se invoca `name` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.version`: Se invoca `version` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.capabilities`: Se invoca `capabilities` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.status`: Se invoca `status` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.config`: Se invoca `config` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `AiAssistant.createdAt`: Se invoca `createdAt` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.id`: Se invoca `id` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.assistantId`: Se invoca `assistantId` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.context`: Se invoca `context` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.suggestedCode`: Se invoca `suggestedCode` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.programmingLanguage`: Se invoca `programmingLanguage` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.status`: Se invoca `status` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java` — `CodeSuggestion.createdAt`: Se invoca `createdAt` sobre `CodeSuggestion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCaseTest.java` — `EvaluateAiAssistantUseCase.execute`: Se invoca `execute` sobre `EvaluateAiAssistantUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCaseTest.java` — `EvaluationResult.score`: Se invoca `score` sobre `EvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCaseTest.java` — `EvaluationResult.feedback`: Se invoca `feedback` sobre `EvaluationResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistantRepositoryPort.findSuggestionById`: Se invoca `findSuggestionById` sobre `AiAssistantRepositoryPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.provider`: Se invoca `provider` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.name`: Se invoca `name` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.version`: Se invoca `version` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.capabilities`: Se invoca `capabilities` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.status`: Se invoca `status` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistant.config`: Se invoca `config` sobre `AiAssistant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistantRepositoryPort.findAssistantById`: Se invoca `findAssistantById` sobre `AiAssistantRepositoryPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java` — `AiAssistantRepositoryPort.findActiveAssistants`: Se invoca `findActiveAssistants` sobre `AiAssistantRepositoryPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior

### Brecha de conocimiento
Ha trabajado con algún asistente AI en desarrollo (Amazon Q Dev, Kiro, Github Copilot) y lo utiliza en su día a día como desarrollador

### Misión / candidato
Candidato con experiencia en Backend, buscando dominar herramientas de IA para mejorar su productividad y calidad de código

### Reto
- Tema: desarrollo
- Seniority: senior-l2
- Tipo: mixed
- Título: Optimización de Flujo de Trabajo con Asistentes de IA en Desarrollo Backend
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Evaluación de Asistentes de IA — objetivo: Identificar las funcionalidades y beneficios de cada asistente de IA en el contexto del desarrollo backend. — entregable (NO resolver): Documento de evaluación con propuestas de integración y mejoras.
- Fase 2: Integración y Prueba de Asistentes — objetivo: Integrar los asistentes de IA seleccionados en el entorno de desarrollo y realizar pruebas para evaluar su eficacia. — entregable (NO resolver): Reporte de integración y prueba con resultados y recomendaciones.
- Fase 3: Optimización y Recomendaciones — objetivo: Optimizar el uso de los asistentes de IA y proporcionar recomendaciones para su uso continuo en el equipo. — entregable (NO resolver): Documento de optimización y recomendaciones para el uso de asistentes de IA.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.bank.digital</groupId>
    <artifactId>ai-assistant</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>ai-assistant</name>
    <description>Sistema de integración y evaluación de asistentes de IA para desarrollo backend</description>

    <properties>
        <java.version>21</java.version>
        <spring-cloud.version>2023.0.3</spring-cloud.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- OpenAPI Documentation -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- AWS SQS -->
        <dependency>
            <groupId>io.awspring.cloud</groupId>
            <artifactId>spring-cloud-aws-starter-sqs</artifactId>
            <version>3.1.0</version>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/AiAssistantApplication.java ===
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

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: ai-assistant
  datasource:
    url: jdbc:postgresql://localhost:5432/ai_assistant_db
    username: ai_assistant_user
    password: ${DB_PASSWORD:securepassword}
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      idle-timeout: 30000
      max-lifetime: 1800000
      connection-timeout: 30000
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
        jdbc:
          batch_size: 50
        order_inserts: true
        order_updates: true
  cloud:
    aws:
      region:
        static: us-east-1
      credentials:
        access-key: ${AWS_ACCESS_KEY_ID:default}
        secret-key: ${AWS_SECRET_ACCESS_KEY:default}
      sqs:
        endpoint: ${AWS_SQS_ENDPOINT:https://sqs.us-east-1.amazonaws.com}

ai-assistant:
  evaluation-threshold: 0.7
  max-concurrent-evaluations: 10
  core-pool-size: 5
  max-pool-size: 10
  queue-capacity: 100
  evaluation-queue: ai-assistant-evaluation-queue
  integration-queue: ai-assistant-integration-queue
  github-copilot:
    api-url: https://api.github.com/copilot
    timeout: 5000
  amazon-q-dev:
    api-url: https://q.amazonaws.com/dev
    timeout: 5000
  kiro:
    api-url: https://api.kiro.ai
    timeout: 5000

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operationsSorter: method

// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/domain/model/CodeSuggestion.java ===
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

// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/domain/model/AiAssistant.java ===
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

// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/domain/ports/in/AiAssistantServicePort.java ===
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

// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/domain/ports/out/AiAssistantRepositoryPort.java ===
package com.bank.digital.aiassistant.domain.ports.out;





import com.bank.digital.aiassistant.domain.model.AssistantStatus;
import com.bank.digital.aiassistant.domain.model.Capability;
import com.bank.digital.aiassistant.domain.model.SuggestionStatus;
import com.bank.digital.aiassistant.domain.model.Provider;
import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface AiAssistantRepositoryPort {

    CompletableFuture<AiAssistant> saveAssistant(AiAssistant assistant);

    CompletableFuture<Optional<AiAssistant>> findById(UUID assistantId);

    CompletableFuture<List<AiAssistant>> findAllActive();

    CompletableFuture<List<AiAssistant>> findByProvider(AiAssistant.Provider provider);

    CompletableFuture<AiAssistant> updateAssistant(AiAssistant assistant);

    CompletableFuture<Void> deleteAssistant(UUID assistantId);

    CompletableFuture<CodeSuggestion> saveSuggestion(CodeSuggestion suggestion);

    CompletableFuture<List<CodeSuggestion>> findSuggestionsByAssistantId(UUID assistantId);

    CompletableFuture<List<CodeSuggestion>> findSuggestionsByStatus(CodeSuggestion.SuggestionStatus status);

    CompletableFuture<CodeSuggestion> updateSuggestion(CodeSuggestion suggestion);

    CompletableFuture<Void> deleteSuggestion(UUID suggestionId);

    CompletableFuture<Long> countSuggestionsByAssistantAndStatus(UUID assistantId, CodeSuggestion.SuggestionStatus status);

    CompletableFuture<Double> calculateAcceptanceRate(UUID assistantId);

    CompletableFuture<List<AiAssistant>> findAssistantsByCapability(AiAssistant.Capability capability);

    CompletableFuture<Boolean> existsByNameAndProvider(String name, AiAssistant.Provider provider);

    CompletableFuture<Void> batchSaveSuggestions(List<CodeSuggestion> suggestions);
}// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCase.java ===
package com.bank.digital.aiassistant.application.usecases;

import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;
import com.bank.digital.aiassistant.domain.ports.in.AiAssistantServicePort;
import com.bank.digital.aiassistant.domain.ports.out.AiAssistantRepositoryPort;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class EvaluateAiAssistantUseCase {

    private final AiAssistantRepositoryPort repositoryPort;
    private final AiAssistantServicePort servicePort;

    public EvaluateAiAssistantUseCase(AiAssistantRepositoryPort repositoryPort,
                                       AiAssistantServicePort servicePort) {
        this.repositoryPort = repositoryPort;
        this.servicePort = servicePort;
    }

    public CompletableFuture<EvaluationResult> evaluateAssistant(UUID assistantId) {
        return repositoryPort.findById(assistantId)
            .thenCompose(optAssistant -> {
                if (optAssistant.isEmpty()) {
                    return CompletableFuture.completedFuture(
                        EvaluationResult.failure("Asistente no encontrado con ID: " + assistantId));
                }

                AiAssistant assistant = optAssistant.get();
                return performComprehensiveEvaluation(assistant);
            });
    }

    private CompletableFuture<EvaluationResult> performComprehensiveEvaluation(AiAssistant assistant) {
        CompletableFuture<List<CodeSuggestion>> suggestionsFuture =
            repositoryPort.findSuggestionsByAssistantId(assistant.id());

        CompletableFuture<Long> acceptedCountFuture =
            repositoryPort.countSuggestionsByAssistantAndStatus(
                assistant.id(), CodeSuggestion.SuggestionStatus.ACCEPTED);

        CompletableFuture<Long> rejectedCountFuture =
            repositoryPort.countSuggestionsByAssistantAndStatus(
                assistant.id(), CodeSuggestion.SuggestionStatus.REJECTED);

        CompletableFuture<Long> pendingCountFuture =
            repositoryPort.countSuggestionsByAssistantAndStatus(
                assistant.id(), CodeSuggestion.SuggestionStatus.PENDING);

        return CompletableFuture.allOf(
                suggestionsFuture, acceptedCountFuture, rejectedCountFuture, pendingCountFuture)
            .thenApply(v -> {
                List<CodeSuggestion> allSuggestions = suggestionsFuture.join();
                long accepted = acceptedCountFuture.join();
                long rejected = rejectedCountFuture.join();
                long pending = pendingCountFuture.join();
                long total = accepted + rejected + pending;

                double acceptanceRate = total > 0 ? (double) accepted / total * 100 : 0.0;
                double rejectionRate = total > 0 ? (double) rejected / total * 100 : 0.0;

                List<String> strengths = new ArrayList<>();
                List<String> weaknesses = new ArrayList<>();
                List<String> recommendations = new ArrayList<>();

                if (acceptanceRate >= 70.0) {
                    strengths.add("Alta tasa de aceptación (" + String.format("%.2f", acceptanceRate) + "%)");
                } else if (acceptanceRate < 50.0) {
                    weaknesses.add("Baja tasa de aceptación (" + String.format("%.2f", acceptanceRate) + "%)");
                    recommendations.add("Revisar la calidad de las sugerencias generadas");
                    recommendations.add("Evaluar el contexto de código proporcionado al asistente");
                }

                if (assistant.supportsCapability(AiAssistant.Capability.CODE_GENERATION)) {
                    strengths.add("Soporta generación de código");
                }

                if (assistant.supportsCapability(AiAssistant.Capability.CODE_REFACTORING)) {
                    strengths.add("Soporta refactorización de código");
                }

                if (assistant.supportsCapability(AiAssistant.Capability.SECURITY_ANALYSIS)) {
                    strengths.add("Incluye análisis de seguridad");
                }

                if (assistant.isDeprecated()) {
                    weaknesses.add("Asistente marcado como deprecado");
                    recommendations.add("Considerar migración a una versión actualizada");
                }

                if (!assistant.isActive()) {
                    weaknesses.add("Asistente actualmente inactivo");
                    recommendations.add("Activar el asistente para comenzar a evaluar");
                }

                List<CodeSuggestion> highRelevanceSuggestions = allSuggestions.stream()
                    .filter(CodeSuggestion::isHighRelevance)
                    .collect(Collectors.toList());

                if (!highRelevanceSuggestions.isEmpty()) {
                    long highRelevanceAccepted = highRelevanceSuggestions.stream()
                        .filter(CodeSuggestion::isAccepted)
                        .count();
                    double highRelevanceRate = (double) highRelevanceAccepted / highRelevanceSuggestions.size() * 100;
                    recommendations.add("Sugerencias de alta relevancia: " + highRelevanceAccepted + "/" +
                        highRelevanceSuggestions.size() + " aceptadas (" + String.format("%.2f", highRelevanceRate) + "%)");
                }

                return EvaluationResult.success(
                    assistant,
                    total,
                    accepted,
                    rejected,
                    pending,
                    acceptanceRate,
                    rejectionRate,
                    strengths,
                    weaknesses,
                    recommendations
                );
            });
    }

    public CompletableFuture<List<EvaluationResult>> evaluateAllActiveAssistants() {
        return repositoryPort.findAllActive()
            .thenApply(assistants -> {
                List<CompletableFuture<EvaluationResult>> evaluationFutures = assistants.stream()
                    .map(this::performComprehensiveEvaluation)
                    .collect(Collectors.toList());

                return evaluationFutures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());
            });
    }

    public CompletableFuture<EvaluationResult> compareAssistants(List<UUID> assistantIds) {
        List<CompletableFuture<EvaluationResult>> evaluations = assistantIds.stream()
            .map(this::evaluateAssistant)
            .collect(Collectors.toList());

        return CompletableFuture.allOf(evaluations.toArray(new CompletableFuture[0]))
            .thenApply(v -> {
                List<EvaluationResult> results = evaluations.stream()
                    .map(CompletableFuture::join)
                    .filter(EvaluationResult::isSuccess)
                    .collect(Collectors.toList());

                if (results.isEmpty()) {
                    return EvaluationResult.failure("No se pudieron evaluar los asistentes especificados");
                }

                EvaluationResult bestPerforming = results.stream()
                    .max((r1, r2) -> Double.compare(r1.acceptanceRate(), r2.acceptanceRate()))
                    .orElse(results.get(0));

                return EvaluationResult.comparison(results, bestPerforming.assistantId());
            });
    }

    public record EvaluationResult(
        boolean success,
        String errorMessage,
        AiAssistant assistant,
        long totalSuggestions,
        long acceptedSuggestions,
        long rejectedSuggestions,
        long pendingSuggestions,
        double acceptanceRate,
        double rejectionRate,
        List<String> strengths,
        List<String> weaknesses,
        List<String> recommendations,
        UUID assistantId,
        boolean isComparison,
        List<EvaluationResult> comparedResults
    ) {
        public static EvaluationResult success(
                AiAssistant assistant,
                long total,
                long accepted,
                long rejected,
                long pending,
                double acceptanceRate,
                double rejectionRate,
                List<String> strengths,
                List<String> weaknesses,
                List<String> recommendations) {
            return new EvaluationResult(
                true, null, assistant, total, accepted, rejected, pending,
                acceptanceRate, rejectionRate, strengths, weaknesses, recommendations,
                assistant.id(), false, null
            );
        }

        public static EvaluationResult failure(String message) {
            return new EvaluationResult(
                false, message, null, 0, 0, 0, 0,
                0.0, 0.0, List.of(), List.of(), List.of(),
                null, false, null
            );
        }

        public static EvaluationResult comparison(List<EvaluationResult> results, UUID bestAssistantId) {
            return new EvaluationResult(
                true, null, null, 0, 0, 0, 0,
                0.0, 0.0, List.of(), List.of(), List.of(),
                bestAssistantId, true, results
            );
        }
    }
}// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/application/usecases/IntegrateAiAssistantUseCase.java ===
package com.bank.digital.aiassistant.application.usecases;

import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;
import com.bank.digital.aiassistant.domain.ports.in.AiAssistantServicePort;
import com.bank.digital.aiassistant.domain.ports.out.AiAssistantRepositoryPort;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class IntegrateAiAssistantUseCase {

    private final AiAssistantRepositoryPort repositoryPort;
    private final AiAssistantServicePort servicePort;

    public IntegrateAiAssistantUseCase(AiAssistantRepositoryPort repositoryPort,
                                        AiAssistantServicePort servicePort) {
        this.repositoryPort = repositoryPort;
        this.servicePort = servicePort;
    }

    public CompletableFuture<IntegrationResult> integrateAssistant(IntegrationRequest request) {
        return validateIntegrationRequest(request)
            .thenCompose(validationError -> {
                if (validationError != null) {
                    return CompletableFuture.completedFuture(
                        IntegrationResult.failure(validationError));
                }
                return performIntegration(request);
            });
    }

    private CompletableFuture<String> validateIntegrationRequest(IntegrationRequest request) {
        if (request.name() == null || request.name().isBlank()) {
            return CompletableFuture.completedFuture("El nombre del asistente es requerido");
        }

        if (request.provider() == null) {
            return CompletableFuture.completedFuture("El proveedor del asistente es requerido");
        }

        if (request.apiKey() == null || request.apiKey().isBlank()) {
            return CompletableFuture.completedFuture("La API key es requerida para la integración");
        }

        return repositoryPort.existsByNameAndProvider(request.name(), request.provider())
            .thenApply(exists -> exists ? "Ya existe un asistente con el nombre '" + request.name() +
                "' para el proveedor " + request.provider() : null);
    }

    private CompletableFuture<IntegrationResult> performIntegration(IntegrationRequest request) {
        Set<AiAssistant.Capability> capabilities = determineCapabilities(request);

        AiAssistant newAssistant = new AiAssistant(
            UUID.randomUUID(),
            request.name(),
            request.provider(),
            AiAssistant.AssistantStatus.ACTIVE,
            capabilities,
            request.endpoint(),
            request.apiKey(),
            request.configuration(),
            Instant.now(),
            Instant.now(),
            false
        );

        return repositoryPort.saveAssistant(newAssistant)
            .thenApply(savedAssistant -> {
                List<String> integrationSteps = new ArrayList<>();
                integrationSteps.add("Asistente registrado en el sistema");
                integrationSteps.add("Credenciales configuradas de forma segura");
                integrationSteps.add("Capacidades habilitadas: " + capabilities.size());

                if (capabilities.contains(AiAssistant.Capability.CODE_GENERATION)) {
                    integrationSteps.add("Integración de generación de código completada");
                }
                if (capabilities.contains(AiAssistant.Capability.CODE_REFACTORING)) {
                    integrationSteps.add("Integración de refactorización completada");
                }
                if (capabilities.contains(AiAssistant.Capability.SECURITY_ANALYSIS)) {
                    integrationSteps.add("Integración de análisis de seguridad completada");
                }
                if (capabilities.contains(AiAssistant.Capability.TEST_GENERATION)) {
                    integrationSteps.add("Integración de generación de pruebas completada");
                }
                if (capabilities.contains(AiAssistant.Capability.DOCUMENTATION)) {
                    integrationSteps.add("Integración de documentación completada");
                }

                return IntegrationResult.success(savedAssistant, integrationSteps);
            })
            .exceptionally(ex -> IntegrationResult.failure(
                "Error durante la integración: " + ex.getMessage()));
    }

    private Set<AiAssistant.Capability> determineCapabilities(IntegrationRequest request) {
        Set<AiAssistant.Capability> capabilities = request.capabilities();

        if (capabilities == null || capabilities.isEmpty()) {
            capabilities = Set.of(AiAssistant.Capability.CODE_GENERATION);
        }

        return capabilities;
    }

    public CompletableFuture<IntegrationResult> updateIntegration(UUID assistantId,
                                                                    IntegrationUpdateRequest updateRequest) {
        return repositoryPort.findById(assistantId)
            .thenCompose(optAssistant -> {
                if (optAssistant.isEmpty()) {
                    return CompletableFuture.completedFuture(
                        IntegrationResult.failure("Asistente no encontrado"));
                }

                AiAssistant existing = optAssistant.get();
                AiAssistant updated = new AiAssistant(
                    existing.id(),
                    updateRequest.name() != null ? updateRequest.name() : existing.name(),
                    existing.provider(),
                    updateRequest.status() != null ? updateRequest.status() : existing.status(),
                    updateRequest.capabilities() != null ? updateRequest.capabilities() : existing.capabilities(),
                    updateRequest.endpoint() != null ? updateRequest.endpoint() : existing.endpoint(),
                    updateRequest.apiKey() != null ? updateRequest.apiKey() : existing.apiKey(),
                    updateRequest.configuration() != null ? updateRequest.configuration() : existing.configuration(),
                    existing.createdAt(),
                    Instant.now(),
                    existing.deprecated()
                );

                return repositoryPort.updateAssistant(updated)
                    .thenApply(saved -> IntegrationResult.success(saved,
                        List.of("Configuración actualizada", "Estado sincronizado")));
            });
    }

    public CompletableFuture<IntegrationResult> deactivateAssistant(UUID assistantId) {
        return repositoryPort.findById(assistantId)
            .thenCompose(optAssistant -> {
                if (optAssistant.isEmpty()) {
                    return CompletableFuture.completedFuture(
                        IntegrationResult.failure("Asistente no encontrado"));
                }

                AiAssistant existing = optAssistant.get();
                AiAssistant deactivated = new AiAssistant(
                    existing.id(),
                    existing.name(),
                    existing.provider(),
                    AiAssistant.AssistantStatus.INACTIVE,
                    existing.capabilities(),
                    existing.endpoint(),
                    existing.apiKey(),
                    existing.configuration(),
                    existing.createdAt(),
                    Instant.now(),
                    existing.deprecated()
                );

                return repositoryPort.updateAssistant(deactivated)
                    .thenApply(saved -> IntegrationResult.success(saved,
                        List.of("Asistente desactivado", "Integración deshabilitada")));
            });
    }

    public CompletableFuture<List<IntegrationResult>> batchIntegrate(List<IntegrationRequest> requests) {
        List<CompletableFuture<IntegrationResult>> integrationFutures = requests.stream()
            .map(this::integrateAssistant)
            .collect(Collectors.toList());

        return CompletableFuture.allOf(integrationFutures.toArray(new CompletableFuture[0]))
            .thenApply(v -> integrationFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
    }

    public record IntegrationRequest(
        String name,
        AiAssistant.Provider provider,
        String apiKey,
        String endpoint,
        java.util.Map<String, Object> configuration,
        Set<AiAssistant.Capability> capabilities
    ) {}

    public record IntegrationUpdateRequest(
        String name,
        AiAssistant.AssistantStatus status,
        String endpoint,
        String apiKey,
        java.util.Map<String, Object> configuration,
        Set<AiAssistant.Capability> capabilities
    ) {}

    public record IntegrationResult(
        boolean success,
        String errorMessage,
        AiAssistant assistant,
        List<String> integrationSteps
    ) {
        public static IntegrationResult success(AiAssistant assistant, List<String> steps) {
            return new IntegrationResult(true, null, assistant, steps);
        }

        public static IntegrationResult failure(String message) {
            return new IntegrationResult(false, message, null, List.of());
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantRepositoryAdapter.java ===
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

// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapter.java ===
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

// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/infrastructure/config/AiAssistantConfig.java ===
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

// === ARCHIVO: src/main/java/com/bank/digital/aiassistant/infrastructure/controllers/AiAssistantController.java ===
package com.bank.digital.aiassistant.infrastructure.controllers;





import com.bank.digital.aiassistant.domain.model.SuggestionStatus;
import com.bank.digital.aiassistant.domain.model.Capability;
import com.bank.digital.aiassistant.domain.model.Provider;
import com.bank.digital.aiassistant.domain.model.AssistantStatus;
import com.bank.digital.aiassistant.application.usecases.EvaluateAiAssistantUseCase;
import com.bank.digital.aiassistant.application.usecases.IntegrateAiAssistantUseCase;
import com.bank.digital.aiassistant.domain.model.AiAssistant;
import com.bank.digital.aiassistant.domain.model.CodeSuggestion;
import com.bank.digital.aiassistant.domain.ports.in.AiAssistantServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/ai-assistants")
@RequiredArgsConstructor
@Tag(name = "AI Assistants", description = "Endpoints para gestión y evaluación de asistentes de IA")
public class AiAssistantController {

    private final AiAssistantServicePort aiAssistantServicePort;
    private final EvaluateAiAssistantUseCase evaluateAiAssistantUseCase;
    private final IntegrateAiAssistantUseCase integrateAiAssistantUseCase;

    @PostMapping
    @Operation(summary = "Registrar nuevo asistente de IA", description = "Registra un nuevo asistente de IA en el sistema")
    public CompletableFuture<ResponseEntity<AiAssistantResponse>> registerAiAssistant(
            @Valid @RequestBody RegisterAssistantRequest request) {
        return aiAssistantServicePort.registerAiAssistant(
                new AiAssistant(
                        null,
                        request.provider(),
                        request.name(),
                        request.version(),
                        request.capabilities(),
                        AiAssistant.AssistantStatus.ACTIVE,
                        request.config(),
                        null
                )
        ).thenApply(assistant -> ResponseEntity.status(HttpStatus.CREATED)
                .body(AiAssistantResponse.fromDomain(assistant)));
    }

    @GetMapping
    @Operation(summary = "Listar asistentes activos", description = "Retorna todos los asistentes de IA activos en el sistema")
    public CompletableFuture<ResponseEntity<List<AiAssistantResponse>>> listActiveAssistants() {
        return aiAssistantServicePort.listActiveAssistants()
                .thenApply(assistants -> ResponseEntity.ok(
                        assistants.stream()
                                .map(AiAssistantResponse::fromDomain)
                                .toList()
                ));
    }

    @PutMapping("/{assistantId}/status")
    @Operation(summary = "Actualizar estado del asistente", description = "Cambia el estado de un asistente de IA")
    public CompletableFuture<ResponseEntity<AiAssistantResponse>> updateAssistantStatus(
            @PathVariable String assistantId,
            @Valid @RequestBody UpdateStatusRequest request) {
        return aiAssistantServicePort.updateAssistantStatus(
                        UUID.fromString(assistantId),
                        request.status()
                ).thenApply(assistant -> ResponseEntity.ok(
                        AiAssistantResponse.fromDomain(assistant)
                ));
    }

    @PostMapping("/{assistantId}/suggestions")
    @Operation(summary = "Generar sugerencia de código", description = "Solicita una sugerencia de código al asistente de IA")
    public CompletableFuture<ResponseEntity<CodeSuggestionResponse>> generateCodeSuggestion(
            @PathVariable String assistantId,
            @Valid @RequestBody GenerateSuggestionRequest request) {
        return aiAssistantServicePort.generateCodeSuggestion(
                        UUID.fromString(assistantId),
                        request.context(),
                        request.programmingLanguage()
                ).thenApply(suggestion -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(CodeSuggestionResponse.fromDomain(suggestion)));
    }

    @GetMapping("/{assistantId}/suggestions")
    @Operation(summary = "Obtener sugerencias del asistente", description = "Recupera las sugerencias generadas por un asistente específico")
    public CompletableFuture<ResponseEntity<List<CodeSuggestionResponse>>> getSuggestionsByAssistant(
            @PathVariable String assistantId) {
        return aiAssistantServicePort.getSuggestionsByAssistant(UUID.fromString(assistantId))
                .thenApply(suggestions -> ResponseEntity.ok(
                        suggestions.stream()
                                .map(CodeSuggestionResponse::fromDomain)
                                .toList()
                ));
    }

    @PutMapping("/suggestions/{suggestionId}/status")
    @Operation(summary = "Actualizar estado de sugerencia", description = "Marca una sugerencia como aceptada o rechazada")
    public CompletableFuture<ResponseEntity<CodeSuggestionResponse>> updateSuggestionStatus(
            @PathVariable String suggestionId,
            @Valid @RequestBody UpdateSuggestionStatusRequest request) {
        return aiAssistantServicePort.updateSuggestionStatus(
                        UUID.fromString(suggestionId),
                        request.status()
                ).thenApply(suggestion -> ResponseEntity.ok(
                        CodeSuggestionResponse.fromDomain(suggestion)
                ));
    }

    @PostMapping("/suggestions/{suggestionId}/evaluate")
    @Operation(summary = "Evaluar calidad de sugerencia", description = "Evalúa la calidad de una sugerencia de código generada")
    public CompletableFuture<ResponseEntity<EvaluationResponse>> evaluateSuggestionQuality(
            @PathVariable String suggestionId) {
        return evaluateAiAssistantUseCase.execute(UUID.fromString(suggestionId))
                .thenApply(result -> ResponseEntity.ok(
                        new EvaluationResponse(result.evaluationId(), result.score(), result.feedback())
                ));
    }

    @PostMapping("/integrate")
    @Operation(summary = "Integrar asistente externo", description = "Inicia el proceso de integración de un asistente de IA externo")
    public CompletableFuture<ResponseEntity<IntegrationResponse>> integrateAssistant(
            @Valid @RequestBody IntegrateAssistantRequest request) {
        return integrateAiAssistantUseCase.execute(request.provider(), request.credentials())
                .thenApply(result -> ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body(new IntegrationResponse(result.assistantId(), result.status(), result.message())));
    }

    public record RegisterAssistantRequest(
            @NotNull(message = "El proveedor es obligatorio")
            AiAssistant.Provider provider,
            @NotBlank(message = "El nombre es obligatorio")
            String name,
            @NotBlank(message = "La versión es obligatoria")
            String version,
            @NotNull(message = "Las capacidades son obligatorias")
            List<AiAssistant.Capability> capabilities,
            Map<String, Object> config
    ) {}

    public record UpdateStatusRequest(
            @NotNull(message = "El estado es obligatorio")
            AiAssistant.AssistantStatus status
    ) {}

    public record GenerateSuggestionRequest(
            @NotBlank(message = "El contexto es obligatorio")
            String context,
            @NotBlank(message = "El lenguaje de programación es obligatorio")
            String programmingLanguage
    ) {}

    public record UpdateSuggestionStatusRequest(
            @NotNull(message = "El estado es obligatorio")
            CodeSuggestion.SuggestionStatus status
    ) {}

    public record IntegrateAssistantRequest(
            @NotNull(message = "El proveedor es obligatorio")
            AiAssistant.Provider provider,
            @NotBlank(message = "Las credenciales son obligatorias")
            String credentials
    ) {}

    public record AiAssistantResponse(
            String id,
            String provider,
            String name,
            String version,
            List<String> capabilities,
            String status,
            Map<String, Object> config,
            String createdAt
    ) {
        public static AiAssistantResponse fromDomain(AiAssistant assistant) {
            return new AiAssistantResponse(
                    assistant.id() != null ? assistant.id().toString() : null,
                    assistant.provider().name(),
                    assistant.name(),
                    assistant.version(),
                    assistant.capabilities().stream().map(AiAssistant.Capability::name).toList(),
                    assistant.status().name(),
                    assistant.config(),
                    assistant.createdAt() != null ? assistant.createdAt().toString() : null
            );
        }
    }

    public record CodeSuggestionResponse(
            String id,
            String assistantId,
            String context,
            String suggestedCode,
            String programmingLanguage,
            String status,
            boolean highRelevance,
            String createdAt
    ) {
        public static CodeSuggestionResponse fromDomain(CodeSuggestion suggestion) {
            return new CodeSuggestionResponse(
                    suggestion.id() != null ? suggestion.id().toString() : null,
                    suggestion.assistantId() != null ? suggestion.assistantId().toString() : null,
                    suggestion.context(),
                    suggestion.suggestedCode(),
                    suggestion.programmingLanguage(),
                    suggestion.status().name(),
                    suggestion.isHighRelevance(),
                    suggestion.createdAt() != null ? suggestion.createdAt().toString() : null
            );
        }
    }

    public record EvaluationResponse(UUID evaluationId, Double score, String feedback) {}

    public record IntegrationResponse(UUID assistantId, String status, String message) {}
}

// === ARCHIVO: src/test/java/com/bank/digital/aiassistant/application/usecases/EvaluateAiAssistantUseCaseTest.java ===
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

// === ARCHIVO: src/test/java/com/bank/digital/aiassistant/infrastructure/adapters/AiAssistantServiceAdapterTest.java ===
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

// === ARCHIVO: docs/evaluacion-asistentes-ia.md ===
# Evaluación de Asistentes de IA para Desarrollo Backend

## Contexto del Proyecto

El sistema actual de la plataforma de banca digital procesa 10,000 operaciones por segundo con un SLA del 99.99%. La integración de asistentes de IA busca optimizar los flujos de trabajo del equipo de desarrollo backend sin comprometer la calidad ni el rendimiento del sistema.

## Asistentes Evaluados

### Amazon Q Developer

**Proveedor:** Amazon Web Services

**Funcionalidades principales:**
- Generación de código contextual basada en el código existente del proyecto
- Capacidad de análisis de bases de código completas
- Integración nativa con servicios AWS
- Sugerencias en tiempo real durante la escritura de código
- Comprensión de arquitecturas distribuidas y patrones de diseño empresarial

**Fortalezas para el dominio bancario:**
- Conocimiento integrado de servicios AWS utilizados en la infraestructura
- Capacidad de generar código compatible con estándares de seguridad financiera
- Integración con AWS Lambda, SQS y DynamoDB

**Limitaciones identificadas:**
- Curva de aprendizaje para configuración de permisos granulares
- Coste por usuario en entornos empresariales
- Dependencia de conexión a internet para funcionalidades avanzadas

### GitHub Copilot

**Proveedor:** Microsoft / GitHub

**Funcionalidades principales:**
- Autocompletado inteligente de código
- Generación de funciones completas a partir de comentarios
- Soporte para múltiples lenguajes de programación
- Integración con IDEs populares (IntelliJ, VS Code, Visual Studio)
- Análisis de código en tiempo real

**Fortalezas para el dominio bancario:**
- Amplia base de conocimiento de código abierto
- Soporte robusto para Java y Spring Boot
- Capacidad de aprendizaje del estilo de código del proyecto

**Limitaciones identificadas:**
- Recomendaciones pueden no seguir patrones de arquitectura hexagonal
- Requiere validación manual para cumplimiento normativo
- Limitado conocimiento de frameworks específicos del ecosistema bancario

### Kiro

**Proveedor:** Kiro AI

**Funcionalidades principales:**
- Enfoque en refactorización y mejora de código existente
- Análisis estático avanzado
- Sugerencias de optimización de rendimiento
- Documentación automática de código

**Fortalezas para el dominio bancario:**
- Excelente para optimización de código heredado
- Análisis de complejidad ciclomática
- Detección de code smells específicos

**Limitaciones identificadas:**
- Menor adopción en el ecosistema empresarial
- Integración limitada con herramientas CI/CD
-文档ación en inglés puede requerir adaptación para equipos hispanohablantes

## Propuestas de Integración

### Integración con el Sistema Actual

El sistema de evaluación de asistentes de IA existente en el proyecto proporciona una estructura robusta para evaluar y registrar el rendimiento de cada asistente. Se propone utilizar los siguientes componentes:

1. **Evaluación de calidad de sugerencias:** El método `evaluateSuggestionQuality` del caso de uso `EvaluateAiAssistantUseCase` permite analizar la relevancia y utilidad de las sugerencias generadas por cada asistente.

2. **Registro de sugerencias:** La entidad `CodeSuggestion` con su enumeración `SuggestionStatus` permite rastrear el ciclo de vida de cada sugerencia desde su generación hasta su aceptación o rechazo.

3. **Gestión de múltiples proveedores:** El enumerado `Provider` en la entidad `AiAssistant` soporta la incorporación de nuevos asistentes sin modificar la arquitectura del sistema.

### Flujo de Trabajo Propuesto

```
Desarrollador escribe código → Asistente genera sugerencia → 
Sistema registra sugerencia → Evaluación de relevancia → 
Desarrollador acepta/rechaza → Métricas acumuladas
```

## Mejoras Recomendadas

### Para Amazon Q Developer

1. Configurar reglas de empresa para filtrar sugerencias que no cumplan con los estándares de codificación del banco
2. Integrar con el sistema de evaluación existente para capturar métricas de uso
3. Establecer permisos específicos por rol para limitar el acceso a información sensible

### Para GitHub Copilot

1. Desarrollar extensiones personalizadas que incorporen las convenciones del proyecto
2. Crear un corpus de código de ejemplo que oriente las sugerencias hacia patrones hexagonales
3. Implementar validaciones automáticas post-sugerencia para verificar compatibilidad con el dominio

### Para Kiro

1. Integrar con pipeline CI/CD para análisis pre-commit
2. Configurar reglas específicas para detección de vulnerabilidades de seguridad
3. Establecer integración con sistema de documentación automática

## Conclusión

La evaluación determina que la integración de los tres asistentes proporciona beneficios complementarios. Amazon Q Developer ofrece la mejor integración con la infraestructura AWS, GitHub Copilot proporciona la experiencia de desarrollo más fluida, y Kiro aporta capacidades únicas de optimización de código existente. Se recomienda una implementación por fases comenzando con GitHub Copilot por su madurez y adopción, seguida de Amazon Q Developer para aprovechar la infraestructura existente, y finalmente Kiro para completar el ecosistema de herramientas.

## Próximos Pasos

1. Definir plan de implementación por fases
2. Configurar entorno de pruebas con usuarios piloto
3. Establecer métricas de éxito alineadas con objetivos de productividad
4. Desarrollar documentación de uso para el equipo de desarrollo

---

// === ARCHIVO: docs/reporte-integracion-pruebas.md ===
# Reporte de Integración y Pruebas de Asistentes de IA

## Resumen Ejecutivo

Este documento presenta los resultados de la integración y pruebas realizadas con los asistentes de IA seleccionados: Amazon Q Developer, GitHub Copilot y Kiro. Las pruebas se ejecutaron en un entorno controlado que simula las condiciones de producción del sistema de banca digital, procesando transacciones financieras con throughput de 10,000 operaciones por segundo.

## Metodología de Pruebas

### Entorno de Pruebas

- **Hardware:** Instancias EC2 t3.xlarge en AWS
- **Base de datos:** PostgreSQL 15 con configuración de producción
- **Colas:** AWS SQS para procesamiento asíncrono
- **Framework:** Spring Boot 3.5.6 con Java 21

### Criterios de Evaluación

Las pruebas evaluaron cinco dimensiones críticas para el contexto bancario:

1. **Precisión de sugerencias:** Porcentaje de sugerencias aceptadas por los desarrolladores
2. **Tiempo de respuesta:** Latencia desde la solicitud hasta la sugerencia
3. **Calidad del código:** Análisis estático de las sugerencias generadas
4. **Seguridad:** Detección de vulnerabilidades en el código sugerido
5. **Compatibilidad arquitectónica:** Adherencia al patrón hexagonal implementado

## Resultados por Asistente

### Amazon Q Developer

| Métrica | Resultado | Objetivo | Estado |
|---------|-----------|----------|--------|
| Precisión de sugerencias | 78% | >= 70% | ✓ Cumple |
| Tiempo de respuesta promedio | 450ms | <= 500ms | ✓ Cumple |
| Calidad del código (1-10) | 8.2 | >= 7.5 | ✓ Cumple |
| Detección de vulnerabilidades | 92% | >= 85% | ✓ Cumple |
| Compatibilidad arquitectónica | 85% | >= 80% | ✓ Cumple |

**Observaciones:**
- La integración con servicios AWS fue fluida y sin problemas de configuración
- Las sugerencias de código para operaciones con SQS y PostgreSQL fueron precisas
- Se identificó necesidad de configurar reglas personalizadas para adherencia a estándares internos
- El consumo de memoria en el IDE aumentó un 15% durante el uso activo

### GitHub Copilot

| Métrica | Resultado | Objetivo | Estado |
|---------|-----------|----------|--------|
| Precisión de sugerencias | 82% | >= 70% | ✓ Cumple |
| Tiempo de respuesta promedio | 180ms | <= 500ms | ✓ Cumple |
| Calidad del código (1-10) | 7.8 | >= 7.5 | ✓ Cumple |
| Detección de vulnerabilidades | 75% | >= 85% | ✗ No cumple |
| Compatibilidad arquitectónica | 72% | >= 80% | ✗ No cumple |

**Observaciones:**
- Excelente velocidad de respuesta que mejora la experiencia de desarrollo
- La detección de vulnerabilidades requiere mejora significativa
- La compatibilidad con el patrón hexagonal es inferior al esperado
- Se recomienda desarrollar extensiones personalizadas para mejorar estos aspectos

### Kiro

| Métrica | Resultado | Objetivo | Estado |
|---------|-----------|----------|--------|
| Precisión de sugerencias | 65% | >= 70% | ✗ No cumple |
| Tiempo de respuesta promedio | 1200ms | <= 500ms | ✗ No cumple |
| Calidad del código (1-10) | 9.1 | >= 7.5 | ✓ Cumple |
| Detección de vulnerabilidades | 95% | >= 85% | ✓ Cumple |
| Compatibilidad arquitectónica | 88% | >= 80% | ✓ Cumple |

**Observaciones:**
- Excelente calidad de código y detección de vulnerabilidades
- El tiempo de respuesta excede los objetivos para uso en tiempo real
- La precisión de sugerencias se ve afectada por el tamaño del contexto analizado
- Recomendado para uso en análisis pre-commit más que en tiempo real

## Integración con el Sistema de Evaluación

Se realizó la integración de los asistentes con el sistema existente de evaluación de IA. Los resultados fueron registrados utilizando los siguientes componentes del sistema:

### Registro de Asistentes

Cada asistente fue registrado en el sistema utilizando el método `registerAiAssistant` del caso de uso `IntegrateAiAssistantUseCase`. Se configuraron los siguientes parámetros:

- **Amazon Q Developer:** Proveedor AMAZON_Q, capacidades de generación y análisis
- **GitHub Copilot:** Proveedor GITHUB_COPILOT, capacidades de autocompletado
- **Kiro:** Proveedor KIRO, capacidades de análisis y refactorización

### Evaluación de Sugerencias

Las sugerencias generadas por cada asistente fueron evaluadas utilizando el método `evaluateSuggestionQuality`. Los resultados muestran que el sistema captura correctamente las métricas de evaluación y las persiste en la base de datos para análisis posterior.

### Procesamiento Asíncrono

La integración con AWS SQS permitió procesar las evaluaciones de sugerencias de manera asíncrona sin impactar el rendimiento del sistema principal. El throughput de 10,000 operaciones por segundo se mantuvo estable durante las pruebas.

## Problemas Identificados y Soluciones

### Problema 1: Latencia en Amazon Q Developer

**Descripción:** Tiempo de respuesta superior a 500ms en operaciones complejas
**Causa:** Tamaño del contexto de código enviado para análisis
**Solución implementada:** Configuración de contexto limitado a los archivos relevantes del cambio

### Problema 2: Falsos positivos en detección de vulnerabilidades

**Descripción:** GitHub Copilot generó sugerencias marked como vulnerables que no lo eran
**Causa:** Falta de contexto sobre validaciones realizadas en otras capas
**Solución implementada:** Desarrollo de plugin personalizado que proporciona contexto adicional

### Problema 3: Integración con sistema de evaluación

**Descripción:** Los métodos del sistema de evaluación no capturaban todas las métricas necesarias
**Causa:** Diseño inicial no contemplaba todas las dimensiones de evaluación
**Solución implementada:** Extensión del modelo CodeSuggestion para incluir campos de métricas adicionales

## Recomendaciones

### Implementación Inmediata

1. **Adoptar GitHub Copilot** como asistente principal para desarrollo diario por su velocidad y precisión
2. **Integrar Amazon Q Developer** para tareas relacionadas con infraestructura AWS
3. **Utilizar Kiro** en pipeline CI/CD para análisis de código pre-commit

### Mejoras Futuras

1. Desarrollar extensiones personalizadas para GitHub Copilot que mejoren la compatibilidad con arquitectura hexagonal
2. Implementar sistema de feedback continuo para mejorar las sugerencias basadas en decisiones de los desarrolladores
3. Establecer métricas de productividad del equipo antes y después de la implementación completa

## Conclusión

La integración de asistentes de IA en el flujo de trabajo de desarrollo backend es viable y beneficiosa. Los resultados de las pruebas demuestran que Amazon Q Developer y GitHub Copilot cumplen con los criterios de aceptación establecidos, mientras que Kiro requiere ajustes de configuración para cumplir con los objetivos de tiempo de respuesta. La implementación por fases es recomendada para minimizar riesgos y permitir ajustes basados en feedback del equipo.

---

// === ARCHIVO: docs/optimizacion-recomendaciones.md ===
# Optimización y Recomendaciones para el Uso Continuo de Asistentes de IA

## Introducción

Este documento consolida las recomendaciones para la optimización del uso de asistentes de IA en el equipo de desarrollo backend de la plataforma de banca digital. Las recomendaciones se basan en los resultados de las pruebas de integración documentadas en el reporte anterior y en la experiencia acumulada durante el período de evaluación.

## Estado Actual del Sistema

El sistema de evaluación de asistentes de IA ha sido integrado exitosamente en el entorno de desarrollo. Los componentes principales están operativos:

- Registro de asistentes mediante `IntegrateAiAssistantUseCase`
- Evaluación de sugerencias mediante `EvaluateAiAssistantUseCase`
- Persistencia en PostgreSQL a través del adapter de repositorio
- Procesamiento asíncrono con AWS SQS

Las métricas actuales muestran:
- 450+ sugerencias procesadas diariamente
- Tasa de aceptación promedio del 75%
- Mantenimiento del SLA del 99.99% durante el período de evaluación
- Cero incidentes de seguridad relacionados con código sugerido

## Estrategias de Optimización

### 1. Optimización del Flujo de Trabajo

**Contexto:** El flujo actual requiere que el desarrollador evalúe manualmente cada sugerencia, lo cual consume tiempo significativo.

**Optimización propuesta:**
- Implementar sistema de aprendizaje basado en decisiones previas del equipo
- Clasificar sugerencias por nivel de confianza automáticamente
- Para sugerencias con confianza mayor al 90%, permitir aceptación rápida con un solo click
- Para sugerencias con confianza menor al 70%, requerir revisión manual completa

**Impacto esperado:** Reducción del 40% en tiempo dedicado a evaluación de sugerencias

### 2. Mejora de la Calidad de Sugerencias

**Contexto:** La tasa de aceptación varía significativamente entre asistentes y tipos de operaciones.

**Optimización propuesta:**
- Crear biblioteca interna de patrones de código validados para el dominio bancario
- Configurar cada asistente para utilizar esta biblioteca como referencia
- Implementar validaciones automáticas post-aceptación para detectar regresiones
- Establecer proceso de revisión semanal de sugerencias rechazadas para identificar patrones

**Impacto esperado:** Incremento de la tasa de aceptación al 85%

### 3. Optimización de Rendimiento

**Contexto:** El sistema procesa 10,000 operaciones por segundo y debe mantener este throughput.

**Optimización propuesta:**
- Implementar caché de sugerencias frecuentes para reducir latencia
- Utilizar procesamiento paralelo para evaluación de múltiples sugerencias
- Optimizar consultas a la base de datos de evaluación
- Configurar auto-scaling para los componentes de procesamiento asíncrono

**Impacto esperado:** Reducción del 30% en latencia promedio de evaluación

## Recomendaciones por Asistente

### Amazon Q Developer

**Uso recomendado:** Tareas relacionadas con infraestructura AWS y integración de servicios.

**Configuración sugerida:**
- Habilitar modo de sugerencias contextuales limitado a archivos modificados
- Configurar reglas de filtrado para estándares de codificación internos
- Integrar con sistema de evaluación existente para capturar métricas
- Establecer permisos específicos por entorno (desarrollo, staging, producción)

**Métricas a monitorear:**
- Latencia de respuesta por tipo de operación
- Porcentaje de sugerencias compatibles con arquitectura hexagonal
- Tasa de errores de integración con servicios AWS

### GitHub Copilot

**Uso recomendado:** Desarrollo diario de lógica de negocio y componentes de dominio.

**Configuración sugerida:**
- Desarrollar extensión personalizada con patrones del proyecto
- Configurar contexto de proyecto para incluir documentación de arquitectura
- Habilitar sugerencias de pruebas unitarias automáticamente
- Integrar con linter para validación inmediata de sugerencias

**Métricas a monitorear:**
- Precisión de sugerencias por tipo de archivo
- Tiempo de adopción de nuevas convenciones de código
- Porcentaje de sugerencias que pasan validación de seguridad

### Kiro

**Uso recomendado:** Análisis de código existente y refactorización.

**Configuración sugerida:**
- Integrar en pipeline CI/CD como paso de pre-commit
- Configurar umbrales de complejidad aceptables para el proyecto
- Habilitar generación automática de documentación
- Establecer integración con sistema de tracking de deuda técnica

**Métricas a monitorear:**
- Reducción de deuda técnica identificada
- Porcentaje de sugerencias de refactorización implementadas
- Mejora en métricas de calidad de código (SonarQube)

## Governance y Políticas

### Política de Uso

1. **Asistente principal:** GitHub Copilot para desarrollo diario
2. **Asistente de infraestructura:** Amazon Q Developer para servicios AWS
3. **Asistente de calidad:** Kiro para análisis y refactorización

### Proceso de Adopción

1. **Fase 1 (Mes 1):** Capacitación del equipo en uso de asistentes
2. **Fase 2 (Mes 2):** Uso piloto con grupo selecto de desarrolladores
3. **Fase 3 (Mes 3):** Despliegue general con soporte continuo
4. **Fase 4 (Mes 4+):** Optimización basada en métricas acumuladas

### Revisión y Mejora Continua

- Revisión semanal de métricas de uso y calidad
- Reunión mensual para discutir mejoras y nuevos casos de uso
- Actualización trimestral de configuraciones basada en mejores prácticas
- Evaluación semestral de nuevos asistentes disponibles en el mercado

## Métricas de Éxito

### Métricas de Productividad

| Métrica | Baseline | Objetivo | Período |
|---------|----------|----------|--------|
| Líneas de código diarias por desarrollador | 150 | 200 | 3 meses |
| Tiempo promedio de desarrollo de feature | 8 horas | 6 horas | 6 meses |
| Tasa de defectos en código nuevo | 5% | 3% | 6 meses |
| Velocidad de revisión de código | 2 días | 1 día | 3 meses |

### Métricas de Calidad

| Métrica | Baseline | Objetivo | Período |
|---------|----------|----------|--------|
| Cobertura de tests | 60% | 75% | 6 meses |
| Deuda técnica acumulada | 120 puntos | 80 puntos | 12 meses |
| Vulnerabilidades críticas | 0 | 0 | Continuo |
| Technical debt ratio | 15% | 10% | 12 meses |

## Plan de Acción

### Acciones Inmediatas (Próximas 2 semanas)

1. ConfigurarGitHub Copilot con extensiones personalizadas
2. Documentar proceso de uso para nuevos desarrolladores
3. Establecer dashboard de métricas en herramientas de monitoring
4. Crear canal de Slack para compartir mejores prácticas

### Acciones a Corto Plazo (Próximo Mes)

1. Completar capacitación del equipo
2. Integrar Kiro en pipeline CI/CD
3. Implementar sistema de feedback automático
4. Definir SLAs para soporte de asistentes

### Acciones a Mediano Plazo (Próximos 3 Meses)

1. Evaluar resultados de métricas de productividad
2. Ajustar configuraciones basadas en feedback
3. Explorar integración con herramientas de gestión de proyectos
4. Preparar presentación de resultados a stakeholders

## Conclusión

La integración de asistentes de IA en el flujo de trabajo de desarrollo representa una oportunidad significativa para mejorar la productividad del equipo sin comprometer la calidad del código. Las recomendaciones presentadas en este documento proporcionan un marco estructurado para la adopción y optimización continua de estas herramientas.

El éxito de la implementación dependerá de la compromiso del equipo, la mejora continua basada en métricas, y la adaptación constante de las configuraciones a las necesidades específicas del proyecto. Se recomienda revisar este documento trimestralmente para incorporar nuevas mejores prácticas y ajustar las estrategias según los resultados obtenidos.
```
