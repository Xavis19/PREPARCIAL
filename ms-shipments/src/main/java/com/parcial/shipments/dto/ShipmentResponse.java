package com.parcial.shipments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentResponse {
    private String id;
    private String trackingNumber;
    private String origin;
    private String destination;
    private String senderName;
    private String recipientName;
    private String recipientAddress;
    private Double weight;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
