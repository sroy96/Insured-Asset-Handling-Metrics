package com.acko.insuredassetcredibility.dao.interfaces;

import com.acko.insuredassetcredibility.models.VehicleMaintenanceCondition;

import java.util.List;

public interface VehicleMaintenanceConditionDao {

    List<VehicleMaintenanceCondition> getAllVehicleMaintenanceCondition();

    List<VehicleMaintenanceCondition> getVehicleMaintenanceCondition(String assetId);

    void updateVehicleMaintenanceCondition(VehicleMaintenanceCondition vehicleMaintenanceCondition);

    void deleteVehicleMaintenanceCondition(VehicleMaintenanceCondition vehicleMaintenanceCondition);
}
