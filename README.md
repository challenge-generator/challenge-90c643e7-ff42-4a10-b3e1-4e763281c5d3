# Optimización de Flujo de Trabajo con Asistentes de IA en Desarrollo Backend

En un entorno de desarrollo backend para una plataforma de banca digital, el equipo busca integrar y optimizar el uso de asistentes de IA como Amazon Q Dev, Kiro y Github Copilot para mejorar la productividad y la calidad del código. El sistema actual maneja transacciones financieras con un throughput de 10 000 operaciones por segundo y un SLA del 99.99%. El candidato deberá identificar puntos de integración para estos asistentes, evaluar su impacto y proponer mejoras.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | desarrollo |
| **Nivel** | senior-l2 |
| **Tipo** | mixed |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Evaluación de Asistentes de IA

**Objetivo:** Identificar las funcionalidades y beneficios de cada asistente de IA en el contexto del desarrollo backend.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Investiga y documenta las funcionalidades clave de Amazon Q Dev, Kiro y Github Copilot.
- Evalúa cómo cada asistente puede integrarse en el flujo de trabajo actual del equipo.
- Identifica posibles mejoras en productividad y calidad del código que cada asistente puede ofrecer.

**Entregable:** Documento de evaluación con propuestas de integración y mejoras.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la capacidad de cada asistente para sugerir código, detectar errores y mejorar la eficiencia del desarrollo.
- Evalúa la facilidad de integración de cada asistente en el entorno de desarrollo existente.

</details>

### Fase 2: Integración y Prueba de Asistentes

**Objetivo:** Integrar los asistentes de IA seleccionados en el entorno de desarrollo y realizar pruebas para evaluar su eficacia.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Selecciona los asistentes de IA que ofrecerán el mayor beneficio para el equipo.
- Integra los asistentes en el entorno de desarrollo siguiendo las mejores prácticas.
- Realiza pruebas para evaluar la eficacia de cada asistente en términos de productividad y calidad del código.

**Entregable:** Reporte de integración y prueba con resultados y recomendaciones.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la configuración óptima de cada asistente para maximizar su eficacia.
- Evalúa el impacto de cada asistente en el rendimiento y la calidad del código durante las pruebas.

</details>

### Fase 3: Optimización y Recomendaciones

**Objetivo:** Optimizar el uso de los asistentes de IA y proporcionar recomendaciones para su uso continuo en el equipo.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Analiza los resultados de las pruebas y propone optimizaciones para maximizar el beneficio de los asistentes de IA.
- Documenta recomendaciones para el uso continuo de los asistentes en el equipo, incluyendo mejores prácticas y posibles mejoras futuras.

**Entregable:** Documento de optimización y recomendaciones para el uso de asistentes de IA.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la retroalimentación del equipo y las mejoras potenciales para la integración de los asistentes.
- Evalúa la posibilidad de automatizar tareas adicionales con los asistentes de IA.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los asistentes de IA y cómo funcionan en el contexto del desarrollo backend?
- **paraQueSirve**: ¿Para qué sirven los asistentes de IA en el flujo de trabajo de desarrollo backend?
- **comoSeUsa**: ¿Cómo se integran y utilizan los asistentes de IA en el entorno de desarrollo?
- **erroresComunes**: ¿Cuáles son los errores comunes al integrar y usar asistentes de IA en el desarrollo backend?
- **queDecisionesImplica**: ¿Qué decisiones implica la integración y uso de asistentes de IA en el equipo de desarrollo?

## Criterios de Evaluacion

- Identificación y evaluación de las funcionalidades de los asistentes de IA.
- Integración efectiva de los asistentes en el entorno de desarrollo.
- Realización de pruebas para evaluar la eficacia de los asistentes.
- Propuesta de optimizaciones y recomendaciones para el uso continuo de los asistentes.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
