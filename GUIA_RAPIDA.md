# 📋 Guía Rápida de Ejecución

## ⚡ Inicio Rápido (3 Pasos)

### Opción 1: Scripts Automatizados (Recomendado)

```bash
# 1. Iniciar todos los servicios
./start-services.sh

# 2. Probar endpoints
./test-endpoints.sh

# 3. Detener servicios
./stop-services.sh
```

### Opción 2: Manual

```bash
# Terminal 1 - Shipments Service
cd ms-shipments && mvn spring-boot:run

# Terminal 2 - Packages Service  
cd ms-packages && mvn spring-boot:run

# Terminal 3 - API Gateway
cd gateway && mvn spring-boot:run
```

### Opción 3: Docker

```bash
# Construir e iniciar
docker-compose up -d

# Ver logs
docker-compose logs -f

# Detener
docker-compose down
```

## 🧪 Pruebas con Postman

1. **Importar colección**: `Gestion_Envios_Rastreo.postman_collection.json`
2. **Importar entorno**: `Postman_Environment.json`
3. **Ejecutar pruebas** en el orden de las carpetas

## 🔗 URLs Principales

| Servicio | URL | Health Check |
|----------|-----|--------------|
| **Gateway** | http://localhost:8080 | http://localhost:8080/actuator/health |
| **Shipments** | http://localhost:8081 | http://localhost:8081/actuator/health |
| **Packages** | http://localhost:8082 | http://localhost:8082/actuator/health |

## 📝 Ejemplos de Prueba Rápida

### Listar Envíos
```bash
curl http://localhost:8080/api/shipments
```

### Crear Envío
```bash
curl -X POST http://localhost:8080/api/shipments \
  -H "Content-Type: application/json" \
  -d '{
    "trackingNumber": "TRK003",
    "origin": "CDMX",
    "destination": "GDL",
    "senderName": "Juan",
    "recipientName": "María",
    "recipientAddress": "Av. Principal 123",
    "weight": 5.5,
    "status": "PENDING"
  }'
```

### Listar Paquetes
```bash
curl http://localhost:8080/api/packages
```

### Obtener Paquete por ID
```bash
curl http://localhost:8080/api/packages/1
```

## ✅ Checklist de Entrega

- [x] Gateway enruta a ambos microservicios
- [x] CRUD completo (GET, POST, PUT, DELETE)
- [x] Validación y manejo de errores
- [x] Códigos HTTP correctos
- [x] Colección de Postman funcional
- [x] README con instrucciones claras
- [x] Dockerización opcional
- [x] WebFlux (programación reactiva)
- [x] DTOs y separación de capas

## 🎯 Arquitectura del Proyecto

```
┌─────────────────────────────────────────────────────┐
│                   API GATEWAY                        │
│                  (Port: 8080)                        │
│          Spring Cloud Gateway + WebFlux              │
└──────────────────┬──────────────────────────────────┘
                   │
        ┌──────────┴──────────┐
        │                     │
┌───────▼────────┐    ┌───────▼────────┐
│   SHIPMENTS    │    │    PACKAGES    │
│   SERVICE      │    │    SERVICE     │
│  (Port: 8081)  │    │  (Port: 8082)  │
│                │    │                │
│  - Envíos      │    │  - Rastreo     │
│  - CRUD        │    │  - CRUD        │
│  - WebFlux     │    │  - WebFlux     │
│  - Validación  │    │  - Validación  │
└────────────────┘    └────────────────┘
```

## 📊 Modelos de Datos

### Shipment (Envío)
- `id`: String (UUID)
- `trackingNumber`: String ✓
- `origin`: String ✓
- `destination`: String ✓
- `senderName`: String ✓
- `recipientName`: String ✓
- `recipientAddress`: String ✓
- `weight`: Double (positivo) ✓
- `status`: String ✓
- `createdAt`: LocalDateTime
- `updatedAt`: LocalDateTime

### Package (Paquete)
- `id`: String (UUID)
- `trackingNumber`: String ✓
- `description`: String ✓
- `currentLocation`: String ✓
- `lastCheckpoint`: String ✓
- `estimatedDeliveryDays`: Double (positivo) ✓
- `trackingStatus`: String ✓
- `lastUpdate`: LocalDateTime
- `notes`: String (opcional)

✓ = Campo validado

## 🔧 Tecnologías

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring WebFlux** (Reactor)
- **Spring Cloud Gateway**
- **Bean Validation**
- **Lombok**
- **Maven**
- **Docker** (opcional)

## 📦 Estructura de Carpetas

```
parcial/
├── gateway/                    # API Gateway (8080)
├── ms-shipments/              # Microservicio Envíos (8081)
├── ms-packages/               # Microservicio Paquetes (8082)
├── logs/                      # Logs de aplicación
├── *.postman_collection.json  # Colección Postman
├── Postman_Environment.json   # Variables de entorno
├── docker-compose.yml         # Docker Compose
├── start-services.sh          # Script inicio
├── stop-services.sh           # Script detención
├── test-endpoints.sh          # Script pruebas
└── README.md                  # Documentación completa
```

## 🐛 Solución de Problemas

### Puerto ocupado
```bash
lsof -ti:8080 | xargs kill -9
lsof -ti:8081 | xargs kill -9
lsof -ti:8082 | xargs kill -9
```

### Ver logs
```bash
tail -f logs/gateway.log
tail -f logs/shipments.log
tail -f logs/packages.log
```

### Verificar Java y Maven
```bash
java --version   # Debe ser 17+
mvn --version    # Debe ser 3.8+
```

## 📞 Contacto

**Autor**: Edson Gutiérrez  
**Tema**: Gestión de envíos y rastreo de paquetes  
**Framework**: Spring WebFlux + Spring Cloud Gateway  
**Fecha**: 14 de octubre de 2025
