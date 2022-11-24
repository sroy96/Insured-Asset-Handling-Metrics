/**
 * @author saurav roy
 * Date:24/11/22
 * Time:6:28 PM
 */
package com.acko.insuredassetcredibility.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalTollRegistry extends MongoRepository<com.acko.insuredassetcredibility.dao.NationalTollRegistry,String> {
    com.acko.insuredassetcredibility.dao.NationalTollRegistry findByTollId(String tollId);
}
