package com.acko.insuredassetcredibility.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Document("repair")
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VehicleRepair {

    @Id
    private String serviceCenterName;
    private String assetId;
    private LocalDateTime engineOilChangeDate;
    private LocalDateTime headLightRepairDate;
    private LocalDateTime brakeLightRepairDate;
    private LocalDateTime parkingLightRepairDate;
    private LocalDateTime leftTurnSignalLightRepairDate;
    private LocalDateTime rightTurnSignalLightRepairDate;
    private LocalDateTime airFilterRepairDate;
    private LocalDateTime transmissionRepairDate;
    private LocalDateTime shockAbsorbersRepairDate;
    private LocalDateTime batteryChangedDate;
    private LocalDateTime sparkPlugRepairDate;
    private LocalDateTime windShieldWiperChangeDate;
    private LocalDateTime lastRefreshedTime;
}
