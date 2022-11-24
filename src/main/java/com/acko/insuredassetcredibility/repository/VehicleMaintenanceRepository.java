package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.models.VehicleMaintenanceCondition;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleMaintenanceRepository extends MongoRepository<VehicleMaintenanceCondition, String> {

    List<VehicleMaintenanceCondition> findAllByAssetId(String assetId);
}
