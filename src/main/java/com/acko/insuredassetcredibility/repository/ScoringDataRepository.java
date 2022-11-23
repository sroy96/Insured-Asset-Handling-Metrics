/**
 * @author saurav roy
 * Date:23/11/22
 * Time:9:47 PM
 */
package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.dao.ScoreDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoringDataRepository extends MongoRepository<ScoreDao,String> {
    ScoreDao findScoreDaosByAssetId(String assetId);
}
