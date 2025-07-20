# Protocolo MCP: Un Ejemplo con Spring Boot, Python, Gemini y LLaMA 3.2

![License: Apache 2.0](https://img.shields.io/badge/License-Apache_2.0-blue.svg)
![Java](https://img.shields.io/badge/SpringBoot-3.5-blue.svg)
![Python](https://img.shields.io/badge/Python-3.13-yellow.svg)
![Gemini](https://img.shields.io/badge/Gemini-DeepMind-blueviolet.svg)
![Llama3](https://img.shields.io/badge/Llama3.2-Ollama-ff69b4.svg)

Este repositorio contiene un ejemplo de implementacion de cliente-servidor MCP (Model context protocol) distribuido compuesto por:

- 🖥️ **Servidor MCP** desarrollado en Spring Boot
- 🤖 **Cliente MCP #1**: Spring Boot + [Llama 3.2](https://ollama.com/library/llama3) a través de Ollama
- 🐍 **Cliente MCP #2**: Python + [Gemini](https://deepmind.google/discover/gemini/) para procesamiento de lenguaje natural

## 📦 Estructura del proyecto
```
└── spring-ai-mcp
   ├── ai-mcp-client
   │  └── src
   │     └── main
   │        ├── java
   │        │  └── edu
   │        │     └── bo
   │        │        └── uyunicode
   │        │           └── ai
   │        │              └── mcp
   │        │                 └── client
   │        │                    ├── config
   │        │                    ├── constroller
   │        │                    ├── model
   │        │                    └── services
   │        └── resources
   ├── ai-mcp-client-gemini
   └── ai-mcp-server
      └── src
         └── main
            ├── java
            │  └── edu
            │     └── bo
            │        └── uyunicode
            │           └── ai
            │              └── mcp
            │                 └── server
            │                    ├── config
            │                    ├── entities
            │                    ├── repository
            │                    ├── services
            │                    └── tools
            └── resources
```
## 🚀 Características principales

- Comunicación bidireccional entre servidor y clientes a traves de MCP (Model context protocol)
- Integración de modelos de lenguaje (LLMs) para análisis, respuesta y procesamiento de texto

## 🛠️ Tecnologías utilizadas

| Componente           | Tecnología                       |
|----------------------|----------------------------------|
| Servidor MCP         | Spring Boot                      |
| Cliente MCP #1       | Spring Boot + Ollama + Llama 3.2 |
| Cliente MCP #2       | Python + Gemini                  |

## 🎯 Objetivo del proyecto

Demostrar cómo diferentes clientes MCP pueden interactuar con un servidor MCP y obtener las herramientas disponibles del servidor y entregarlas al modelo LLM para resolver tareas específicas de procesamiento de lenguaje natural.

## 📚 Cómo ejecutar el proyecto

1. **Servidor MCP**:
    #### ✅ Requisitos:
    - Java 21
    - Apache Maven
    - PostgreSQL configurado y corriendo
    - Credenciales de la base de datos definidas en `application.properties`
   ```bash
   cd spring-ai-mcp
   mvn clean install
   java -jar target/spring-ai-mcp-1.0-SNAPSHOT.jar
    ```
2. **:Cliente Spring Boot + Ollama:**
    #### ✅ Requisitos:
    - Instala y configura Ollama
    - descargar el modelo Llama 3.2 con:
        ```bash
        ollama pull llama3.2
        ```
    - PostgreSQL configurado y corriendo
    - Credenciales de la base de datos definidas en `application.properties`
    - Java 21
    - Apache Maven
    - Antes de correr actualizar la IP y puerto del servidor MCP en `application.yml`
    ```bash
    cd ai-mcp-client
    mvn clean install
    java -jar target/ai-mcp-client-1.0-SNAPSHOT.jar
    ```
3. **Cliente Python + Gemini:**
    #### ✅ Requisitos:
    - Python 3.13
    - uv (gestor de paquetes moderno para Python)
    - API Key activa para Gemini
    - IP y puerto del servidor MCP definidos en configuración `mcp-client-gemini.py`
    ```bash
    cd ai-mcp-client-gemini
    uv sync
    uv run 
    ```

## 📄 Licencia

Este proyecto está licenciado bajo la **Apache License 2.0** – January 2004.  
Puedes revisar los términos completos en [http://www.apache.org/licenses/](http://www.apache.org/licenses/).

## 🔗 Referencias

- [Introducción al Model Context Protocol (MCP)](https://modelcontextprotocol.io/introduction): Protocolo abierto que estandariza cómo las aplicaciones proporcionan contexto a los modelos de lenguaje.
- [Function Calling con Gemini API](https://ai.google.dev/gemini-api/docs/function-calling?example=meeting): Documentación oficial sobre cómo Gemini puede invocar funciones externas para realizar acciones reales.
- [MCP en Spring AI](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-overview.html): Guía de integración de MCP en aplicaciones Spring Boot usando Spring AI.
