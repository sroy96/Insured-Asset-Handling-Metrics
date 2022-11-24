/**
 * @author saurav roy
 * Date:21/11/22
 * Time:12:37 AM
 */
package com.acko.insuredassetcredibility.repository;

import com.acko.insuredassetcredibility.dao.RegisteredAssetDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisteredAssetRepository extends MongoRepository<RegisteredAssetDao,String> {
    List<RegisteredAssetDao> findAllByOwnerMobileOrOwnerEmail(String mobile, String email);
    List<RegisteredAssetDao> findAllByOwnerMobile(String mobile);
}
