package com.parcial.shipments.service;

import com.parcial.shipments.dto.ShipmentRequest;
import com.parcial.shipments.dto.ShipmentResponse;
import com.parcial.shipments.model.Shipment;
import com.parcial.shipments.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShipmentService {
    
    private final ShipmentRepository repository;
    
    public Flux<ShipmentResponse> getAllShipments() {
        return repository.findAll()
                .map(this::toResponse);
    }
    
    public Mono<ShipmentResponse> getShipmentById(String id) {
        return repository.findById(id)
                .map(this::toResponse);
    }
    
    public Mono<ShipmentResponse> createShipment(ShipmentRequest request) {
        Shipment shipment = new Shipment();
        shipment.setTrackingNumber(request.getTrackingNumber());
        shipment.setOrigin(request.getOrigin());
        shipment.setDestination(request.getDestination());
        shipment.setSenderName(request.getSenderName());
        shipment.setRecipientName(request.getRecipientName());
        shipment.setRecipientAddress(request.getRecipientAddress());
        shipment.setWeight(request.getWeight());
        shipment.setStatus(request.getStatus());
        shipment.setCreatedAt(LocalDateTime.now());
        shipment.setUpdatedAt(LocalDateTime.now());
        
        return repository.save(shipment)
                .map(this::toResponse);
    }
    
    public Mono<ShipmentResponse> updateShipment(String id, ShipmentRequest request) {
        return repository.findById(id)
                .flatMap(existingShipment -> {
                    existingShipment.setTrackingNumber(request.getTrackingNumber());
                    existingShipment.setOrigin(request.getOrigin());
                    existingShipment.setDestination(request.getDestination());
                    existingShipment.setSenderName(request.getSenderName());
                    existingShipment.setRecipientName(request.getRecipientName());
                    existingShipment.setRecipientAddress(request.getRecipientAddress());
                    existingShipment.setWeight(request.getWeight());
                    existingShipment.setStatus(request.getStatus());
                    existingShipment.setUpdatedAt(LocalDateTime.now());
                    
                    return repository.save(existingShipment);
                })
                .map(this::toResponse);
    }
    
    public Mono<Void> deleteShipment(String id) {
        return repository.findById(id)
                .flatMap(shipment -> repository.deleteById(id));
    }
    
    private ShipmentResponse toResponse(Shipment shipment) {
        return new ShipmentResponse(
            shipment.getId(),
            shipment.getTrackingNumber(),
            shipment.getOrigin(),
            shipment.getDestination(),
            shipment.getSenderName(),
            shipment.getRecipientName(),
            shipment.getRecipientAddress(),
            shipment.getWeight(),
            shipment.getStatus(),
            shipment.getCreatedAt(),
            shipment.getUpdatedAt()
        );
    }
}
