package com.acko.insuredassetcredibility.services;

import com.acko.insuredassetcredibility.dao.ScoreDao;
import com.acko.insuredassetcredibility.enums.*;
import com.acko.insuredassetcredibility.interfaces.ApplicationService;
import com.acko.insuredassetcredibility.models.EventData;
import com.acko.insuredassetcredibility.models.KeyActivities;
import com.acko.insuredassetcredibility.models.KeyFactorDataScore;
import com.acko.insuredassetcredibility.models.KeyFactorsData;
import com.acko.insuredassetcredibility.utils.scoring.ServicingScoringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaintenanceServiceImpl implements ApplicationService {

    @Autowired
    private ServicingScoringUtil servicingScoringUtil;

    @Override
    public List<KeyActivities> getActivities(String assetId, ScoreDao scoreDao) {

        EventData accidentEvent = new EventData();
        accidentEvent.setEventName(Events.ACCIDENT.getEventName());

        List<EventData> events = new ArrayList<>();
        events.add(accidentEvent);
        KeyActivities keyActivities = KeyActivities.builder().activityId(Activities.SERVICING.getActivityId()).activityName(Activities.SERVICING.getActivityId()).total(10).events(null).build();

        return Collections.singletonList(keyActivities);


    }

    @Override
    public List<KeyFactorDataScore> getKeyFactorData(String assetId, ScoreDao scoreDao) {

        Integer lastUpdatedScore = scoreDao.getKeyFactorScores().get(KeyFactors.SERVICING);
        Integer currScore = servicingScoringUtil.calculateServiceActivityScore(assetId, lastUpdatedScore);
        Integer deltaConstraint = lastUpdatedScore - currScore;

        String delta = "";
        if (deltaConstraint > 0) {
            delta = "+".concat(deltaConstraint.toString());
        } else delta = deltaConstraint.toString();

        KeyFactorsData keyFactorsData = new KeyFactorsData();
        keyFactorsData.setImpact(ImpactType.LOW);
        keyFactorsData.setUsageCategory(ImpactCategory.EXCELLENT);
        keyFactorsData.setDelta(delta);

        KeyFactorDataScore keyFactorDataScore = KeyFactorDataScore.builder().keyFactorsData(keyFactorsData).score(currScore).build();
        return Collections.singletonList(keyFactorDataScore);
    }
}
