package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.dao.ChallanDao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ChallanRepository extends MongoRepository<ChallanDao,String> {


  List<ChallanDao> findByAssetIdAndUpdatedDateBetween(String assetId, LocalDateTime startDate, LocalDateTime endDate);

  List<ChallanDao> findByAssetId(String assetId);
}
