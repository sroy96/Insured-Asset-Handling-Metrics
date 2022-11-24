package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.models.VehicleMaintenanceCondition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMaintenanceRepository extends MongoRepository<VehicleMaintenanceCondition, String> {

}
