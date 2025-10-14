package com.parcial.shipments.repository;

import com.parcial.shipments.model.Shipment;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ShipmentRepository {
    
    private final Map<String, Shipment> shipments = new ConcurrentHashMap<>();
    
    public ShipmentRepository() {
        // Datos de ejemplo
        initializeData();
    }
    
    private void initializeData() {
        Shipment shipment1 = new Shipment(
            "1",
            "TRK001",
            "Ciudad de México",
            "Guadalajara",
            "Juan Pérez",
            "María García",
            "Av. Principal 123",
            5.5,
            "IN_TRANSIT",
            LocalDateTime.now().minusDays(2),
            LocalDateTime.now()
        );
        
        Shipment shipment2 = new Shipment(
            "2",
            "TRK002",
            "Monterrey",
            "Puebla",
            "Carlos López",
            "Ana Martínez",
            "Calle Secundaria 456",
            3.2,
            "PENDING",
            LocalDateTime.now().minusDays(1),
            LocalDateTime.now()
        );
        
        shipments.put(shipment1.getId(), shipment1);
        shipments.put(shipment2.getId(), shipment2);
    }
    
    public Flux<Shipment> findAll() {
        return Flux.fromIterable(shipments.values());
    }
    
    public Mono<Shipment> findById(String id) {
        return Mono.justOrEmpty(shipments.get(id));
    }
    
    public Mono<Shipment> save(Shipment shipment) {
        if (shipment.getId() == null) {
            shipment.setId(UUID.randomUUID().toString());
            shipment.setCreatedAt(LocalDateTime.now());
        }
        shipment.setUpdatedAt(LocalDateTime.now());
        shipments.put(shipment.getId(), shipment);
        return Mono.just(shipment);
    }
    
    public Mono<Void> deleteById(String id) {
        shipments.remove(id);
        return Mono.empty();
    }
    
    public Mono<Boolean> existsById(String id) {
        return Mono.just(shipments.containsKey(id));
    }
}
