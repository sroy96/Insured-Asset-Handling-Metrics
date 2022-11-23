package com.acko.insuredassetcredibility.dao.interfaces;

import com.acko.insuredassetcredibility.models.VehicleMaintenanceCondition;

import java.util.List;

public interface VehicleMaintenanceConditionDao {

    public List<VehicleMaintenanceCondition> getAllVehicleMaintenanceCondition();

    public List<VehicleMaintenanceCondition> getVehicleMaintenanceCondition(String assetId);

    public void updateVehicleMaintenanceCondition(VehicleMaintenanceCondition vehicleMaintenanceCondition);

    public void deleteVehicleMaintenanceCondition(VehicleMaintenanceCondition vehicleMaintenanceCondition);
}
