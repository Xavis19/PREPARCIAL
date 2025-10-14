#!/bin/bash

echo "🚀 Iniciando Sistema de Gestión de Envíos y Rastreo de Paquetes"
echo "================================================================"

# Colores para output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Función para verificar si un puerto está en uso
check_port() {
    if lsof -Pi :$1 -sTCP:LISTEN -t >/dev/null ; then
        echo -e "${YELLOW}⚠️  Puerto $1 está en uso. Liberando...${NC}"
        lsof -ti:$1 | xargs kill -9 2>/dev/null
        sleep 2
    fi
}

# Verificar y liberar puertos
echo -e "${BLUE}📡 Verificando puertos...${NC}"
check_port 8080
check_port 8081
check_port 8082

# Iniciar Microservicio de Envíos
echo -e "${BLUE}📦 Iniciando Microservicio de Envíos (Puerto 8081)...${NC}"
cd ms-shipments
mvn clean spring-boot:run > ../logs/shipments.log 2>&1 &
SHIPMENTS_PID=$!
cd ..

sleep 10

# Iniciar Microservicio de Paquetes
echo -e "${BLUE}📫 Iniciando Microservicio de Paquetes (Puerto 8082)...${NC}"
cd ms-packages
mvn clean spring-boot:run > ../logs/packages.log 2>&1 &
PACKAGES_PID=$!
cd ..

sleep 10

# Iniciar API Gateway
echo -e "${BLUE}🌐 Iniciando API Gateway (Puerto 8080)...${NC}"
cd gateway
mvn clean spring-boot:run > ../logs/gateway.log 2>&1 &
GATEWAY_PID=$!
cd ..

echo ""
echo -e "${GREEN}✅ Servicios iniciados exitosamente!${NC}"
echo ""
echo "PIDs de los procesos:"
echo "  - Shipments Service: $SHIPMENTS_PID"
echo "  - Packages Service: $PACKAGES_PID"
echo "  - API Gateway: $GATEWAY_PID"
echo ""
echo "URLs de los servicios:"
echo "  - Gateway: http://localhost:8080"
echo "  - Shipments: http://localhost:8081"
echo "  - Packages: http://localhost:8082"
echo ""
echo "Health checks:"
echo "  - curl http://localhost:8080/actuator/health"
echo "  - curl http://localhost:8081/actuator/health"
echo "  - curl http://localhost:8082/actuator/health"
echo ""
echo -e "${YELLOW}Para detener los servicios, ejecuta: ./stop-services.sh${NC}"
echo ""

# Guardar PIDs
echo $SHIPMENTS_PID > .shipments.pid
echo $PACKAGES_PID > .packages.pid
echo $GATEWAY_PID > .gateway.pid

# Esperar 5 segundos y verificar estado
sleep 5
echo -e "${BLUE}🔍 Verificando estado de los servicios...${NC}"
curl -s http://localhost:8081/actuator/health > /dev/null && echo -e "${GREEN}✓ Shipments Service: OK${NC}" || echo -e "${YELLOW}⚠ Shipments Service: Iniciando...${NC}"
curl -s http://localhost:8082/actuator/health > /dev/null && echo -e "${GREEN}✓ Packages Service: OK${NC}" || echo -e "${YELLOW}⚠ Packages Service: Iniciando...${NC}"
curl -s http://localhost:8080/actuator/health > /dev/null && echo -e "${GREEN}✓ API Gateway: OK${NC}" || echo -e "${YELLOW}⚠ API Gateway: Iniciando...${NC}"

echo ""
echo -e "${GREEN}🎉 ¡Sistema listo para usar!${NC}"
