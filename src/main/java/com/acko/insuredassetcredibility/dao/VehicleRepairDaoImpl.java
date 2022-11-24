package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.dao.interfaces.VehicleRepairDao;
import com.acko.insuredassetcredibility.models.VehicleRepair;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class VehicleRepairDaoImpl implements VehicleRepairDao {
    @Override
    public List<VehicleRepair> getAllVehicleRepair() {
        return null;
    }

    @Override
    public List<VehicleRepair> getVehicleRepair(String assetId) {
        return null;
    }

    @Override
    public void updateVehicleRepair(VehicleRepair vehicleRepair) {

    }

    @Override
    public void deleteVehicleRepair(VehicleRepair vehicleRepair) {

    }
}
