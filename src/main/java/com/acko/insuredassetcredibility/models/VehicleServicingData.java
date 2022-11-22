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
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VehicleServicingData {

    private VehicleAccident vehicleAccident;
    private VehicleRepair vehicleRepair;
    private VehicleMaintenanceCondition vehicleMaintenanceCondition;

}
