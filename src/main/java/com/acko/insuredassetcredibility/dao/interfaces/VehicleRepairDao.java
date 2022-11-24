package com.acko.insuredassetcredibility.dao.interfaces;

import com.acko.insuredassetcredibility.models.VehicleRepair;

import java.util.List;

public interface VehicleRepairDao {

    List<VehicleRepair> getAllVehicleRepair();

    List<VehicleRepair> getVehicleRepair(String assetId);

    void updateVehicleRepair(VehicleRepair vehicleRepair);

    void deleteVehicleRepair(VehicleRepair vehicleRepair);
}
