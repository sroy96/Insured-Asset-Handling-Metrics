package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.models.VehicleAccident;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleAccidentRepository extends MongoRepository<VehicleAccident, String> {

    List<VehicleAccident> findAllByAssetId(String assetId);
}
