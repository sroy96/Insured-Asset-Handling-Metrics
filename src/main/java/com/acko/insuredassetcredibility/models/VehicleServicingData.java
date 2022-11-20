/**
 * @author saurav roy
 * Date:20/11/22
 * Time:4:11 PM
 */
package com.acko.insuredassetcredibility.models;

import com.acko.insuredassetcredibility.enums.DocumentStatus;
import com.acko.insuredassetcredibility.enums.ServicingStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VehicleServicingData {
    private Date engineOilChangeDate;
    private Date headLightRepairDate;
    private ServicingStatus headLightCondition;
    private Date brakeLightRepairDate;
    private ServicingStatus brakeLightCondition;
    private Date parkingLightRepairDate;
    private ServicingStatus parkingLightCondition;
    private Date leftTurnSignalLightRepairDate;
    private ServicingStatus leftTurnSignalLightCondition;
    private Date airFilterRepairDate;
    private ServicingStatus airFilterCondition;
    private DocumentStatus PUCStatus;
    private Date lastPUCDate;
    private Date minorAccidentDate;
    private Date majorAccidentDate;
    private ServicingStatus engineStatus;
    private Double lastOdoMetreReading;
    private Date transmissionRepairDate;
    private Date shockAbsorbersRepairDate;
    private String shockAbsorberTypeInstalled;
    private Boolean isCustomized;
    private Date windShieldWiperChangeDate;
    private ServicingStatus batteryHealth;
    private Date frontDifferentialFluidChangeDate;
    private ServicingStatus frontDifferentialStatus;
    private Date rearDifferentialFluidChangeDate;
    private ServicingStatus rearDifferentialStatus;
    private Date batteryChangedDate;
    private Date sparkPlugRepairDate;
}
