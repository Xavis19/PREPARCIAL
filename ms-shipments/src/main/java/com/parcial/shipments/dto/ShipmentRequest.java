package com.parcial.shipments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRequest {
    
    @NotBlank(message = "El número de rastreo es obligatorio")
    private String trackingNumber;
    
    @NotBlank(message = "El origen es obligatorio")
    private String origin;
    
    @NotBlank(message = "El destino es obligatorio")
    private String destination;
    
    @NotBlank(message = "El nombre del remitente es obligatorio")
    private String senderName;
    
    @NotBlank(message = "El nombre del destinatario es obligatorio")
    private String recipientName;
    
    @NotBlank(message = "La dirección del destinatario es obligatoria")
    private String recipientAddress;
    
    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser positivo")
    private Double weight;
    
    @NotBlank(message = "El estado es obligatorio")
    private String status;
}
