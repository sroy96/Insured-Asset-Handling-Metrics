/**
 * @author saurav roy
 * Date:21/11/22
 * Time:12:31 AM
 */
package com.acko.insuredassetcredibility.services;

import com.acko.insuredassetcredibility.constants.AppConstants;
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

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private RegisteredAssetRepository repository;

    @Autowired
    ScoringDataRepository scoringDataRepository;

    @Autowired
    ChallanServiceImpl challanService;

    @Autowired
    DistanceServiceImpl distanceService;

    @Autowired
    private MaintenanceServiceImpl maintenanceService;


    public AssetScoringResponse getAssetScoringDetails(String phone) {
        List<RegisteredAssetDao> registeredAssetList = repository.findAllByOwnerMobile(phone);
        AssetScoringResponse assetScoringResponse = new AssetScoringResponse();
        List<AssetScores> assetScoresList = new ArrayList<>();
        for(RegisteredAssetDao assetDao: registeredAssetList){
            double finalScore = 0;
            String assetId = assetDao.getAssetId();
            AssetScores assetScores =  new AssetScores();
            assetScores.setAssetName(assetDao.getMake() +"-" + assetDao.getModel());
            assetScores.setAssetId(assetId);
            ScoreDao scoreDao = scoringDataRepository.findScoreDaosByAssetId(assetId);
            if (!Objects.isNull(scoreDao) &&  ChronoUnit.MINUTES.between(scoreDao.getRefreshDate(), LocalDateTime.now()) < AppConstants.REFRESH_PERIOD_MINUTES) {
                assetScores.setScore(scoreDao.getScore());
                assetScores.setKeyActivities(scoreDao.getActivitiesList());
                assetScores.setKeyFactorsData(scoreDao.getKeyFactorsData());
                assetScoresList.add(assetScores);
                continue;
            }

            List<KeyActivities> keyActivitiesList =this.getActivities(assetId,scoreDao);
            List<KeyFactorDataScore> keyFactorDataScores = this.getKeyFactorData(assetId,scoreDao);
            assetScores.setKeyActivities(keyActivitiesList);
            scoreDao = Objects.isNull(scoreDao)?new ScoreDao():scoreDao;
            scoreDao.setAssetId(assetId);
            scoreDao.setActivitiesList(keyActivitiesList);

            List<KeyFactorsData> keyFactorsData = new ArrayList<>();
            Map<KeyFactors,Integer> keyFactorScores = new HashMap<>();
            for(KeyFactorDataScore dataScore : keyFactorDataScores){
                keyFactorsData.add(dataScore.getKeyFactorsData());
                keyFactorScores.put(dataScore.getKeyFactorsData().getKeyFactor(),dataScore.getScore());
                finalScore = finalScore + dataScore.getScore() * dataScore.getKeyFactorsData().getKeyFactor().getWeightage();
            }
            finalScore = (int)finalScore;
            if(finalScore<300){
                finalScore=300;
            }
            finalScore = Math.min(1000,finalScore);
            scoreDao.setKeyFactorsData(keyFactorsData);
            assetScores.setKeyFactorsData(keyFactorsData);
            assetScores.setScore((int)finalScore);
            scoreDao.setScore((int)finalScore);
            assetScoresList.add(assetScores);
            scoreDao.setRefreshDate(LocalDateTime.now());
            scoreDao.setKeyFactorScores(keyFactorScores);
            scoringDataRepository.save(scoreDao);

        }
        assetScoringResponse.setAssetScoresList(assetScoresList);
        return assetScoringResponse;
    }


    @Override
    public List<KeyActivities> getActivities(String assetId, ScoreDao scoreDao) {
        List<KeyActivities>keyActivities = new ArrayList<>();
        //keyActivities.addAll(maintenanceService.getActivities(assetId,scoreDao));

        keyActivities.addAll(challanService.getActivities(assetId,scoreDao));
        keyActivities.addAll(distanceService.getActivities(assetId,scoreDao));
        return keyActivities;
    }

    @Override
    public List<KeyFactorDataScore> getKeyFactorData(String assetId, ScoreDao scoreDao) {
        List<KeyFactorDataScore> keyFactorDataScoreList = new ArrayList<>();
        keyFactorDataScoreList.addAll(challanService.getKeyFactorData(assetId,scoreDao));
        keyFactorDataScoreList.addAll(distanceService.getKeyFactorData(assetId,scoreDao));
   //     keyFactorDataScoreList.addAll(maintenanceService.getKeyFactorData(assetId, scoreDao));
        return keyFactorDataScoreList;
    }

    public void saveAsset(RegisteredAssetDao registeredAssetDao){
        repository.save(registeredAssetDao);
    }
}
