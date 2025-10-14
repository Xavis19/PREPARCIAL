package com.parcial.shipments.controller;

import com.parcial.shipments.dto.ShipmentRequest;
import com.parcial.shipments.dto.ShipmentResponse;
import com.parcial.shipments.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {
    
    private final ShipmentService service;
    
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
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ShipmentResponse> createShipment(@Valid @RequestBody ShipmentRequest request) {
        return service.createShipment(request);
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ShipmentResponse>> updateShipment(
            @PathVariable String id,
            @Valid @RequestBody ShipmentRequest request) {
        return service.updateShipment(id, request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteShipment(@PathVariable String id) {
        return service.deleteShipment(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
