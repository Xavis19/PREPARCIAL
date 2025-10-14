package com.parcial.packages.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageRequest {
    
    @NotBlank(message = "El número de rastreo es obligatorio")
    private String trackingNumber;
    
    @NotBlank(message = "La descripción es obligatoria")
    private String description;
    
    @NotBlank(message = "La ubicación actual es obligatoria")
    private String currentLocation;
    
    @NotBlank(message = "El último punto de control es obligatorio")
    private String lastCheckpoint;
    
    @NotNull(message = "Los días estimados de entrega son obligatorios")
    @Positive(message = "Los días estimados deben ser positivos")
    private Double estimatedDeliveryDays;
    
    @NotBlank(message = "El estado de rastreo es obligatorio")
    private String trackingStatus;
    
    private String notes;
}
