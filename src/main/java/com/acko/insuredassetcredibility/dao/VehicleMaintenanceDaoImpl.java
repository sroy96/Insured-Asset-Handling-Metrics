package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.interfaces.VehicleMaintenanceConditionDao;
import com.acko.insuredassetcredibility.models.VehicleMaintenanceCondition;
import lombok.Data;

import java.util.List;

@Data
public class VehicleMaintenanceDaoImpl implements VehicleMaintenanceConditionDao {
    @Override
    public List<VehicleMaintenanceCondition> getAllVehicleMaintenanceCondition() {
        return null;
    }

    @Override
    public List<VehicleMaintenanceCondition> getVehicleMaintenanceCondition(String assetId) {
        return null;
    }

    @Override
    public void updateVehicleMaintenanceCondition(VehicleMaintenanceCondition vehicleMaintenanceCondition) {

    }

    @Override
    public void deleteVehicleMaintenanceCondition(VehicleMaintenanceCondition vehicleMaintenanceCondition) {

    }
}