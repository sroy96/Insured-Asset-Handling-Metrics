package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.dao.interfaces.VehicleAccidentDao;
import com.acko.insuredassetcredibility.models.VehicleAccident;
import com.acko.insuredassetcredibility.repository.VehicleAccidentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class VehicleAccidentDaoImpl implements VehicleAccidentDao {

    @Autowired
    private VehicleAccidentRepository vehicleAccidentRepository;

    @Override
    public List<VehicleAccident> getAllVehicleAccident() {
        return null;
    }

    @Override
    public List<VehicleAccident> getVehicleAccident(String assetId) {

        return vehicleAccidentRepository.findAllByAssetId(assetId);
    }

    @Override
    public void updateVehicleAccident(VehicleAccident vehicleAccident) {

    }

    @Override
    public void deleteVehicleAccident(VehicleAccident vehicleAccident) {

    }
}
