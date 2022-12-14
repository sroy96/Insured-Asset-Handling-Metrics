package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.models.VehicleMaintenanceCondition;
import com.acko.insuredassetcredibility.models.VehicleRepair;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleRepairRepository extends MongoRepository<VehicleRepair, String> {

    List<VehicleRepair> findAllByAssetId(String assetId);

    List<VehicleRepair> findByAssetIdAndLastRefreshedTimeBetween(String assetId, LocalDateTime startDate, LocalDateTime endDate);

}
