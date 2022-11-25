package com.acko.insuredassetcredibility.models;

import com.acko.insuredassetcredibility.enums.ServicingStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Document("maintenance")
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VehicleMaintenanceCondition {

    @Id
    private String serviceCenterName;
    private String assetId;
    private ServicingStatus headLightCondition;
    private ServicingStatus brakeLightCondition;
    private ServicingStatus parkingLightCondition;
    private ServicingStatus leftTurnSignalLightCondition;
    private ServicingStatus airFilterCondition;
    private LocalDateTime lastRefreshedTime;

}