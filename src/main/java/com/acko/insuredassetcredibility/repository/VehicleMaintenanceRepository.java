package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.models.VehicleAccident;
import com.acko.insuredassetcredibility.models.VehicleMaintenanceCondition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleMaintenanceRepository extends MongoRepository<VehicleMaintenanceCondition, String> {

    List<VehicleMaintenanceCondition> findAllByAssetId(String assetId);
    List<VehicleMaintenanceCondition> findByAssetIdAndLastRefreshedTimeBetween(String assetId, LocalDateTime startDate, LocalDateTime endDate);

}
