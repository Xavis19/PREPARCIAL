package com.parcial.packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Package {
    private String id;
    private String trackingNumber;
    private String description;
    private String currentLocation;
    private String lastCheckpoint;
    private Double estimatedDeliveryDays;
    private String trackingStatus; // REGISTERED, IN_WAREHOUSE, OUT_FOR_DELIVERY, DELIVERED, LOST
    private LocalDateTime lastUpdate;
    private String notes;
}
