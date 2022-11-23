/**
 * @author saurav roy
 * Date:19/11/22
 * Time:7:32 PM
 */
package com.acko.insuredassetcredibility.interfaces;

import com.acko.insuredassetcredibility.dao.ScoreDao;
import com.acko.insuredassetcredibility.models.KeyFactorDataScore;

public interface ScoringService<T> {
    Integer calculateScore(String assetId);

    Integer getScore(String assetId);

    KeyFactorDataScore calculateKeyFactor(String assetId, ScoreDao scoreDao);

    T getActivityDetails(String assetId);
}
