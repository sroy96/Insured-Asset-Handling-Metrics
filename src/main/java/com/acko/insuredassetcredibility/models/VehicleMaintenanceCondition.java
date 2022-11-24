package com.acko.insuredassetcredibility.models;

import com.acko.insuredassetcredibility.enums.ServicingStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VehicleMaintenanceCondition {

    private ServicingStatus headLightCondition;
    private ServicingStatus brakeLightCondition;
    private ServicingStatus parkingLightCondition;
    private ServicingStatus leftTurnSignalLightCondition;
    private ServicingStatus airFilterCondition;
    private LocalDateTime lastRefreshedTime;

}