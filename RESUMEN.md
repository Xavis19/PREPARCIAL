# 📊 Resumen del Proyecto - Gestión de Envíos y Rastreo de Paquetes

## 🎯 Información General

- **Autor**: Edson Gutiérrez
- **Tema**: Gestión de envíos y rastreo de paquetes
- **Framework**: Spring WebFlux + Spring Cloud Gateway
- **Fecha**: 14 de octubre de 2025
- **Repositorio**: /Users/editsongutierreza/Downloads/parcial

---

## 📁 Estructura del Proyecto

```
parcial/
│
├── gateway/                              # API Gateway (Puerto 8080)
│   ├── src/main/java/com/parcial/gateway/
│   │   └── GatewayApplication.java
│   ├── src/main/resources/
│   │   ├── application.yml
│   │   └── application-docker.yml
│   ├── Dockerfile
│   └── pom.xml
│
├── ms-shipments/                         # Microservicio de Envíos (Puerto 8081)
│   ├── src/main/java/com/parcial/shipments/
│   │   ├── controller/
│   │   │   └── ShipmentController.java
│   │   ├── service/
│   │   │   └── ShipmentService.java
│   │   ├── repository/
│   │   │   └── ShipmentRepository.java
│   │   ├── model/
│   │   │   └── Shipment.java
│   │   ├── dto/
│   │   │   ├── ShipmentRequest.java
│   │   │   └── ShipmentResponse.java
│   │   ├── exception/
│   │   │   └── GlobalExceptionHandler.java
│   │   └── ShipmentsApplication.java
│   ├── src/main/resources/
│   │   └── application.yml
│   ├── Dockerfile
│   └── pom.xml
│
├── ms-packages/                          # Microservicio de Paquetes (Puerto 8082)
│   ├── src/main/java/com/parcial/packages/
│   │   ├── controller/
│   │   │   └── PackageController.java
│   │   ├── service/
│   │   │   └── PackageService.java
│   │   ├── repository/
│   │   │   └── PackageRepository.java
│   │   ├── model/
│   │   │   └── Package.java
│   │   ├── dto/
│   │   │   ├── PackageRequest.java
│   │   │   └── PackageResponse.java
│   │   ├── exception/
│   │   │   └── GlobalExceptionHandler.java
│   │   └── PackagesApplication.java
│   ├── src/main/resources/
│   │   └── application.yml
│   ├── Dockerfile
│   └── pom.xml
│
├── logs/                                 # Logs de aplicación
│   └── .gitkeep
│
├── Gestion_Envios_Rastreo.postman_collection.json
├── Postman_Environment.json
├── docker-compose.yml
├── start-services.sh
├── stop-services.sh
├── test-endpoints.sh
├── README.md
├── GUIA_RAPIDA.md
├── DEMOSTRACION.md
├── ESTRUCTURA_PROYECTO.txt
├── .gitignore
└── RESUMEN.md (este archivo)
```

---

## 🏗️ Arquitectura

### Componentes

1. **API Gateway** (Spring Cloud Gateway)
   - Puerto: 8080
   - Enruta peticiones a los microservicios
   - Configuración de CORS
   - Health checks con Actuator

2. **Microservicio de Envíos** (Spring WebFlux)
   - Puerto: 8081
   - CRUD completo de envíos
   - Validación de datos
   - Manejo de errores

3. **Microservicio de Paquetes** (Spring WebFlux)
   - Puerto: 8082
   - CRUD completo de rastreo
   - Validación de datos
   - Manejo de errores

### Flujo de Datos

```
Cliente → API Gateway (8080) → Microservicio (8081/8082) → Respuesta
```

---

## 📋 Endpoints Implementados

### Microservicio de Envíos (Shipments)

| Método | Endpoint | Descripción | HTTP Status |
|--------|----------|-------------|-------------|
| GET | `/api/shipments` | Listar todos | 200 OK |
| GET | `/api/shipments/{id}` | Obtener por ID | 200 OK / 404 |
| POST | `/api/shipments` | Crear nuevo | 201 Created |
| PUT | `/api/shipments/{id}` | Actualizar | 200 OK / 404 |
| DELETE | `/api/shipments/{id}` | Eliminar | 204 No Content / 404 |

### Microservicio de Paquetes (Packages)

| Método | Endpoint | Descripción | HTTP Status |
|--------|----------|-------------|-------------|
| GET | `/api/packages` | Listar todos | 200 OK |
| GET | `/api/packages/{id}` | Obtener por ID | 200 OK / 404 |
| POST | `/api/packages` | Crear nuevo | 201 Created |
| PUT | `/api/packages/{id}` | Actualizar | 200 OK / 404 |
| DELETE | `/api/packages/{id}` | Eliminar | 204 No Content / 404 |

---

## 🔧 Tecnologías Utilizadas

### Framework y Librerías

- **Spring Boot**: 3.2.0
- **Spring WebFlux**: Programación reactiva
- **Spring Cloud Gateway**: API Gateway
- **Project Reactor**: Mono/Flux
- **Bean Validation**: Validaciones (@Valid, @NotBlank, @Positive)
- **Lombok**: Reducción de boilerplate
- **Maven**: Gestión de dependencias
- **Java**: 17

### Infraestructura (Opcional)

- **Docker**: Containerización
- **Docker Compose**: Orquestación de contenedores

---

## 📊 Modelos de Datos

### Shipment (Envío)

```java
{
  "id": "string",                    // UUID generado
  "trackingNumber": "string",        // Número de rastreo
  "origin": "string",                // Ciudad de origen
  "destination": "string",           // Ciudad de destino
  "senderName": "string",           // Nombre del remitente
  "recipientName": "string",        // Nombre del destinatario
  "recipientAddress": "string",     // Dirección de entrega
  "weight": "number",               // Peso en kg (positivo)
  "status": "string",               // PENDING, IN_TRANSIT, DELIVERED, CANCELLED
  "createdAt": "datetime",          // Fecha de creación
  "updatedAt": "datetime"           // Última actualización
}
```

### Package (Paquete)

```java
{
  "id": "string",                    // UUID generado
  "trackingNumber": "string",        // Número de rastreo
  "description": "string",           // Descripción del paquete
  "currentLocation": "string",       // Ubicación actual
  "lastCheckpoint": "string",        // Último punto de control
  "estimatedDeliveryDays": "number", // Días estimados (positivo)
  "trackingStatus": "string",        // REGISTERED, IN_WAREHOUSE, OUT_FOR_DELIVERY, DELIVERED, LOST
  "lastUpdate": "datetime",          // Última actualización
  "notes": "string"                  // Notas adicionales
}
```

---

## ✅ Características Implementadas

### Requisitos Funcionales

- ✅ Arquitectura de microservicios
- ✅ API Gateway con enrutamiento
- ✅ Spring WebFlux (programación reactiva)
- ✅ Operaciones CRUD completas
- ✅ DTOs para request/response
- ✅ Validación de datos (@Valid)
- ✅ Manejo global de errores
- ✅ Códigos HTTP apropiados
- ✅ Persistencia en memoria (ConcurrentHashMap)
- ✅ Datos de ejemplo precargados

### Características Adicionales

- ✅ Scripts de inicio/detención automatizados
- ✅ Script de pruebas de endpoints
- ✅ Configuración Docker/Docker Compose
- ✅ Documentación completa (README, Guía Rápida, Demostración)
- ✅ Colección de Postman exportada
- ✅ Variables de entorno para Postman
- ✅ Health checks (Actuator)
- ✅ Configuración de CORS
- ✅ Logs organizados

---

## 🧪 Pruebas con Postman

### Archivos de Postman

1. **Gestion_Envios_Rastreo.postman_collection.json**
   - 15+ requests organizados en carpetas
   - Ejemplos de GET, POST, PUT, DELETE
   - Casos de error de validación
   - Health checks

2. **Postman_Environment.json**
   - Variables de entorno configuradas
   - URLs de Gateway y servicios
   - Listo para importar y usar

### Carpetas de Pruebas

1. **Shipments Service** (6 requests)
   - GET todos los envíos
   - GET envío por ID
   - POST crear envío
   - PUT actualizar envío
   - DELETE eliminar envío
   - POST error de validación

2. **Packages Service** (6 requests)
   - GET todos los paquetes
   - GET paquete por ID
   - POST crear paquete
   - PUT actualizar paquete
   - DELETE eliminar paquete
   - POST error de validación

3. **Health Checks** (3 requests)
   - Gateway health
   - Shipments service health
   - Packages service health

---

## 🚀 Instrucciones de Ejecución

### Inicio Rápido (< 5 minutos)

```bash
# 1. Navegar al directorio
cd /Users/editsongutierreza/Downloads/parcial

# 2. Iniciar servicios
./start-services.sh

# 3. Probar endpoints (opcional)
./test-endpoints.sh

# 4. Detener servicios
./stop-services.sh
```

### Ejecución Manual

**Terminal 1 - Shipments:**
```bash
cd ms-shipments && mvn spring-boot:run
```

**Terminal 2 - Packages:**
```bash
cd ms-packages && mvn spring-boot:run
```

**Terminal 3 - Gateway:**
```bash
cd gateway && mvn spring-boot:run
```

### Ejecución con Docker

```bash
# Construir e iniciar
docker-compose up -d

# Ver logs
docker-compose logs -f

# Detener
docker-compose down
```

---

## 📈 Criterios de Evaluación Cumplidos

### 1. Arquitectura y separación (25%)
- ✅ API Gateway implementado
- ✅ 2 microservicios independientes
- ✅ Enrutamiento correcto
- ✅ Separación clara de capas

### 2. Correctitud de endpoints (25%)
- ✅ Todos los CRUD implementados
- ✅ Códigos HTTP correctos
- ✅ Respuestas bien estructuradas
- ✅ Manejo de casos de error

### 3. Reactividad (20%)
- ✅ WebFlux en todos los servicios
- ✅ Uso correcto de Mono/Flux
- ✅ Controladores no bloqueantes
- ✅ Operaciones reactivas

### 4. Pruebas en Postman (20%)
- ✅ Colección completa
- ✅ Variables de entorno
- ✅ Casos de éxito y error
- ✅ Documentación en requests
- ✅ Reproducible al importar

### 5. Documentación/README (10%)
- ✅ README completo
- ✅ Instrucciones claras
- ✅ Descripción de modelos
- ✅ Ejemplos de uso
- ✅ Troubleshooting
- ✅ Guía de demostración

---

## 📝 Archivos de Documentación

1. **README.md** - Documentación principal completa
2. **GUIA_RAPIDA.md** - Inicio rápido y ejemplos
3. **DEMOSTRACION.md** - Guía paso a paso para el profesor
4. **RESUMEN.md** - Este archivo (resumen ejecutivo)
5. **ESTRUCTURA_PROYECTO.txt** - Árbol de directorios

---

## 🔗 URLs de Acceso

| Servicio | URL Base | Health Check |
|----------|----------|--------------|
| **API Gateway** | http://localhost:8080 | http://localhost:8080/actuator/health |
| **Shipments** | http://localhost:8081 | http://localhost:8081/actuator/health |
| **Packages** | http://localhost:8082 | http://localhost:8082/actuator/health |

### Endpoints principales vía Gateway

- `http://localhost:8080/api/shipments`
- `http://localhost:8080/api/packages`

---

## 🎯 Próximos Pasos para la Demostración

1. ✅ Compilar todos los proyectos
2. ✅ Iniciar los servicios
3. ✅ Importar colección de Postman
4. ✅ Verificar health checks
5. ✅ Probar todos los endpoints
6. ✅ Demostrar enrutamiento del Gateway
7. ✅ Mostrar validaciones y manejo de errores
8. ✅ Explicar características de WebFlux

---

## 📞 Información de Contacto

- **Estudiante**: Edson Gutiérrez
- **Tema Asignado**: Gestión de envíos y rastreo de paquetes
- **Framework**: Spring WebFlux + Spring Cloud Gateway
- **Fecha de Entrega**: 14 de octubre de 2025

---

## 📌 Notas Importantes

1. **Persistencia**: Implementada en memoria con ConcurrentHashMap
2. **Datos de prueba**: Precargados al iniciar cada servicio
3. **Puertos**: 8080 (Gateway), 8081 (Shipments), 8082 (Packages)
4. **Java Version**: 17+
5. **Maven Version**: 3.8+
6. **Docker**: Opcional, pero totalmente funcional

---

## ✨ Características Destacadas

- 🚀 Scripts automatizados para facilitar la ejecución
- 📦 Dockerización completa con Docker Compose
- 🧪 Colección de Postman exhaustiva
- 📚 Documentación detallada en múltiples niveles
- ✅ Validaciones robustas con mensajes claros
- 🔄 Programación reactiva end-to-end
- 🌐 API Gateway con enrutamiento inteligente
- 🛡️ Manejo de errores global y consistente

---

**Estado del Proyecto**: ✅ COMPLETADO Y LISTO PARA DEMOSTRACIÓN

**Última actualización**: 14 de octubre de 2025
