package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.dao.interfaces.VehicleMaintenanceConditionDao;
import com.acko.insuredassetcredibility.models.VehicleMaintenanceCondition;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
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
