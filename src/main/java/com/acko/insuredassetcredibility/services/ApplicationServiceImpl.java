/**
 * @author saurav roy
 * Date:21/11/22
 * Time:12:31 AM
 */
package com.acko.insuredassetcredibility.services;

import com.acko.insuredassetcredibility.dao.RegisteredAssetDao;
import com.acko.insuredassetcredibility.dao.ScoreDao;
import com.acko.insuredassetcredibility.dto.requests.AssetScoringRequest;
import com.acko.insuredassetcredibility.dto.responses.AssetScoringResponse;
import com.acko.insuredassetcredibility.enums.Activities;
import com.acko.insuredassetcredibility.enums.KeyFactors;
import com.acko.insuredassetcredibility.interfaces.ApplicationService;
import com.acko.insuredassetcredibility.models.AssetScores;
import com.acko.insuredassetcredibility.models.KeyActivities;
import com.acko.insuredassetcredibility.models.KeyFactorDataScore;
import com.acko.insuredassetcredibility.models.KeyFactorsData;
import com.acko.insuredassetcredibility.repository.RegisteredAssetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private RegisteredAssetRepository repository;


    public AssetScoringResponse getAssetScoringDetails(AssetScoringRequest assetScoringRequest) {
        List<RegisteredAssetDao> registeredAssetList = repository.findAllByOwnerMobileOrOwnerEmail(assetScoringRequest.getMobile(), assetScoringRequest.getEmail());
        AssetScoringResponse assetScoringResponse = new AssetScoringResponse();
        for(RegisteredAssetDao assetDao: registeredAssetList){
            String assetId = assetDao.getAssetId();
            AssetScores assetScores =  new AssetScores();
            assetScores.setAssetId(assetId);
            assetScores.setScore();


        }
        return new AssetScoringResponse();
    }


    @Override
    public List<KeyActivities> getActivities(String assetId, ScoreDao scoreDao) {
        return null;
    }

    @Override
    public List<KeyFactorDataScore> getKeyFactorData(String assetId, ScoreDao scoreDao) {
        return null;
    }
}
