package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.models.VehicleRepair;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleRepairRepository extends MongoRepository<VehicleRepair, String> {

    List<VehicleRepair> findAllByAssetId(String assetId);
}
