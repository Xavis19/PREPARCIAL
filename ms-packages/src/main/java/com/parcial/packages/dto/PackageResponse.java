package com.parcial.packages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageResponse {
    private String id;
    private String trackingNumber;
    private String description;
    private String currentLocation;
    private String lastCheckpoint;
    private Double estimatedDeliveryDays;
    private String trackingStatus;
    private LocalDateTime lastUpdate;
    private String notes;
}
