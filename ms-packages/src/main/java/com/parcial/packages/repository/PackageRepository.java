package com.parcial.packages.repository;

import com.parcial.packages.model.Package;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PackageRepository {
    
    private final Map<String, Package> packages = new ConcurrentHashMap<>();
    
    public PackageRepository() {
        // Datos de ejemplo
        initializeData();
    }
    
    private void initializeData() {
        Package package1 = new Package(
            "1",
            "TRK001",
            "Electrónicos - Laptop Dell",
            "Centro de Distribución Guadalajara",
            "Salió de Ciudad de México",
            2.0,
            "IN_WAREHOUSE",
            LocalDateTime.now().minusHours(3),
            "Paquete frágil, manejar con cuidado"
        );
        
        Package package2 = new Package(
            "2",
            "TRK002",
            "Documentos legales",
            "En camino a Puebla",
            "Centro de Distribución Monterrey",
            1.0,
            "OUT_FOR_DELIVERY",
            LocalDateTime.now().minusHours(1),
            "Entrega urgente"
        );
        
        packages.put(package1.getId(), package1);
        packages.put(package2.getId(), package2);
    }
    
    public Flux<Package> findAll() {
        return Flux.fromIterable(packages.values());
    }
    
    public Mono<Package> findById(String id) {
        return Mono.justOrEmpty(packages.get(id));
    }
    
    public Mono<Package> save(Package packageItem) {
        if (packageItem.getId() == null) {
            packageItem.setId(UUID.randomUUID().toString());
        }
        packageItem.setLastUpdate(LocalDateTime.now());
        packages.put(packageItem.getId(), packageItem);
        return Mono.just(packageItem);
    }
    
    public Mono<Void> deleteById(String id) {
        packages.remove(id);
        return Mono.empty();
    }
    
    public Mono<Boolean> existsById(String id) {
        return Mono.just(packages.containsKey(id));
    }
}
