package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.dao.interfaces.VehicleMaintenanceConditionDao;
import com.acko.insuredassetcredibility.models.VehicleMaintenanceCondition;
import com.acko.insuredassetcredibility.repository.VehicleMaintenanceRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class VehicleMaintenanceDaoImpl implements VehicleMaintenanceConditionDao {

    @Autowired
    private VehicleMaintenanceRepository vehicleMaintenanceRepository;

    @Override
    public List<VehicleMaintenanceCondition> getAllVehicleMaintenanceCondition() {
        return null;
    }

    @Override
    public List<VehicleMaintenanceCondition> getVehicleMaintenanceCondition(String assetId) {

        return vehicleMaintenanceRepository.findAllByAssetId(assetId);
    }

    @Override
    public void updateVehicleMaintenanceCondition(VehicleMaintenanceCondition vehicleMaintenanceCondition) {

    }

    @Override
    public void deleteVehicleMaintenanceCondition(VehicleMaintenanceCondition vehicleMaintenanceCondition) {

    }
}
