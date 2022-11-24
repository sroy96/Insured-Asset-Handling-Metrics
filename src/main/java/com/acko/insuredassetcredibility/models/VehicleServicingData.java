/**
 * @author saurav roy
 * Date:20/11/22
 * Time:4:11 PM
 */
package com.acko.insuredassetcredibility.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VehicleServicingData {

    private VehicleAccident vehicleAccident;
    private VehicleRepair vehicleRepair;
    private VehicleMaintenanceCondition vehicleMaintenanceCondition;

}
