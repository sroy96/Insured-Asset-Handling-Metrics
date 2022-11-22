package com.acko.insuredassetcredibility.interfaces;

import com.acko.insuredassetcredibility.models.VehicleRepair;

import java.util.List;

public interface VehicleRepairDao {

    public List<VehicleRepair> getAllVehicleRepair();

    public List<VehicleRepair> getVehicleRepair(String assetId);

    public void updateVehicleRepair(VehicleRepair vehicleRepair);

    public void deleteVehicleRepair(VehicleRepair vehicleRepair);
}
