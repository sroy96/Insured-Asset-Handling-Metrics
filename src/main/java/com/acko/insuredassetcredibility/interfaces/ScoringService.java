/**
 * @author saurav roy
 * Date:19/11/22
 * Time:7:32 PM
 */
package com.acko.insuredassetcredibility.interfaces;

public interface ScoringService<T> {
    Double calculateScore(String assetId);

    Double getScore(String assetId);

    Double calculateKeyFactorDelta(String assetId);

    T getActivityDetails(String assetId);
}
