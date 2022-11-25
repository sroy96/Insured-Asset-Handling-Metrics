/**
 * @author saurav roy
 * Date:21/11/22
 * Time:12:29 AM
 */
package com.acko.insuredassetcredibility.utils.scoring;

import com.acko.insuredassetcredibility.constants.AppConstants;
import com.acko.insuredassetcredibility.dao.ScoreDao;
import com.acko.insuredassetcredibility.dao.interfaces.VehicleAccidentDao;
import com.acko.insuredassetcredibility.dao.interfaces.VehicleMaintenanceConditionDao;
import com.acko.insuredassetcredibility.dao.interfaces.VehicleRepairDao;
import com.acko.insuredassetcredibility.enums.*;
import com.acko.insuredassetcredibility.interfaces.ScoringService;
import com.acko.insuredassetcredibility.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServicingScoringUtil {

    @Autowired
    private VehicleAccidentDao vehicleAccidentDao;

    @Autowired
    private VehicleRepairDao vehicleRepairDao;

    @Autowired
    private VehicleMaintenanceConditionDao vehicleMaintenanceConditionDao;

    @Autowired
    private KeyFactorDataScore keyFactorDataScore;

    @Autowired
    private KeyActivities keyActivities;

    public static Integer calculatedScore = AppConstants.BASE_SCORE;

    public Integer calculateServiceActivityScore(String assetId, Integer lastUpdatedScore) {

        calculatedScore = lastUpdatedScore;
        LocalDateTime currDate = LocalDateTime.now();
        this.getVehicleAccidentScore(assetId, currDate);
        this.getVehicleRepairScore(assetId, currDate);
        this.getVehicleMaintenanceConditionScore(assetId, currDate);
        if (calculatedScore < AppConstants.MIN_SCORE) return AppConstants.MIN_SCORE;
        else if (calculatedScore > AppConstants.MAX_SCORE) return AppConstants.MAX_SCORE;
        else return calculatedScore;
    }

    private Integer getVehicleAccidentScore(String assetId, LocalDateTime currDate) {

        List<VehicleAccident> vehicleAccident = vehicleAccidentDao.getVehicleAccident(assetId).stream().filter(
                d -> (ChronoUnit.MINUTES.between(currDate, d.getLastRefreshedTime()) <= AppConstants.REFRESH_PERIOD_MINUTES)
        ).collect(Collectors.toList());

        for (VehicleAccident accidentData : vehicleAccident) {

            if (accidentData.getMajorAccidentDate() != null) calculatedScore = calculatedScore - 10;
            else if (accidentData.getMinorAccidentDate() != null) calculatedScore = calculatedScore - 5;
        }

        return calculatedScore;
    }

    private Integer getVehicleRepairScore(String assetId, LocalDateTime currDate) {

        List<VehicleRepair> vehicleRepair = vehicleRepairDao.getVehicleRepair(assetId).stream().filter(
                d -> (ChronoUnit.MINUTES.between(currDate, d.getLastRefreshedTime()) <= AppConstants.REFRESH_PERIOD_MINUTES)
        ).collect(Collectors.toList());

        int noOfTimesEngineOilChanged = 0;
        int noOfTimesRightTurnSignalLightRepaired = 0;
        int noOfTimesTransmissionRepaired = 0;
        int noOfTimesAirFilterRepaired = 0;
        int noOfTimesSparkPlugRepaired = 0;
        int noOfTimesShockAbsorbersRepaired = 0;
        int noOfTimesBatteryChanged = 0;
        int noOfTimesWindShieldWiperChanged = 0;
        int noOfTimesParkingLightRepaired = 0;
        int noOfTimesBrakeLightRepaired = 0;
        int noOfTimesHeadLightRepaired = 0;
        int noOfTimesLeftTurnSignalLightRepaired = 0;

        for (VehicleRepair repairData : vehicleRepair) {

            if (repairData.getEngineOilChangeDate() != null) {
                if (noOfTimesEngineOilChanged < 1 && ChronoUnit.DAYS.between(currDate, repairData.getEngineOilChangeDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesEngineOilChanged++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
            if (repairData.getRightTurnSignalLightRepairDate() != null) {
                if (noOfTimesRightTurnSignalLightRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getRightTurnSignalLightRepairDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesRightTurnSignalLightRepaired++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
            if (repairData.getTransmissionRepairDate() != null) {
                if (noOfTimesTransmissionRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getTransmissionRepairDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesTransmissionRepaired++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
            if (repairData.getAirFilterRepairDate() != null) {
                if (noOfTimesAirFilterRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getAirFilterRepairDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesAirFilterRepaired++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
            if (repairData.getSparkPlugRepairDate() != null) {
                if (noOfTimesSparkPlugRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getSparkPlugRepairDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesSparkPlugRepaired++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
            if (repairData.getShockAbsorbersRepairDate() != null) {
                if (noOfTimesShockAbsorbersRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getShockAbsorbersRepairDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesShockAbsorbersRepaired++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
            if (repairData.getBatteryChangedDate() != null) {
                if (noOfTimesBatteryChanged < 1 && ChronoUnit.DAYS.between(currDate, repairData.getBatteryChangedDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesBatteryChanged++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
            if (repairData.getWindShieldWiperChangeDate() != null) {
                if (noOfTimesWindShieldWiperChanged < 1 && ChronoUnit.DAYS.between(currDate, repairData.getWindShieldWiperChangeDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesWindShieldWiperChanged++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
            if (repairData.getParkingLightRepairDate() != null) {
                if (noOfTimesParkingLightRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getParkingLightRepairDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesParkingLightRepaired++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
            if (repairData.getBrakeLightRepairDate() != null) {
                if (noOfTimesBrakeLightRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getBrakeLightRepairDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesBrakeLightRepaired++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
            if (repairData.getHeadLightRepairDate() != null) {
                if (noOfTimesHeadLightRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getHeadLightRepairDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesHeadLightRepaired++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
            if (repairData.getLeftTurnSignalLightRepairDate() != null) {
                if (noOfTimesLeftTurnSignalLightRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getLeftTurnSignalLightRepairDate()) >= 30) {

                    calculatedScore = calculatedScore + 2;
                    noOfTimesLeftTurnSignalLightRepaired++;

                } else {
                    calculatedScore = calculatedScore - 5;
                }
            }
        }

        return calculatedScore;

    }

    private Integer getVehicleMaintenanceConditionScore(String assetId, LocalDateTime currDate) {

        List<VehicleMaintenanceCondition> vehicleMaintenanceCondition = vehicleMaintenanceConditionDao.getVehicleMaintenanceCondition(assetId).stream().filter(
                d -> (ChronoUnit.MINUTES.between(currDate, d.getLastRefreshedTime()) <= AppConstants.REFRESH_PERIOD_MINUTES)
        ).collect(Collectors.toList());

        for (VehicleMaintenanceCondition maintenanceConditionData : vehicleMaintenanceCondition) {

            if (maintenanceConditionData.getBrakeLightCondition() == ServicingStatus.EXCELLENT)
                calculatedScore = calculatedScore + 5;

            else if (maintenanceConditionData.getBrakeLightCondition() == ServicingStatus.GOOD)
                calculatedScore = calculatedScore + 2;

            else if (maintenanceConditionData.getBrakeLightCondition() == ServicingStatus.NEED_REPAIR)
                calculatedScore = calculatedScore - 2;

            else if (maintenanceConditionData.getBrakeLightCondition() == ServicingStatus.NON_FUNCTIONAL)
                calculatedScore = calculatedScore - 5;

            if (maintenanceConditionData.getAirFilterCondition() == ServicingStatus.EXCELLENT)
                calculatedScore = calculatedScore + 5;

            else if (maintenanceConditionData.getAirFilterCondition() == ServicingStatus.GOOD)
                calculatedScore = calculatedScore + 2;

            else if (maintenanceConditionData.getAirFilterCondition() == ServicingStatus.NEED_REPAIR)
                calculatedScore = calculatedScore - 2;

            else if (maintenanceConditionData.getAirFilterCondition() == ServicingStatus.NON_FUNCTIONAL)
                calculatedScore = calculatedScore - 5;

            if (maintenanceConditionData.getParkingLightCondition() == ServicingStatus.EXCELLENT)
                calculatedScore = calculatedScore + 5;

            else if (maintenanceConditionData.getParkingLightCondition() == ServicingStatus.GOOD)
                calculatedScore = calculatedScore + 2;

            else if (maintenanceConditionData.getParkingLightCondition() == ServicingStatus.NEED_REPAIR)
                calculatedScore = calculatedScore - 2;

            else if (maintenanceConditionData.getParkingLightCondition() == ServicingStatus.NON_FUNCTIONAL)
                calculatedScore = calculatedScore - 5;

        }

        return calculatedScore;
    }

    public ImpactCategory getUsageImpact(Integer deltaConstraint) {

        if (deltaConstraint < -30) return ImpactCategory.EXCELLENT;
        else if (deltaConstraint <= 0) return ImpactCategory.GOOD;
        else return ImpactCategory.POOR;
    }
}