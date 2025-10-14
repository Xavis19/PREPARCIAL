#!/bin/bash

echo "🧪 Ejecutando pruebas de los endpoints"
echo "======================================"

# Colores
GREEN='\033[0;32m'
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m'

GATEWAY_URL="http://localhost:8080"

# Función para hacer request y mostrar resultado
test_endpoint() {
    local method=$1
    local url=$2
    local description=$3
    local data=$4
    
    echo -e "\n${BLUE}Testing: $description${NC}"
    echo "Method: $method"
    echo "URL: $url"
    
    if [ -z "$data" ]; then
        response=$(curl -s -w "\nHTTP_STATUS:%{http_code}" -X $method "$url")
    else
        response=$(curl -s -w "\nHTTP_STATUS:%{http_code}" -X $method -H "Content-Type: application/json" -d "$data" "$url")
    fi
    
    http_code=$(echo "$response" | grep HTTP_STATUS | cut -d: -f2)
    body=$(echo "$response" | sed '/HTTP_STATUS/d')
    
    echo "Response Code: $http_code"
    echo "Response Body:"
    echo "$body" | jq . 2>/dev/null || echo "$body"
    
    if [ "$http_code" -ge 200 ] && [ "$http_code" -lt 300 ]; then
        echo -e "${GREEN}✓ SUCCESS${NC}"
    else
        echo -e "${RED}✗ FAILED${NC}"
    fi
}

echo -e "\n${BLUE}========================================${NC}"
echo -e "${BLUE}    SHIPMENTS SERVICE TESTS${NC}"
echo -e "${BLUE}========================================${NC}"

# GET All Shipments
test_endpoint "GET" "$GATEWAY_URL/api/shipments" "GET - Listar todos los envíos"

# GET Shipment by ID
test_endpoint "GET" "$GATEWAY_URL/api/shipments/1" "GET - Obtener envío por ID"

# POST Create Shipment
test_endpoint "POST" "$GATEWAY_URL/api/shipments" "POST - Crear nuevo envío" '{
  "trackingNumber": "TRK999",
  "origin": "Monterrey",
  "destination": "Querétaro",
  "senderName": "Test User",
  "recipientName": "Test Recipient",
  "recipientAddress": "Test Address 123",
  "weight": 2.5,
  "status": "PENDING"
}'

echo -e "\n${BLUE}========================================${NC}"
echo -e "${BLUE}    PACKAGES SERVICE TESTS${NC}"
echo -e "${BLUE}========================================${NC}"

# GET All Packages
test_endpoint "GET" "$GATEWAY_URL/api/packages" "GET - Listar todos los paquetes"

# GET Package by ID
test_endpoint "GET" "$GATEWAY_URL/api/packages/1" "GET - Obtener paquete por ID"

# POST Create Package
test_endpoint "POST" "$GATEWAY_URL/api/packages" "POST - Crear nuevo paquete" '{
  "trackingNumber": "TRK999",
  "description": "Test Package",
  "currentLocation": "Test Location",
  "lastCheckpoint": "Test Checkpoint",
  "estimatedDeliveryDays": 3,
  "trackingStatus": "REGISTERED",
  "notes": "Test notes"
}'

echo -e "\n${BLUE}========================================${NC}"
echo -e "${BLUE}    HEALTH CHECKS${NC}"
echo -e "${BLUE}========================================${NC}"

test_endpoint "GET" "http://localhost:8080/actuator/health" "Gateway Health Check"
test_endpoint "GET" "http://localhost:8081/actuator/health" "Shipments Service Health Check"
test_endpoint "GET" "http://localhost:8082/actuator/health" "Packages Service Health Check"

echo -e "\n${GREEN}========================================${NC}"
echo -e "${GREEN}    TESTS COMPLETED${NC}"
echo -e "${GREEN}========================================${NC}"
