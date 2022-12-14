/**
 * @author saurav roy
 * Date:23/11/22
 * Time:9:50 PM
 */
package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.dao.acitivity.OutStationActivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OutStationCommuteRepository extends MongoRepository<OutStationActivity,String> {

    List<OutStationActivity>findAllByAssetIdAndTollEntryDateBetween(String assetId, LocalDateTime fromDate, LocalDateTime toDate);

}

