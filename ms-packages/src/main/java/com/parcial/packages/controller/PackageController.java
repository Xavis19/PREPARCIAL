package com.parcial.packages.controller;

import com.parcial.packages.dto.PackageRequest;
import com.parcial.packages.dto.PackageResponse;
import com.parcial.packages.service.PackageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/packages")
@RequiredArgsConstructor
public class PackageController {
    
    private final PackageService service;
    
    @GetMapping
    public Flux<PackageResponse> getAllPackages() {
        return service.getAllPackages();
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<PackageResponse>> getPackageById(@PathVariable String id) {
        return service.getPackageById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PackageResponse> createPackage(@Valid @RequestBody PackageRequest request) {
        return service.createPackage(request);
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<PackageResponse>> updatePackage(
            @PathVariable String id,
            @Valid @RequestBody PackageRequest request) {
        return service.updatePackage(id, request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePackage(@PathVariable String id) {
        return service.deletePackage(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
