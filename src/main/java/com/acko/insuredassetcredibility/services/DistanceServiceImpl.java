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
        EventData fastTagEventData = this.getFastTagEvent(assetId, LocalDateTime.now(), ServiceUtils.getNewRefreshDate(scoreDao.getRefreshDate()));
        Double totalOutStationDistance = this.calculateDistance(fastTagEventData);
        keyActivities.setTotal(Integer.valueOf(String.valueOf(totalOutStationDistance)));
        keyActivities.setEvents(eventData);
        keyActivitiesList.add(keyActivities);
        return keyActivitiesList;
    }

    private Double calculateDistance(EventData fastTagEventData) {
        List<BaseEventData> eventData = fastTagEventData.getEventData();
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
            return OutStationDistanceUtil.getDistanceBetweenToll(entryTollLat,entryTollLong,exitTollLat,exitTollLong);

        }
        return 0.0;
    }

    private EventData getFastTagEvent(String assetId, LocalDateTime from, LocalDateTime to) {
        EventData fastTagEvent = new EventData();
        fastTagEvent.setEventName(Activities.OUTSTATION_COMMUTE.getActivityId());
        List<OutStationActivity> activities = outStationCommuteRepository.findAllByAssetId(from, to, assetId);
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

    private Integer calculateScore(KeyActivities keyActivities) {
        return 0;
    }

    @Override
    public List<KeyFactorDataScore> getKeyFactorData(String assetId, ScoreDao scoreDao) {
        KeyFactorDataScore keyFactorDataScore = new KeyFactorDataScore();
        KeyFactorsData keyFactorsData = new KeyFactorsData();
        keyFactorsData.setKeyFactor(KeyFactors.DISTANCE_COMMUTED);
        keyFactorsData.setImpact(ImpactType.MEDIUM);
        log.info("Getting outstation activity");
        Integer score = this.calculateScore(this.getActivities(assetId, scoreDao).get(0));
        keyFactorDataScore.setScore(score);
        Integer delta = scoreDao.getKeyFactorScores().get(KeyFactors.DISTANCE_COMMUTED);
        keyFactorsData.setDelta((delta > 0.0 ? "+" : "-") + delta);
        Integer lastScore = scoreDao.getScore();
        if (score > AppConstants.OUTSTATION_COMMUTE__MONTHLY_THRESHOLD_IN_KM) {
            keyFactorsData.setUsageCategory(ImpactCategory.POOR);
        } else if (lastScore < score && score < AppConstants.OUTSTATION_COMMUTE__MONTHLY_THRESHOLD_IN_KM) {
            keyFactorsData.setUsageCategory(ImpactCategory.GOOD);
        } else if (score < lastScore && score < AppConstants.OUTSTATION_COMMUTE__MONTHLY_THRESHOLD_IN_KM) {
            keyFactorsData.setUsageCategory(ImpactCategory.VERY_GOOD);
        } else if (score < 200 && lastScore < AppConstants.OUTSTATION_COMMUTE__MONTHLY_THRESHOLD_IN_KM) {
            log.info("User has been under threshold for last 90 days.");
            keyFactorsData.setUsageCategory(ImpactCategory.EXCELLENT);
        }
        keyFactorDataScore.setKeyFactorsData(keyFactorsData);
        keyFactorDataScore.setScore(score);
        List<KeyFactorDataScore> keyFactorDataScores = new ArrayList<>();
        keyFactorDataScores.add(keyFactorDataScore);
        return keyFactorDataScores;
    }
}
