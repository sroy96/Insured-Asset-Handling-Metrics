/**
 * @author saurav roy
 * Date:19/11/22
 * Time:7:33 PM
 */
package com.acko.insuredassetcredibility.interfaces;

import com.acko.insuredassetcredibility.dao.ScoreDao;
import com.acko.insuredassetcredibility.models.KeyActivities;
import com.acko.insuredassetcredibility.models.KeyFactorDataScore;
import com.acko.insuredassetcredibility.models.KeyFactorsData;

import java.util.List;

public interface ApplicationService {

    List<KeyActivities>getActivities(String assetId, ScoreDao scoreDao);
    List<KeyFactorDataScore>getKeyFactorData(String assetId, ScoreDao scoreDao);


}
