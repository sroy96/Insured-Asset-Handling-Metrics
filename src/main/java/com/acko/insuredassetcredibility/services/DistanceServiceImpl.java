/**
 * @author saurav roy
 * Date:23/11/22
 * Time:9:22 PM
 */
package com.acko.insuredassetcredibility.services;

import com.acko.insuredassetcredibility.constants.AppConstants;
import com.acko.insuredassetcredibility.dao.ScoreDao;
import com.acko.insuredassetcredibility.dao.acitivity.OutStationActivity;
import com.acko.insuredassetcredibility.enums.Activities;
import com.acko.insuredassetcredibility.enums.ImpactCategory;
import com.acko.insuredassetcredibility.enums.ImpactType;
import com.acko.insuredassetcredibility.enums.KeyFactors;
import com.acko.insuredassetcredibility.interfaces.ApplicationService;
import com.acko.insuredassetcredibility.models.*;
import com.acko.insuredassetcredibility.repository.NationalTollRegistry;
import com.acko.insuredassetcredibility.repository.OutStationCommuteRepository;
import com.acko.insuredassetcredibility.utils.scoring.OutStationDistanceUtil;
import com.acko.insuredassetcredibility.utils.scoring.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DistanceServiceImpl implements ApplicationService {

    @Autowired
    OutStationCommuteRepository outStationCommuteRepository;

    @Autowired
    NationalTollRegistry registry;

    @Override
    public List<KeyActivities> getActivities(String assetId, ScoreDao scoreDao) {
        List<KeyActivities> keyActivitiesList = new ArrayList<>();
        KeyActivities keyActivities = new KeyActivities();
        keyActivities.setActivityId(Activities.OUTSTATION_COMMUTE.name());
        keyActivities.setUnitOfMeasurement("km");
        keyActivities.setActivityName(Activities.OUTSTATION_COMMUTE.getActivityId());
        List<EventData> eventData = new ArrayList<>();
        //TODO: refresh date
        EventData fastTagEventData = this.getFastTagEvent(assetId, ServiceUtils.getNewRefreshDate(scoreDao.getRefreshDate()),LocalDateTime.now());
        Double totalOutStationDistance = this.calculateDistance(fastTagEventData);
        keyActivities.setTotal((int)Math.round(totalOutStationDistance));
        eventData.add(fastTagEventData);
        keyActivities.setEvents(eventData);
        keyActivitiesList.add(keyActivities);
        return keyActivitiesList;
    }

    private Double calculateDistance(EventData fastTagEventData) {
        List<BaseEventData> eventData = fastTagEventData.getEventData();
        double totalCalculate = 0.0;
        for(BaseEventData data: eventData){
            String entryTollId =  data.getName();
            com.acko.insuredassetcredibility.dao.NationalTollRegistry entryTollInfo = registry.findByTollId(entryTollId);
            List<String>exitTollIds = entryTollInfo.getExitTollIds();
            Double entryTollLat = entryTollInfo.getLatitude();
            Double entryTollLong = entryTollInfo.getLongitude();
            String consideredExitTollId = exitTollIds.get(0);
            com.acko.insuredassetcredibility.dao.NationalTollRegistry exitTollInfo = registry.findByTollId(consideredExitTollId);
            Double exitTollLat = exitTollInfo.getLatitude();
            Double exitTollLong = exitTollInfo.getLongitude();
            totalCalculate= totalCalculate+ OutStationDistanceUtil.getDistanceBetweenToll(entryTollLat,entryTollLong,exitTollLat,exitTollLong);

        }
        return totalCalculate;
    }

    private EventData getFastTagEvent(String assetId, LocalDateTime from, LocalDateTime to) {
        EventData fastTagEvent = new EventData();
        fastTagEvent.setEventName(Activities.OUTSTATION_COMMUTE.getActivityId());
        List<OutStationActivity> activities = outStationCommuteRepository.findAllByAssetIdAndTollEntryDateBetween(assetId,from,to);
        fastTagEvent.setCount(activities.size());
        List<BaseEventData> baseEventDataList = new ArrayList<>();
        if (activities.size() > 0) {
            for (OutStationActivity outStationActivity : activities) {
                String tollId = outStationActivity.getTollId();
                LocalDateTime localDateTime = outStationActivity.getTollEntryDate();
                baseEventDataList.add(new BaseEventData(tollId, localDateTime));
            }
            fastTagEvent.setEventData(baseEventDataList);
        }
        return fastTagEvent;

    }

    private List<String> getRedFlags() {
        return null;
    }

    private Integer calculateScore(List<KeyActivities> keyActivities,ScoreDao scoreDao) {
        List<KeyActivities>activities = scoreDao.getActivitiesList();
        KeyActivities fastTag = null;
        KeyActivities currentFastag = null;
        for(KeyActivities lastActivities: activities){
            if(Activities.OUTSTATION_COMMUTE.name().equals(lastActivities.getActivityId())){
                fastTag = lastActivities;
            }
        }
        for(KeyActivities currentActivity: keyActivities){
            if(Activities.OUTSTATION_COMMUTE.name().equals(currentActivity.getActivityId())){
                currentFastag = currentActivity;
            }
        }
        if(null!=currentFastag) {
            int lastTotal = fastTag!=null ? fastTag.getTotal() : 0;
            int lastNumberOfEvents =fastTag!=null ? fastTag.getEvents().size():0;
            int currentTotal = currentFastag.getTotal();
            int currentNumberOfEvents =  currentFastag.getEvents().size();
            int boost = 0;
            int penalize = 0;
            if(lastTotal==currentTotal && currentNumberOfEvents==lastNumberOfEvents ){
                penalize-=50;
            }
            if(lastTotal==currentTotal && currentNumberOfEvents<lastNumberOfEvents){
                penalize-=70;
            }
            if(lastTotal<currentTotal){
                penalize+=20;
            }
            if(lastTotal<currentTotal && lastNumberOfEvents<currentNumberOfEvents){
                penalize+=40;
            }
            if(lastTotal>currentTotal && lastNumberOfEvents>currentNumberOfEvents && currentTotal<AppConstants.OUTSTATION_COMMUTE__MONTHLY_THRESHOLD_IN_KM){
                boost+=30;
            }
            if(lastTotal<currentTotal && lastNumberOfEvents>currentNumberOfEvents && currentTotal<AppConstants.OUTSTATION_COMMUTE__MONTHLY_THRESHOLD_IN_KM){
                boost+=15;
            }
            if(lastTotal<currentTotal && lastNumberOfEvents>currentNumberOfEvents && currentTotal<AppConstants.OUTSTATION_COMMUTE__MONTHLY_THRESHOLD_IN_KM){
                boost+=10;
            }
            if(lastTotal<currentTotal && currentTotal>AppConstants.OUTSTATION_COMMUTE__MONTHLY_THRESHOLD_IN_KM){
                penalize+=45;
            }
            if(lastTotal<currentTotal && currentTotal<AppConstants.OUTSTATION_COMMUTE__MONTHLY_THRESHOLD_IN_KM && lastNumberOfEvents<currentNumberOfEvents){
                penalize+=35;
            }
            int currentScore = scoreDao.getKeyFactorScores().get(KeyFactors.DISTANCE)!=null?scoreDao.getKeyFactorScores().get(KeyFactors.DISTANCE):1000 + boost - penalize;
            if(currentScore<=300){
                return 300;
            }
            return Math.min(currentScore, 1000);

        }

        return 0;
    }

    @Override
    public List<KeyFactorDataScore> getKeyFactorData(String assetId, ScoreDao scoreDao) {
        KeyFactorDataScore keyFactorDataScore = new KeyFactorDataScore();
        KeyFactorsData keyFactorsData = new KeyFactorsData();
        keyFactorsData.setKeyFactor(KeyFactors.DISTANCE);
        keyFactorsData.setImpact(ImpactType.HIGH);
        log.info("Getting outstation activity");
        List<KeyActivities> keyActivities = this.getActivities(assetId, scoreDao);
        keyFactorsData.setTotal(keyActivities.get(0).getTotal());
        int currentScore = this.calculateScore(keyActivities,scoreDao);
        keyFactorDataScore.setScore(currentScore);
        Integer lastScore = scoreDao.getKeyFactorScores().get(KeyFactors.DISTANCE)!=null? scoreDao.getKeyFactorScores().get(KeyFactors.DISTANCE): 1000;
        int delta = currentScore-lastScore;
        if(delta==0){
            keyFactorsData.setDelta("0");
        }
        else {
            keyFactorsData.setDelta((delta > 0 ? "+" : "-") + Math.abs(delta));
        }
        if(300<currentScore && currentScore<500){
            keyFactorsData.setUsageCategory(ImpactCategory.POOR);
        }
        if(500<currentScore && currentScore<700){
            keyFactorsData.setUsageCategory(ImpactCategory.GOOD);
        }
        if(700<currentScore && currentScore<900){
            keyFactorsData.setUsageCategory(ImpactCategory.VERY_GOOD);
        }
        if(900<currentScore && currentScore<1000){
            keyFactorsData.setUsageCategory(ImpactCategory.EXCELLENT);
        }
        keyFactorDataScore.setKeyFactorsData(keyFactorsData);
        keyFactorDataScore.setScore(currentScore);
        List<KeyFactorDataScore> keyFactorDataScores = new ArrayList<>();
        keyFactorDataScores.add(keyFactorDataScore);
        return keyFactorDataScores;
    }

    public void addActivity(OutStationActivity outStationActivity) {
        outStationActivity.setTollId(this.cleanTollId(outStationActivity.getTollId()));
        outStationCommuteRepository.save(outStationActivity);
    }

    private String cleanTollId(String tollId){
        tollId = tollId.replaceAll("\\s","");
        return tollId.toUpperCase();
    }

    public void addNationalToll(com.acko.insuredassetcredibility.dao.NationalTollRegistry nationalTollRegistry){
        nationalTollRegistry.setTollId(this.cleanTollId(nationalTollRegistry.getTollId()));
        registry.save(nationalTollRegistry);
    }
}
