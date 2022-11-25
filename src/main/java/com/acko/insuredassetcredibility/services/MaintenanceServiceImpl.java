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
import com.acko.insuredassetcredibility.repository.VehicleMaintenanceRepository;
import com.acko.insuredassetcredibility.repository.VehicleRepairRepository;
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

    @Autowired
    private VehicleAccidentRepository vehicleAccidentRepository;

    @Autowired
    private VehicleRepairRepository vehicleRepairRepository;

    @Autowired
    private VehicleMaintenanceRepository vehicleMaintenanceRepository;
    @Override
    public List<KeyActivities> getActivities(String assetId, ScoreDao scoreDao) {

        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        if (!Objects.isNull(scoreDao)) {

            startDate = scoreDao.getRefreshDate();
            endDate = startDate.plusMinutes(AppConstants.REFRESH_PERIOD_MINUTES);
        }

        List<VehicleAccident> vehicleAccident = vehicleAccidentDao.getVehicleAccident(assetId);
        List<VehicleRepair> vehicleRepair = vehicleRepairDao.getVehicleRepair(assetId);
        List<VehicleMaintenanceCondition> vehicleMaintenanceCondition = vehicleMaintenanceConditionDao.getVehicleMaintenanceCondition(assetId);

        List<BaseEventData> accidentBaseEventDataList = new ArrayList<>();
        for (VehicleAccident accident : vehicleAccident) {

            BaseEventData data = BaseEventData.builder().name(accident.getServiceCenterName()).date(accident.getLastRefreshedTime()).build();
            accidentBaseEventDataList.add(data);
        }

        List<BaseEventData> repairBaseEventDataList = new ArrayList<>();
        for (VehicleRepair repair : vehicleRepair) {

            BaseEventData data = BaseEventData.builder().name(repair.getServiceCenterName()).date(repair.getLastRefreshedTime()).build();
            repairBaseEventDataList.add(data);
        }

        List<BaseEventData> maintenanceBaseEventDataList = new ArrayList<>();
        for (VehicleMaintenanceCondition maintenance : vehicleMaintenanceCondition) {

            BaseEventData data = BaseEventData.builder().name(maintenance.getServiceCenterName()).date(maintenance.getLastRefreshedTime()).build();
            maintenanceBaseEventDataList.add(data);
        }

        EventData accident = EventData.builder().eventName(Events.ACCIDENT.getEventName()).count(vehicleAccident.size()).eventData(accidentBaseEventDataList).build();
        EventData repair = EventData.builder().eventName(Events.REPAIR.getEventName()).count(vehicleRepair.size()).eventData(repairBaseEventDataList).build();
        EventData maintenance = EventData.builder().eventName(Events.MAINTENANCE.getEventName()).count(vehicleMaintenanceCondition.size()).eventData(maintenanceBaseEventDataList).build();

        List<EventData> events = new ArrayList<>();
        events.add(accident);
        events.add(repair);
        events.add(maintenance);

        int count = vehicleAccident.size() + vehicleRepair.size() + vehicleMaintenanceCondition.size();
        KeyActivities keyActivities = KeyActivities.builder().activityId(Activities.SERVICING.getActivityId()).activityName(Activities.SERVICING.getActivityId()).total(count).unitOfMeasurement("number").events(events).build();
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
        } else if (deltaConstraint < 0) {
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
