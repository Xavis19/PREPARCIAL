package com.parcial.packages.service;

import com.parcial.packages.dto.PackageRequest;
import com.parcial.packages.dto.PackageResponse;
import com.parcial.packages.model.Package;
import com.parcial.packages.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PackageService {
    
    private final PackageRepository repository;
    
    public Flux<PackageResponse> getAllPackages() {
        return repository.findAll()
                .map(this::toResponse);
    }
    
    public Mono<PackageResponse> getPackageById(String id) {
        return repository.findById(id)
                .map(this::toResponse);
    }
    
    public Mono<PackageResponse> createPackage(PackageRequest request) {
        Package packageItem = new Package();
        packageItem.setTrackingNumber(request.getTrackingNumber());
        packageItem.setDescription(request.getDescription());
        packageItem.setCurrentLocation(request.getCurrentLocation());
        packageItem.setLastCheckpoint(request.getLastCheckpoint());
        packageItem.setEstimatedDeliveryDays(request.getEstimatedDeliveryDays());
        packageItem.setTrackingStatus(request.getTrackingStatus());
        packageItem.setLastUpdate(LocalDateTime.now());
        packageItem.setNotes(request.getNotes());
        
        return repository.save(packageItem)
                .map(this::toResponse);
    }
    
    public Mono<PackageResponse> updatePackage(String id, PackageRequest request) {
        return repository.findById(id)
                .flatMap(existingPackage -> {
                    existingPackage.setTrackingNumber(request.getTrackingNumber());
                    existingPackage.setDescription(request.getDescription());
                    existingPackage.setCurrentLocation(request.getCurrentLocation());
                    existingPackage.setLastCheckpoint(request.getLastCheckpoint());
                    existingPackage.setEstimatedDeliveryDays(request.getEstimatedDeliveryDays());
                    existingPackage.setTrackingStatus(request.getTrackingStatus());
                    existingPackage.setLastUpdate(LocalDateTime.now());
                    existingPackage.setNotes(request.getNotes());
                    
                    return repository.save(existingPackage);
                })
                .map(this::toResponse);
    }
    
    public Mono<Void> deletePackage(String id) {
        return repository.findById(id)
                .flatMap(packageItem -> repository.deleteById(id));
    }
    
    private PackageResponse toResponse(Package packageItem) {
        return new PackageResponse(
            packageItem.getId(),
            packageItem.getTrackingNumber(),
            packageItem.getDescription(),
            packageItem.getCurrentLocation(),
            packageItem.getLastCheckpoint(),
            packageItem.getEstimatedDeliveryDays(),
            packageItem.getTrackingStatus(),
            packageItem.getLastUpdate(),
            packageItem.getNotes()
        );
    }
}
