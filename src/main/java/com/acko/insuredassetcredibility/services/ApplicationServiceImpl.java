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
import com.acko.insuredassetcredibility.repository.ScoringDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private RegisteredAssetRepository repository;

    @Autowired
    ScoringDataRepository scoringDataRepository;


    public AssetScoringResponse getAssetScoringDetails(AssetScoringRequest assetScoringRequest) {
        List<RegisteredAssetDao> registeredAssetList = repository.findAllByOwnerMobileOrOwnerEmail(assetScoringRequest.getMobile(), assetScoringRequest.getEmail());
        AssetScoringResponse assetScoringResponse = new AssetScoringResponse();
        for(RegisteredAssetDao assetDao: registeredAssetList){
            String assetId = assetDao.getAssetId();
            AssetScores assetScores =  new AssetScores();
            assetScores.setAssetName(assetDao.getMake() +"-" + assetDao.getModel());
            assetScores.setAssetId(assetId);
            ScoreDao scoreDao = scoringDataRepository.findScoreDaosByAssetId(assetId);
            assetScores.setKeyActivities(this.getActivities(assetId,scoreDao));
            assetScores.setKeyFactorsData(this.getKeyFactorData(assetId,scoreDao));


        }
        return new AssetScoringResponse();
    }


    @Override
    public List<KeyActivities> getActivities(String assetId, ScoreDao scoreDao) {
        List<KeyActivities>keyActivities = new ArrayList<>();
        keyActivities.add(new DistanceServiceImpl().getActivities(assetId,scoreDao).get(0));
        return keyActivities;
    }

    @Override
    public List<KeyFactorDataScore> getKeyFactorData(String assetId, ScoreDao scoreDao) {
        return null;
    }
}
