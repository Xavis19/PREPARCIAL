#!/bin/bash

echo "🛑 Deteniendo Sistema de Gestión de Envíos y Rastreo de Paquetes"
echo "================================================================="

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

# Función para detener un servicio
stop_service() {
    if [ -f $1 ]; then
        PID=$(cat $1)
        if ps -p $PID > /dev/null; then
            echo -e "${RED}Deteniendo $2 (PID: $PID)...${NC}"
            kill $PID
            rm $1
        else
            echo -e "${GREEN}$2 ya está detenido${NC}"
            rm $1
        fi
    else
        echo -e "${GREEN}$2 no tiene PID registrado${NC}"
    fi
}

# Detener servicios
stop_service ".gateway.pid" "API Gateway"
stop_service ".packages.pid" "Packages Service"
stop_service ".shipments.pid" "Shipments Service"

# Forzar cierre de puertos si aún están en uso
echo ""
echo "Verificando puertos..."
lsof -ti:8080 | xargs kill -9 2>/dev/null && echo "Puerto 8080 liberado"
lsof -ti:8081 | xargs kill -9 2>/dev/null && echo "Puerto 8081 liberado"
lsof -ti:8082 | xargs kill -9 2>/dev/null && echo "Puerto 8082 liberado"

echo ""
echo -e "${GREEN}✅ Todos los servicios han sido detenidos${NC}"
