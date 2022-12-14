package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.models.VehicleAccident;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleAccidentRepository extends MongoRepository<VehicleAccident, String> {

    List<VehicleAccident> findAllByAssetId(String assetId);
    List<VehicleAccident> findByAssetIdAndLastRefreshedTimeBetween(String assetId, LocalDateTime startDate, LocalDateTime endDate);
}
