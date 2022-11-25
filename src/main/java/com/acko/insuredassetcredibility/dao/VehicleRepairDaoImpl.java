package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.dao.interfaces.VehicleRepairDao;
import com.acko.insuredassetcredibility.models.VehicleRepair;
import com.acko.insuredassetcredibility.repository.VehicleRepairRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class VehicleRepairDaoImpl implements VehicleRepairDao {

    @Autowired
    private VehicleRepairRepository vehicleRepairRepository;

    @Override
    public List<VehicleRepair> getAllVehicleRepair() {
        return null;
    }

    @Override
    public List<VehicleRepair> getVehicleRepair(String assetId) {

        return vehicleRepairRepository.findAllByAssetId(assetId);
    }

    @Override
    public void updateVehicleRepair(VehicleRepair vehicleRepair) {

    }

    @Override
    public void deleteVehicleRepair(VehicleRepair vehicleRepair) {

    }
}
