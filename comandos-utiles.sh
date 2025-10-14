#!/bin/bash

# 🎨 Comandos Útiles para el Proyecto
# =====================================

echo "📋 Comandos Útiles - Gestión de Envíos y Rastreo de Paquetes"
echo "=============================================================="
echo ""

# Función para mostrar comando con descripción
show_command() {
    echo -e "\033[1;34m$1\033[0m"
    echo "  $2"
    echo ""
}

echo "🚀 INICIO Y DETENCIÓN"
echo "---------------------"
show_command "./start-services.sh" "Iniciar todos los servicios"
show_command "./stop-services.sh" "Detener todos los servicios"
show_command "./test-endpoints.sh" "Probar todos los endpoints"

echo "🔍 VERIFICACIÓN DE SERVICIOS"
echo "----------------------------"
show_command "curl http://localhost:8080/actuator/health" "Gateway health check"
show_command "curl http://localhost:8081/actuator/health" "Shipments health check"
show_command "curl http://localhost:8082/actuator/health" "Packages health check"

echo "📦 SHIPMENTS - OPERACIONES"
echo "--------------------------"
show_command "curl http://localhost:8080/api/shipments" "GET - Listar todos"
show_command "curl http://localhost:8080/api/shipments/1" "GET - Por ID"
show_command "curl -X POST http://localhost:8080/api/shipments -H 'Content-Type: application/json' -d '{...}'" "POST - Crear"
show_command "curl -X PUT http://localhost:8080/api/shipments/1 -H 'Content-Type: application/json' -d '{...}'" "PUT - Actualizar"
show_command "curl -X DELETE http://localhost:8080/api/shipments/1" "DELETE - Eliminar"

echo "📫 PACKAGES - OPERACIONES"
echo "-------------------------"
show_command "curl http://localhost:8080/api/packages" "GET - Listar todos"
show_command "curl http://localhost:8080/api/packages/1" "GET - Por ID"
show_command "curl -X POST http://localhost:8080/api/packages -H 'Content-Type: application/json' -d '{...}'" "POST - Crear"
show_command "curl -X PUT http://localhost:8080/api/packages/1 -H 'Content-Type: application/json' -d '{...}'" "PUT - Actualizar"
show_command "curl -X DELETE http://localhost:8080/api/packages/1" "DELETE - Eliminar"

echo "🐳 DOCKER"
echo "---------"
show_command "docker-compose up -d" "Iniciar con Docker"
show_command "docker-compose logs -f" "Ver logs en tiempo real"
show_command "docker-compose down" "Detener contenedores"
show_command "docker-compose ps" "Ver estado de contenedores"

echo "📊 LOGS Y DEBUG"
echo "---------------"
show_command "tail -f logs/gateway.log" "Ver logs del Gateway"
show_command "tail -f logs/shipments.log" "Ver logs de Shipments"
show_command "tail -f logs/packages.log" "Ver logs de Packages"

echo "🔧 UTILIDADES"
echo "-------------"
show_command "lsof -i :8080" "Ver qué usa el puerto 8080"
show_command "lsof -ti:8080 | xargs kill -9" "Liberar puerto 8080"
show_command "mvn clean install" "Compilar proyecto"
show_command "mvn spring-boot:run" "Ejecutar con Maven"

echo "📝 DOCUMENTACIÓN"
echo "----------------"
show_command "cat README.md" "Ver documentación principal"
show_command "cat GUIA_RAPIDA.md" "Ver guía rápida"
show_command "cat DEMOSTRACION.md" "Ver guía de demostración"
show_command "cat RESUMEN.md" "Ver resumen del proyecto"

echo "✅ VERIFICACIÓN COMPLETA"
echo "------------------------"
echo "Para verificar que todo funciona:"
echo "  1. ./start-services.sh"
echo "  2. ./test-endpoints.sh"
echo "  3. Importar colección en Postman"
echo "  4. Ejecutar pruebas en Postman"
echo ""
