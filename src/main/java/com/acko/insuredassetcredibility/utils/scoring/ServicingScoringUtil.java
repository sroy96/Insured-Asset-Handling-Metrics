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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicingScoringUtil{

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

    public Integer calculateServiceActivityScore(String assetId, int originalVehicleServicingScore) {
        LocalDateTime currDate = LocalDateTime.now();
        int scoreFactor = originalVehicleServicingScore / AppConstants.VEHICLE_SERVICING_FACTOR_COUNT;
        int initialAccidentScore = scoreFactor;
        int initialRepairScore = scoreFactor;
        int initialMaintenanceConditionScore = scoreFactor;

        int vehicleAccidentScore = getVehicleAccidentScore(assetId, initialAccidentScore, currDate);
        int vehicleRepairScore = getVehicleRepairScore(assetId, initialRepairScore, currDate);
        int vehicleMaintenanceConditionScore = getVehicleMaintenanceConditionScore(assetId, initialMaintenanceConditionScore, currDate);

        return Math.min(originalVehicleServicingScore, (vehicleAccidentScore + vehicleRepairScore + vehicleMaintenanceConditionScore));
    }

    private Integer getVehicleAccidentScore(String assetId, int initialAccidentScore, LocalDateTime currDate) {

        int modifiedAccidentScore = initialAccidentScore;
        List<VehicleAccident> vehicleAccident = vehicleAccidentDao.getVehicleAccident(assetId).stream().filter(
                d -> (ChronoUnit.DAYS.between(currDate, d.getLastRefreshedTime()) == AppConstants.REFRESH_PERIOD)
        ).collect(Collectors.toList());

        for (VehicleAccident accidentData : vehicleAccident) {

            if (accidentData.getMajorAccidentDate() != null) modifiedAccidentScore = modifiedAccidentScore - 10;
            else if (accidentData.getMinorAccidentDate() != null) modifiedAccidentScore = modifiedAccidentScore - 5;
        }

        return modifiedAccidentScore;
    }

    private Integer getVehicleRepairScore(String assetId, int initialRepairScore, LocalDateTime currDate) {

        int modifiedRepairScore = initialRepairScore;
        List<VehicleRepair> vehicleRepair = vehicleRepairDao.getVehicleRepair(assetId).stream().filter(
                d -> (ChronoUnit.DAYS.between(currDate, d.getLastRefreshedTime()) == AppConstants.REFRESH_PERIOD)
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

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesEngineOilChanged++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
            if (repairData.getRightTurnSignalLightRepairDate() != null) {
                if (noOfTimesRightTurnSignalLightRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getRightTurnSignalLightRepairDate()) >= 30) {

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesRightTurnSignalLightRepaired++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
            if (repairData.getTransmissionRepairDate() != null) {
                if (noOfTimesTransmissionRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getTransmissionRepairDate()) >= 30) {

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesTransmissionRepaired++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
            if (repairData.getAirFilterRepairDate() != null) {
                if (noOfTimesAirFilterRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getAirFilterRepairDate()) >= 30) {

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesAirFilterRepaired++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
            if (repairData.getSparkPlugRepairDate() != null) {
                if (noOfTimesSparkPlugRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getSparkPlugRepairDate()) >= 30) {

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesSparkPlugRepaired++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
            if (repairData.getShockAbsorbersRepairDate() != null) {
                if (noOfTimesShockAbsorbersRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getShockAbsorbersRepairDate()) >= 30) {

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesShockAbsorbersRepaired++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
            if (repairData.getBatteryChangedDate() != null) {
                if (noOfTimesBatteryChanged < 1 && ChronoUnit.DAYS.between(currDate, repairData.getBatteryChangedDate()) >= 30) {

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesBatteryChanged++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
            if (repairData.getWindShieldWiperChangeDate() != null) {
                if (noOfTimesWindShieldWiperChanged < 1 && ChronoUnit.DAYS.between(currDate, repairData.getWindShieldWiperChangeDate()) >= 30) {

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesWindShieldWiperChanged++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
            if (repairData.getParkingLightRepairDate() != null) {
                if (noOfTimesParkingLightRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getParkingLightRepairDate()) >= 30) {

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesParkingLightRepaired++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
            if (repairData.getBrakeLightRepairDate() != null) {
                if (noOfTimesBrakeLightRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getBrakeLightRepairDate()) >= 30) {

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesBrakeLightRepaired++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
            if (repairData.getHeadLightRepairDate() != null) {
                if (noOfTimesHeadLightRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getHeadLightRepairDate()) >= 30) {

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesHeadLightRepaired++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
            if (repairData.getLeftTurnSignalLightRepairDate() != null) {
                if (noOfTimesLeftTurnSignalLightRepaired < 1 && ChronoUnit.DAYS.between(currDate, repairData.getLeftTurnSignalLightRepairDate()) >= 30) {

                    modifiedRepairScore = modifiedRepairScore + 2;
                    noOfTimesLeftTurnSignalLightRepaired++;

                } else {
                    modifiedRepairScore = modifiedRepairScore - 5;
                }
            }
        }

        return modifiedRepairScore;

    }

    private Integer getVehicleMaintenanceConditionScore(String assetId, int initialMaintenanceScore, LocalDateTime currDate) {

        int modifiedMaintenanceScore = initialMaintenanceScore;
        List<VehicleMaintenanceCondition> vehicleMaintenanceCondition = vehicleMaintenanceConditionDao.getVehicleMaintenanceCondition(assetId).stream().filter(
                d -> (ChronoUnit.DAYS.between(currDate, d.getLastRefreshedTime()) == AppConstants.REFRESH_PERIOD)
        ).collect(Collectors.toList());

        for (VehicleMaintenanceCondition maintenanceConditionData : vehicleMaintenanceCondition) {

            if (maintenanceConditionData.getBrakeLightCondition() == ServicingStatus.EXCELLENT)
                modifiedMaintenanceScore = modifiedMaintenanceScore + 5;

            else if (maintenanceConditionData.getBrakeLightCondition() == ServicingStatus.GOOD)
                modifiedMaintenanceScore = modifiedMaintenanceScore + 2;

            else if (maintenanceConditionData.getBrakeLightCondition() == ServicingStatus.NEED_REPAIR)
                modifiedMaintenanceScore = modifiedMaintenanceScore - 2;

            else if (maintenanceConditionData.getBrakeLightCondition() == ServicingStatus.NON_FUNCTIONAL)
                modifiedMaintenanceScore = modifiedMaintenanceScore - 5;

            if (maintenanceConditionData.getAirFilterCondition() == ServicingStatus.EXCELLENT)
                modifiedMaintenanceScore = modifiedMaintenanceScore + 5;

            else if (maintenanceConditionData.getAirFilterCondition() == ServicingStatus.GOOD)
                modifiedMaintenanceScore = modifiedMaintenanceScore + 2;

            else if (maintenanceConditionData.getAirFilterCondition() == ServicingStatus.NEED_REPAIR)
                modifiedMaintenanceScore = modifiedMaintenanceScore - 2;

            else if (maintenanceConditionData.getAirFilterCondition() == ServicingStatus.NON_FUNCTIONAL)
                modifiedMaintenanceScore = modifiedMaintenanceScore - 5;

            if (maintenanceConditionData.getParkingLightCondition() == ServicingStatus.EXCELLENT)
                modifiedMaintenanceScore = modifiedMaintenanceScore + 5;

            else if (maintenanceConditionData.getParkingLightCondition() == ServicingStatus.GOOD)
                modifiedMaintenanceScore = modifiedMaintenanceScore + 2;

            else if (maintenanceConditionData.getParkingLightCondition() == ServicingStatus.NEED_REPAIR)
                modifiedMaintenanceScore = modifiedMaintenanceScore - 2;

            else if (maintenanceConditionData.getParkingLightCondition() == ServicingStatus.NON_FUNCTIONAL)
                modifiedMaintenanceScore = modifiedMaintenanceScore - 5;

        }

        return modifiedMaintenanceScore;
    }
}