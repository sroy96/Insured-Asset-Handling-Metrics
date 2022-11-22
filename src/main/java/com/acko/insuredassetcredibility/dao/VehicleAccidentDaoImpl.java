package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.interfaces.VehicleAccidentDao;
import com.acko.insuredassetcredibility.models.VehicleAccident;
import lombok.Data;

import java.util.List;

@Data
public class VehicleAccidentDaoImpl implements VehicleAccidentDao {
    @Override
    public List<VehicleAccident> getAllVehicleAccident() {
        return null;
    }

    @Override
    public List<VehicleAccident> getVehicleAccident(String assetId) {
        return null;
    }

    @Override
    public void updateVehicleAccident(VehicleAccident vehicleAccident) {

    }

    @Override
    public void deleteVehicleAccident(VehicleAccident vehicleAccident) {

    }
}
