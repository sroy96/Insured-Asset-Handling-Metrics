package com.acko.insuredassetcredibility.services;

import com.acko.insuredassetcredibility.constants.AppConstants;
import com.acko.insuredassetcredibility.dao.ScoreDao;
import com.acko.insuredassetcredibility.dao.interfaces.VehicleAccidentDao;
import com.acko.insuredassetcredibility.dao.interfaces.VehicleMaintenanceConditionDao;
import com.acko.insuredassetcredibility.dao.interfaces.VehicleRepairDao;
import com.acko.insuredassetcredibility.enums.*;
import com.acko.insuredassetcredibility.interfaces.ApplicationService;
import com.acko.insuredassetcredibility.models.*;
import com.acko.insuredassetcredibility.repository.VehicleAccidentRepository;
import com.acko.insuredassetcredibility.utils.scoring.ServicingScoringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MaintenanceServiceImpl implements ApplicationService {

    @Autowired
    private ServicingScoringUtil servicingScoringUtil;

    @Autowired
    private VehicleAccidentDao vehicleAccidentDao;

    @Autowired
    private VehicleRepairDao vehicleRepairDao;

    @Autowired
    private VehicleMaintenanceConditionDao vehicleMaintenanceConditionDao;

    @Override
    public List<KeyActivities> getActivities(String assetId, ScoreDao scoreDao) {

        List<VehicleAccident> vehicleAccident = Objects.isNull(scoreDao) ? vehicleAccidentDao.getVehicleAccident(assetId) : vehicleAccidentDao.getVehicleAccident(assetId).stream().filter(
                d -> (ChronoUnit.DAYS.between(scoreDao.getRefreshDate(), d.getLastRefreshedTime()) <= AppConstants.REFRESH_PERIOD)
        ).collect(Collectors.toList());

        List<VehicleRepair> vehicleRepair = Objects.isNull(scoreDao) ? vehicleRepairDao.getVehicleRepair(assetId) : vehicleRepairDao.getVehicleRepair(assetId).stream().filter(
                d -> (ChronoUnit.DAYS.between(scoreDao.getRefreshDate(), d.getLastRefreshedTime()) <= AppConstants.REFRESH_PERIOD)
        ).collect(Collectors.toList());

        List<VehicleMaintenanceCondition> vehicleMaintenanceCondition = Objects.isNull(scoreDao) ? vehicleMaintenanceConditionDao.getVehicleMaintenanceCondition(assetId) : vehicleMaintenanceConditionDao.getVehicleMaintenanceCondition(assetId).stream().filter(
                d -> (ChronoUnit.DAYS.between(scoreDao.getRefreshDate(), d.getLastRefreshedTime()) <= AppConstants.REFRESH_PERIOD)
        ).collect(Collectors.toList());

        EventData accident = EventData.builder().eventName(Events.ACCIDENT.getEventName()).count(vehicleAccident.size()).build();
        EventData repair = EventData.builder().eventName(Events.REPAIR.getEventName()).count(vehicleRepair.size()).build();
        EventData maintenance = EventData.builder().eventName(Events.MAINTENANCE.getEventName()).count(vehicleMaintenanceCondition.size()).build();

        List<EventData> events = new ArrayList<>();
        events.add(accident);
        events.add(repair);
        events.add(maintenance);
        KeyActivities keyActivities = KeyActivities.builder().activityId(Activities.SERVICING.getActivityId()).activityName(Activities.SERVICING.getActivityId()).total(10).events(events).build();
        return Collections.singletonList(keyActivities);
    }

    @Override
    public List<KeyFactorDataScore> getKeyFactorData(String assetId, ScoreDao scoreDao) {

        Integer lastUpdatedScore = scoreDao == null ? AppConstants.BASE_SCORE : scoreDao.getKeyFactorScores().get(KeyFactors.SERVICING);
        Integer currScore = servicingScoringUtil.calculateServiceActivityScore(assetId, lastUpdatedScore);
        Integer deltaConstraint = lastUpdatedScore - currScore;

        String delta = "";
        if (deltaConstraint > 0) {
            delta = "-".concat(deltaConstraint.toString());
        } else {
            deltaConstraint = Math.abs(deltaConstraint);
            delta = "+".concat(deltaConstraint.toString());
        }

        KeyFactorsData keyFactorsData = new KeyFactorsData();
        keyFactorsData.setKeyFactor(KeyFactors.SERVICING);
        keyFactorsData.setImpact(ImpactType.LOW);
        keyFactorsData.setUsageCategory(servicingScoringUtil.getUsageImpact(deltaConstraint));
        keyFactorsData.setDelta(delta);
        KeyFactorDataScore keyFactorDataScore = KeyFactorDataScore.builder().keyFactorsData(keyFactorsData).score(currScore).build();
        return Collections.singletonList(keyFactorDataScore);
    }
}
