package com.acko.insuredassetcredibility.dao.interfaces;

import com.acko.insuredassetcredibility.models.VehicleAccident;

import java.util.List;

public interface VehicleAccidentDao {

    public List<VehicleAccident> getAllVehicleAccident();

    public List<VehicleAccident> getVehicleAccident(String assetId);

    public void updateVehicleAccident(VehicleAccident vehicleAccident);

    public void deleteVehicleAccident(VehicleAccident vehicleAccident);

}
