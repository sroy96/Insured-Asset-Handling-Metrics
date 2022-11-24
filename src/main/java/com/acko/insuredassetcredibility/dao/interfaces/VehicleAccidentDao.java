package com.acko.insuredassetcredibility.dao.interfaces;

import com.acko.insuredassetcredibility.models.VehicleAccident;

import java.util.List;

public interface VehicleAccidentDao {

    List<VehicleAccident> getAllVehicleAccident();

    List<VehicleAccident> getVehicleAccident(String assetId);

    void updateVehicleAccident(VehicleAccident vehicleAccident);

    void deleteVehicleAccident(VehicleAccident vehicleAccident);

}
