# 📦 Sistema de Gestión de Envíos y Rastreo de Paquetes

Sistema de microservicios reactivos desarrollado con **Spring WebFlux** y **Spring Cloud Gateway** para la gestión de envíos y rastreo de paquetes.

## 🏗️ Arquitectura

El sistema está compuesto por:

- **API Gateway** (Puerto 8080) - Spring Cloud Gateway
- **Microservicio de Envíos** (Puerto 8081) - Gestión de envíos
- **Microservicio de Paquetes** (Puerto 8082) - Rastreo de paquetes

### Diagrama de Arquitectura

```
                    ┌─────────────────┐
                    │   API Gateway   │
                    │   (Port 8080)   │
                    └────────┬────────┘
                             │
                ┌────────────┴────────────┐
                │                         │
        ┌───────▼────────┐        ┌──────▼─────────┐
        │   Shipments    │        │   Packages     │
        │   Service      │        │   Service      │
        │  (Port 8081)   │        │  (Port 8082)   │
        └────────────────┘        └────────────────┘
```

## 🚀 Inicio Rápido (< 5 minutos)

### Prerrequisitos

- Java 17 o superior
- Maven 3.8+
- Postman (para pruebas)

### Ejecución Local

#### 1. Clonar el repositorio

```bash
git clone <tu-repositorio-url>
cd parcial
```

#### 2. Iniciar los servicios (en terminales separadas)

**Terminal 1 - Microservicio de Envíos:**
```bash
cd ms-shipments
mvn clean spring-boot:run
```

**Terminal 2 - Microservicio de Paquetes:**
```bash
cd ms-packages
mvn clean spring-boot:run
```

**Terminal 3 - API Gateway:**
```bash
cd gateway
mvn clean spring-boot:run
```

#### 3. Verificar que los servicios están activos

- Gateway: http://localhost:8080/actuator/health
- Shipments: http://localhost:8081/actuator/health
- Packages: http://localhost:8082/actuator/health

#### 4. Importar colección de Postman

Importa el archivo `Gestion_Envios_Rastreo.postman_collection.json` en Postman.

## 📋 Modelos de Datos

### Shipment (Envío)

```json
{
  "id": "string",
  "trackingNumber": "string",
  "origin": "string",
  "destination": "string",
  "senderName": "string",
  "recipientName": "string",
  "recipientAddress": "string",
  "weight": "number",
  "status": "PENDING | IN_TRANSIT | DELIVERED | CANCELLED",
  "createdAt": "datetime",
  "updatedAt": "datetime"
}
```

### Package (Paquete)

```json
{
  "id": "string",
  "trackingNumber": "string",
  "description": "string",
  "currentLocation": "string",
  "lastCheckpoint": "string",
  "estimatedDeliveryDays": "number",
  "trackingStatus": "REGISTERED | IN_WAREHOUSE | OUT_FOR_DELIVERY | DELIVERED | LOST",
  "lastUpdate": "datetime",
  "notes": "string"
}
```

## 🔌 Endpoints API

### Microservicio de Envíos

| Método | Endpoint | Descripción | Código HTTP |
|--------|----------|-------------|-------------|
| GET | `/api/shipments` | Listar todos los envíos | 200 OK |
| GET | `/api/shipments/{id}` | Obtener envío por ID | 200 OK / 404 Not Found |
| POST | `/api/shipments` | Crear nuevo envío | 201 Created |
| PUT | `/api/shipments/{id}` | Actualizar envío | 200 OK / 404 Not Found |
| DELETE | `/api/shipments/{id}` | Eliminar envío | 204 No Content / 404 Not Found |

### Microservicio de Paquetes

| Método | Endpoint | Descripción | Código HTTP |
|--------|----------|-------------|-------------|
| GET | `/api/packages` | Listar todos los paquetes | 200 OK |
| GET | `/api/packages/{id}` | Obtener paquete por ID | 200 OK / 404 Not Found |
| POST | `/api/packages` | Crear nuevo paquete | 201 Created |
| PUT | `/api/packages/{id}` | Actualizar paquete | 200 OK / 404 Not Found |
| DELETE | `/api/packages/{id}` | Eliminar paquete | 204 No Content / 404 Not Found |

### Acceso a través del Gateway

Todos los endpoints están disponibles a través del Gateway en el puerto 8080:

- `http://localhost:8080/api/shipments/**`
- `http://localhost:8080/api/packages/**`

## 📝 Ejemplos de Uso

### Crear un Envío (POST)

```bash
curl -X POST http://localhost:8080/api/shipments \
  -H "Content-Type: application/json" \
  -d '{
    "trackingNumber": "TRK003",
    "origin": "Tijuana",
    "destination": "Cancún",
    "senderName": "Roberto Sánchez",
    "recipientName": "Laura Fernández",
    "recipientAddress": "Blvd. Kukulcán Km 12.5",
    "weight": 8.5,
    "status": "PENDING"
  }'
```

### Listar Paquetes (GET)

```bash
curl http://localhost:8080/api/packages
```

### Actualizar Paquete (PUT)

```bash
curl -X PUT http://localhost:8080/api/packages/1 \
  -H "Content-Type: application/json" \
  -d '{
    "trackingNumber": "TRK001",
    "description": "Electrónicos - Laptop Dell",
    "currentLocation": "En ruta de entrega final",
    "lastCheckpoint": "Centro de Distribución Guadalajara",
    "estimatedDeliveryDays": 0.5,
    "trackingStatus": "OUT_FOR_DELIVERY",
    "notes": "Paquete frágil"
  }'
```

### Eliminar Envío (DELETE)

```bash
curl -X DELETE http://localhost:8080/api/shipments/2
```

## ✅ Validaciones

Ambos microservicios implementan validación usando Bean Validation:

- Campos obligatorios: `@NotBlank`, `@NotNull`
- Valores positivos: `@Positive`
- Respuestas de error con formato JSON estructurado

### Ejemplo de Error de Validación

```json
{
  "timestamp": "2025-10-14T10:30:00",
  "status": 400,
  "message": "Error de validación",
  "errors": {
    "trackingNumber": "El número de rastreo es obligatorio",
    "weight": "El peso debe ser positivo"
  }
}
```

## 🐳 Ejecución con Docker (Opcional)

### Construcción y ejecución

```bash
# Construir las imágenes
docker-compose build

# Iniciar los servicios
docker-compose up -d

# Ver logs
docker-compose logs -f

# Detener los servicios
docker-compose down
```

Los servicios estarán disponibles en los mismos puertos (8080, 8081, 8082).

## 🧪 Pruebas con Postman

La colección de Postman incluye:

### Variables de Entorno

- `gateway_url`: http://localhost:8080
- `shipments_url`: http://localhost:8081
- `packages_url`: http://localhost:8082

### Carpetas de Pruebas

1. **Shipments Service**: 6 requests (GET, POST, PUT, DELETE + validaciones)
2. **Packages Service**: 6 requests (GET, POST, PUT, DELETE + validaciones)
3. **Health Checks**: 3 requests (verificación de estado de servicios)

### Importar la Colección

1. Abrir Postman
2. Click en "Import"
3. Seleccionar `Gestion_Envios_Rastreo.postman_collection.json`
4. ¡Listo para probar!

## 📊 Capturas de Pantalla

### Gateway Routes
![Gateway](docs/images/gateway.png)

### Postman - GET Shipments
![GET Shipments](docs/images/get-shipments.png)

### Postman - POST Package
![POST Package](docs/images/post-package.png)

### Postman - Validation Error
![Validation Error](docs/images/validation-error.png)

## 🔧 Tecnologías Utilizadas

- **Spring Boot 3.2.0**
- **Spring WebFlux** (Programación reactiva)
- **Spring Cloud Gateway** (API Gateway)
- **Project Reactor** (Mono/Flux)
- **Bean Validation** (Validaciones)
- **Lombok** (Reducción de boilerplate)
- **Maven** (Gestión de dependencias)

## 📁 Estructura del Proyecto

```
parcial/
├── gateway/                          # API Gateway
│   ├── src/main/java/com/parcial/gateway/
│   │   └── GatewayApplication.java
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
│
├── ms-shipments/                     # Microservicio de Envíos
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
│   └── pom.xml
│
├── ms-packages/                      # Microservicio de Paquetes
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
│   └── pom.xml
│
├── Gestion_Envios_Rastreo.postman_collection.json
├── docker-compose.yml
└── README.md
```

## 🎯 Características Implementadas

- ✅ Arquitectura de microservicios con API Gateway
- ✅ Programación reactiva con WebFlux (Mono/Flux)
- ✅ Operaciones CRUD completas (GET, POST, PUT, DELETE)
- ✅ DTOs y separación de capas
- ✅ Validación de datos con @Valid
- ✅ Manejo global de errores
- ✅ Códigos HTTP apropiados
- ✅ Persistencia en memoria (ConcurrentHashMap)
- ✅ Datos de ejemplo precargados
- ✅ Configuración de CORS
- ✅ Endpoints de salud (Actuator)
- ✅ Colección completa de Postman
- ✅ Dockerización (opcional)

## 🐛 Troubleshooting

### Error: Puerto ya en uso

```bash
# macOS/Linux
lsof -ti:8080 | xargs kill -9
lsof -ti:8081 | xargs kill -9
lsof -ti:8082 | xargs kill -9
```

### Error: Maven no encontrado

```bash
# Verificar instalación
mvn --version

# Usar wrapper de Maven (si está disponible)
./mvnw clean spring-boot:run
```

### Gateway no enruta correctamente

Verificar que los microservicios estén ejecutándose:
```bash
curl http://localhost:8081/actuator/health
curl http://localhost:8082/actuator/health
```

## 👨‍💻 Autor

**Edson Gutiérrez**
- Tema: Gestión de envíos y rastreo de paquetes

## 📄 Licencia

Este proyecto es parte de un examen académico.

---

**Fecha de entrega**: 14 de octubre de 2025
**Framework**: Spring WebFlux + Spring Cloud Gateway
**Pruebas**: Postman
