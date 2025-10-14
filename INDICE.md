# 📚 Índice de Documentación

Este proyecto contiene documentación completa organizada en varios archivos. Usa este índice para encontrar rápidamente la información que necesitas.

---

## 📖 Documentos Principales

### 1. **README.md** 📘
**Propósito**: Documentación técnica completa del proyecto

**Contenido**:
- Arquitectura del sistema
- Instrucciones de instalación y ejecución
- Descripción detallada de modelos de datos
- Endpoints API con ejemplos
- Configuración de Docker
- Troubleshooting
- Stack tecnológico

**Cuándo usarlo**: Para entender el proyecto a fondo o para configuración inicial

---

### 2. **GUIA_RAPIDA.md** ⚡
**Propósito**: Inicio rápido en menos de 5 minutos

**Contenido**:
- Pasos de ejecución simplificados
- Comandos esenciales
- Ejemplos de pruebas rápidas
- Checklist de entrega
- Solución de problemas comunes

**Cuándo usarlo**: Cuando necesitas poner en marcha el proyecto rápidamente

---

### 3. **DEMOSTRACION.md** 🎓
**Propósito**: Guía paso a paso para la demostración al profesor

**Contenido**:
- Orden de demostración estructurado
- Scripts de prueba para cada endpoint
- Comandos preparados para copiar/pegar
- Checklist de evaluación
- Comandos de emergencia
- Tiempo estimado: 20-25 minutos

**Cuándo usarlo**: Antes y durante la presentación al profesor

---

### 4. **RESUMEN.md** 📊
**Propósito**: Vista general ejecutiva del proyecto

**Contenido**:
- Estructura completa del proyecto
- Resumen de tecnologías
- Criterios de evaluación cumplidos
- Características destacadas
- Información de contacto

**Cuándo usarlo**: Para obtener una vista panorámica del proyecto

---

## 🛠️ Archivos de Configuración

### 5. **Postman Collection** 📮
- `Gestion_Envios_Rastreo.postman_collection.json`
- `Postman_Environment.json`

**Contenido**:
- 15+ requests organizados
- Variables de entorno configuradas
- Casos de éxito y error
- Health checks

**Cómo usar**: Importar en Postman y ejecutar

---

### 6. **Docker Configuration** 🐳
- `docker-compose.yml`
- `gateway/Dockerfile`
- `ms-shipments/Dockerfile`
- `ms-packages/Dockerfile`

**Contenido**:
- Configuración de contenedores
- Orquestación de servicios
- Health checks
- Networking

**Cómo usar**: `docker-compose up -d`

---

## 🚀 Scripts de Automatización

### 7. **start-services.sh** ▶️
**Propósito**: Iniciar todos los servicios automáticamente

**Uso**:
```bash
./start-services.sh
```

---

### 8. **stop-services.sh** ⏹️
**Propósito**: Detener todos los servicios

**Uso**:
```bash
./stop-services.sh
```

---

### 9. **test-endpoints.sh** 🧪
**Propósito**: Probar todos los endpoints automáticamente

**Uso**:
```bash
./test-endpoints.sh
```

---

### 10. **comandos-utiles.sh** 📝
**Propósito**: Referencia rápida de comandos útiles

**Uso**:
```bash
./comandos-utiles.sh
```

---

## 📂 Código Fuente

### Gateway (Puerto 8080)
```
gateway/
├── src/main/java/com/parcial/gateway/
│   └── GatewayApplication.java
└── src/main/resources/
    └── application.yml
```

### Microservicio de Envíos (Puerto 8081)
```
ms-shipments/
└── src/main/java/com/parcial/shipments/
    ├── controller/ShipmentController.java
    ├── service/ShipmentService.java
    ├── repository/ShipmentRepository.java
    ├── model/Shipment.java
    ├── dto/
    │   ├── ShipmentRequest.java
    │   └── ShipmentResponse.java
    └── exception/GlobalExceptionHandler.java
```

### Microservicio de Paquetes (Puerto 8082)
```
ms-packages/
└── src/main/java/com/parcial/packages/
    ├── controller/PackageController.java
    ├── service/PackageService.java
    ├── repository/PackageRepository.java
    ├── model/Package.java
    ├── dto/
    │   ├── PackageRequest.java
    │   └── PackageResponse.java
    └── exception/GlobalExceptionHandler.java
```

---

## 🗺️ Guía de Navegación por Caso de Uso

### "Quiero poner en marcha el proyecto rápido"
→ Lee **GUIA_RAPIDA.md** y ejecuta `./start-services.sh`

### "Necesito entender la arquitectura completa"
→ Lee **README.md** y **RESUMEN.md**

### "Voy a demostrar el proyecto al profesor"
→ Sigue **DEMOSTRACION.md** paso a paso

### "Quiero probar los endpoints"
→ Importa la colección de Postman o ejecuta `./test-endpoints.sh`

### "Necesito ver código específico"
→ Navega a las carpetas `ms-shipments/` o `ms-packages/`

### "Quiero usar Docker"
→ Ejecuta `docker-compose up -d`

### "Tengo un problema"
→ Revisa la sección Troubleshooting en **README.md** o **GUIA_RAPIDA.md**

### "Necesito comandos rápidos"
→ Ejecuta `./comandos-utiles.sh`

---

## 📞 Información del Proyecto

- **Autor**: Edson Gutiérrez
- **Tema**: Gestión de envíos y rastreo de paquetes
- **Framework**: Spring WebFlux + Spring Cloud Gateway
- **Fecha**: 14 de octubre de 2025

---

## ✅ Checklist de Documentos

- [x] README.md - Documentación completa
- [x] GUIA_RAPIDA.md - Inicio rápido
- [x] DEMOSTRACION.md - Guía de presentación
- [x] RESUMEN.md - Vista general
- [x] INDICE.md - Este archivo
- [x] Postman Collection - Pruebas
- [x] Scripts de automatización
- [x] Configuración Docker

---

## 🎯 Próximos Pasos

1. ✅ Leer este índice
2. ✅ Revisar **GUIA_RAPIDA.md**
3. ✅ Ejecutar `./start-services.sh`
4. ✅ Importar colección de Postman
5. ✅ Practicar la demostración con **DEMOSTRACION.md**
6. ✅ Leer **README.md** para profundizar

---

**¡Buena suerte con el proyecto! 🚀**
