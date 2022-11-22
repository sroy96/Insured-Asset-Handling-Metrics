package com.acko.insuredassetcredibility.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VehicleRepair {

    private String assetId;
    private Date engineOilChangeDate;
    private Date headLightRepairDate;
    private Date brakeLightRepairDate;
    private Date parkingLightRepairDate;
    private Date leftTurnSignalLightRepairDate;
    private Date rightTurnSignalLightRepairDate;
    private Date airFilterRepairDate;
    private Date transmissionRepairDate;
    private Date shockAbsorbersRepairDate;
    private Date batteryChangedDate;
    private Date sparkPlugRepairDate;
    private Date windShieldWiperChangeDate;
    private Long lastRefreshedTimeMillis;
}
