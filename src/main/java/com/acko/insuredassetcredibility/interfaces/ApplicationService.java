/**
 * @author saurav roy
 * Date:19/11/22
 * Time:7:33 PM
 */
package com.acko.insuredassetcredibility.interfaces;

import com.acko.insuredassetcredibility.dto.requests.AssetScoringRequest;
import com.acko.insuredassetcredibility.dto.responses.AssetScoringResponse;

public interface ApplicationService {

    AssetScoringResponse getAssetScoringDetails(AssetScoringRequest assetScoringRequest);

}
