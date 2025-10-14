# 🎓 Guía de Demostración para el Profesor

## 📋 Requisitos de Demostración

Este documento contiene el orden de demostración para cumplir con los requisitos del parcial.

---

## 🚀 Paso 1: Iniciar los Servicios (2 minutos)

### Opción A: Usando Scripts (Recomendado)
```bash
cd /Users/editsongutierreza/Downloads/parcial
./start-services.sh
```

### Opción B: Manual
```bash
# Terminal 1
cd ms-shipments && mvn spring-boot:run

# Terminal 2  
cd ms-packages && mvn spring-boot:run

# Terminal 3
cd gateway && mvn spring-boot:run
```

**Esperar 30-45 segundos** para que todos los servicios inicien.

---

## 🔍 Paso 2: Verificar Health Checks (1 minuto)

### Verificar que todos los servicios estén activos:

```bash
# Gateway
curl http://localhost:8080/actuator/health

# Shipments Service
curl http://localhost:8081/actuator/health

# Packages Service
curl http://localhost:8082/actuator/health
```

**Resultado esperado**: Status "UP" en todos los servicios.

---

## 🌐 Paso 3: Demostrar API Gateway (3 minutos)

### Mostrar que el Gateway enruta correctamente:

#### 3.1 Ruta a Shipments Service
```bash
# A través del Gateway (puerto 8080)
curl http://localhost:8080/api/shipments

# Directamente al servicio (puerto 8081)
curl http://localhost:8081/api/shipments
```

#### 3.2 Ruta a Packages Service
```bash
# A través del Gateway (puerto 8080)
curl http://localhost:8080/api/packages

# Directamente al servicio (puerto 8082)
curl http://localhost:8082/api/packages
```

**Punto clave**: Mostrar que ambas URLs (Gateway y directa) retornan los mismos datos.

---

## 📦 Paso 4: Demostrar CRUD de Shipments (5 minutos)

### 4.1 GET - Listar todos los envíos
```bash
curl http://localhost:8080/api/shipments
```

### 4.2 GET - Obtener envío por ID
```bash
curl http://localhost:8080/api/shipments/1
```

### 4.3 POST - Crear nuevo envío
```bash
curl -X POST http://localhost:8080/api/shipments \
  -H "Content-Type: application/json" \
  -d '{
    "trackingNumber": "DEMO001",
    "origin": "Ciudad de México",
    "destination": "Guadalajara",
    "senderName": "Profesor Test",
    "recipientName": "Estudiante Demo",
    "recipientAddress": "Universidad Campus 123",
    "weight": 2.5,
    "status": "PENDING"
  }'
```

**Guardar el ID retornado** para los siguientes pasos.

### 4.4 PUT - Actualizar envío
```bash
# Usar el ID del paso anterior
curl -X PUT http://localhost:8080/api/shipments/[ID] \
  -H "Content-Type: application/json" \
  -d '{
    "trackingNumber": "DEMO001",
    "origin": "Ciudad de México",
    "destination": "Guadalajara",
    "senderName": "Profesor Test",
    "recipientName": "Estudiante Demo",
    "recipientAddress": "Universidad Campus 123",
    "weight": 2.5,
    "status": "IN_TRANSIT"
  }'
```

### 4.5 DELETE - Eliminar envío
```bash
curl -X DELETE http://localhost:8080/api/shipments/[ID]
```

### 4.6 Verificar eliminación
```bash
curl http://localhost:8080/api/shipments/[ID]
# Debe retornar 404 Not Found
```

---

## 📫 Paso 5: Demostrar CRUD de Packages (5 minutos)

### 5.1 GET - Listar todos los paquetes
```bash
curl http://localhost:8080/api/packages
```

### 5.2 GET - Obtener paquete por ID
```bash
curl http://localhost:8080/api/packages/1
```

### 5.3 POST - Crear nuevo paquete
```bash
curl -X POST http://localhost:8080/api/packages \
  -H "Content-Type: application/json" \
  -d '{
    "trackingNumber": "DEMO001",
    "description": "Paquete de demostración",
    "currentLocation": "Centro de Distribución CDMX",
    "lastCheckpoint": "Recepción inicial",
    "estimatedDeliveryDays": 2.0,
    "trackingStatus": "REGISTERED",
    "notes": "Demo para el profesor"
  }'
```

### 5.4 PUT - Actualizar paquete
```bash
curl -X PUT http://localhost:8080/api/packages/[ID] \
  -H "Content-Type: application/json" \
  -d '{
    "trackingNumber": "DEMO001",
    "description": "Paquete de demostración - Actualizado",
    "currentLocation": "En camino a destino",
    "lastCheckpoint": "Centro de Distribución GDL",
    "estimatedDeliveryDays": 1.0,
    "trackingStatus": "OUT_FOR_DELIVERY",
    "notes": "Demo para el profesor - Actualizado"
  }'
```

### 5.5 DELETE - Eliminar paquete
```bash
curl -X DELETE http://localhost:8080/api/packages/[ID]
```

---

## ✅ Paso 6: Demostrar Validaciones (2 minutos)

### 6.1 Error de validación - Campos vacíos
```bash
curl -X POST http://localhost:8080/api/shipments \
  -H "Content-Type: application/json" \
  -d '{
    "trackingNumber": "",
    "origin": "",
    "destination": "Destino",
    "senderName": "",
    "recipientName": "Nombre",
    "recipientAddress": "Dirección",
    "weight": -5,
    "status": "PENDING"
  }'
```

**Resultado esperado**: HTTP 400 con mensajes de error detallados.

### 6.2 Error 404 - Recurso no encontrado
```bash
curl http://localhost:8080/api/shipments/999999
```

**Resultado esperado**: HTTP 404 Not Found.

---

## 🧪 Paso 7: Demostrar Postman (5 minutos)

### 7.1 Importar colección
1. Abrir Postman
2. Import → File → `Gestion_Envios_Rastreo.postman_collection.json`
3. Import → File → `Postman_Environment.json`

### 7.2 Ejecutar pruebas
1. Seleccionar el entorno "Gestión de Envíos - Local"
2. Ejecutar carpeta "Shipments Service" (Run folder)
3. Ejecutar carpeta "Packages Service" (Run folder)
4. Ejecutar carpeta "Health Checks" (Run folder)

### 7.3 Mostrar ejemplos individuales
- GET con respuesta exitosa (200)
- POST con creación exitosa (201)
- PUT con actualización exitosa (200)
- DELETE con eliminación exitosa (204)
- POST con error de validación (400)

---

## 🎯 Paso 8: Mostrar Características de WebFlux (2 minutos)

### Abrir código fuente y mostrar:

#### 8.1 Controladores Reactivos
```java
// ms-shipments/src/main/java/com/parcial/shipments/controller/ShipmentController.java

@GetMapping
public Flux<ShipmentResponse> getAllShipments() {
    return service.getAllShipments();
}

@GetMapping("/{id}")
public Mono<ResponseEntity<ShipmentResponse>> getShipmentById(@PathVariable String id) {
    return service.getShipmentById(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
}
```

**Puntos clave**:
- Uso de `Mono` para operaciones únicas
- Uso de `Flux` para colecciones
- Programación no bloqueante

#### 8.2 Servicios Reactivos
```java
// ms-shipments/src/main/java/com/parcial/shipments/service/ShipmentService.java

public Mono<ShipmentResponse> createShipment(ShipmentRequest request) {
    // Operaciones reactivas
    return repository.save(shipment)
            .map(this::toResponse);
}
```

#### 8.3 Gateway Configuration
```yaml
# gateway/src/main/resources/application.yml

spring:
  cloud:
    gateway:
      routes:
        - id: shipments-service
          uri: http://localhost:8081
          predicates:
            - Path=/api/shipments/**
```

---

## 📊 Checklist de Evaluación

### Arquitectura y separación (25%)
- [x] API Gateway configurado
- [x] 2 microservicios independientes
- [x] Rutas correctamente configuradas
- [x] Separación clara de responsabilidades

### Correctitud de endpoints (25%)
- [x] GET - Listar todos
- [x] GET - Por ID
- [x] POST - Crear
- [x] PUT - Actualizar
- [x] DELETE - Eliminar
- [x] Códigos HTTP correctos (200, 201, 204, 400, 404)

### Reactividad (20%)
- [x] WebFlux en ambos microservicios
- [x] Uso de Mono y Flux
- [x] Controladores no bloqueantes
- [x] Servicios reactivos

### Pruebas en Postman (20%)
- [x] Colección completa
- [x] Variables de entorno
- [x] Ejemplos de todos los métodos
- [x] Casos de error
- [x] Reproducible al importar

### Documentación/README (10%)
- [x] README completo
- [x] Instrucciones de ejecución
- [x] Descripción de modelos
- [x] Ejemplos de uso
- [x] Troubleshooting

---

## 🎬 Tiempo Total Estimado: 20-25 minutos

1. Iniciar servicios: 2 min
2. Health checks: 1 min
3. Demostrar Gateway: 3 min
4. CRUD Shipments: 5 min
5. CRUD Packages: 5 min
6. Validaciones: 2 min
7. Postman: 5 min
8. WebFlux: 2 min

---

## 🔧 Comandos de Emergencia

### Si algo falla, reiniciar:
```bash
./stop-services.sh
./start-services.sh
```

### Ver logs en tiempo real:
```bash
tail -f logs/gateway.log
tail -f logs/shipments.log
tail -f logs/packages.log
```

### Verificar puertos:
```bash
lsof -i :8080
lsof -i :8081
lsof -i :8082
```

---

## ✅ Preparación Antes de la Demostración

1. ✓ Compilar todos los proyectos
2. ✓ Importar colección de Postman
3. ✓ Probar todos los endpoints
4. ✓ Tener los comandos curl listos
5. ✓ Tener el código fuente abierto en el IDE
6. ✓ Cerrar aplicaciones innecesarias

---

**Autor**: Edson Gutiérrez  
**Tema**: Gestión de envíos y rastreo de paquetes  
**Fecha**: 14 de octubre de 2025

¡Buena suerte con la demostración! 🚀
