/**
 * @author saurav roy
 * Date:23/11/22
 * Time:9:50 PM
 */
package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.dao.acitivity.OutStationActivity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutStationCommuteRepository extends MongoRepository<OutStationActivity> {
}
