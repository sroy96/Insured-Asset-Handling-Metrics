/**
 * @author saurav roy
 * Date:23/11/22
 * Time:9:22 PM
 */
package com.acko.insuredassetcredibility.services;

import com.acko.insuredassetcredibility.dao.FastTagActivity;
import com.acko.insuredassetcredibility.dao.ScoreDao;
import com.acko.insuredassetcredibility.dao.acitivity.OutStationActivity;
import com.acko.insuredassetcredibility.enums.Activities;
import com.acko.insuredassetcredibility.enums.ImpactType;
import com.acko.insuredassetcredibility.enums.KeyFactors;
import com.acko.insuredassetcredibility.interfaces.ApplicationService;
import com.acko.insuredassetcredibility.models.*;
import com.acko.insuredassetcredibility.repository.OutStationCommuteRepository;
import com.acko.insuredassetcredibility.utils.scoring.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class DistanceServiceImpl implements ApplicationService {

    @Autowired
    OutStationCommuteRepository outStationCommuteRepository;

    @Override
    public List<KeyActivities> getActivities(String assetId, ScoreDao scoreDao) {
        KeyActivities keyActivities= new KeyActivities();
        keyActivities.setActivityId(Activities.OUTSTATION_COMMUTE.name());
        keyActivities.setActivityName(Activities.OUTSTATION_COMMUTE.getActivityId());
        List<EventData>eventData = new ArrayList<>();
        eventData.add(this.getFastTagEvent(assetId, LocalDateTime.now(), ServiceUtils.getNewRefreshDate(scoreDao.getRefreshDate())));
        keyActivities.setEvents(eventData);
        return null;
    }

    private EventData getFastTagEvent(String assetId, LocalDateTime from, LocalDateTime to){
        EventData fastTagEvent = new EventData();
        fastTagEvent.setEventName(Activities.OUTSTATION_COMMUTE.getActivityId());
        List<OutStationActivity> activities = outStationCommuteRepository.findAllByAssetId(from,to,assetId);
        fastTagEvent.setCount(activities.size());
        List<BaseEventData> baseEventDataList = new ArrayList<>();
        int totalDistance = 0;
        if(activities.size()>0) {
            for (OutStationActivity outStationActivity : activities) {
                String tollId = outStationActivity.getTollId();
                LocalDateTime localDateTime = outStationActivity.getTollEntryDate();
                //TODO: find the exit Toll of the Toll Id and Calculate distance.
                totalDistance = totalDistance + new Random().nextInt(100) + 1;
                baseEventDataList.add(new BaseEventData(tollId,localDateTime));

            }
            fastTagEvent.setEventData(baseEventDataList);
        }
        return fastTagEvent;

    }

    private Double calculateScore(KeyActivities keyActivities){
        return 0.0;
    }

    @Override
    public List<KeyFactorDataScore> getKeyFactorData(String assetId, ScoreDao scoreDao) {
        KeyFactorDataScore keyFactorDataScore = new KeyFactorDataScore();
        KeyFactorsData  keyFactorsData = new KeyFactorsData();
        keyFactorsData.setKeyFactors(KeyFactors.DISTANCE_COMMUTED);
        keyFactorsData.setImpactType(ImpactType.MEDIUM);
        log.info("Getting outstation activity");
        Double score =this.calculateScore(this.getActivities(assetId,scoreDao).get(0));
        keyFactorDataScore.setScore(score);
        Double delta =  scoreDao.getKeyFactorScores().get(KeyFactors.DISTANCE_COMMUTED);
        return null;
    }
}
