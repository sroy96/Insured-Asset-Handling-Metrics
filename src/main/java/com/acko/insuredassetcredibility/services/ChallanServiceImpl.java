package com.acko.insuredassetcredibility.services;

import com.acko.insuredassetcredibility.constants.AppConstants;
import com.acko.insuredassetcredibility.dao.ChallanDao;
import com.acko.insuredassetcredibility.dao.ScoreDao;
import com.acko.insuredassetcredibility.enums.Activities;
import com.acko.insuredassetcredibility.enums.ImpactCategory;
import com.acko.insuredassetcredibility.enums.KeyFactors;
import com.acko.insuredassetcredibility.enums.ViolationType;
import com.acko.insuredassetcredibility.interfaces.ApplicationService;
import com.acko.insuredassetcredibility.models.*;
import com.acko.insuredassetcredibility.repository.ChallanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ChallanServiceImpl implements ApplicationService {



  @Autowired
  private ChallanRepository challanRepository;

  @Override
  public List<KeyActivities> getActivities(String assetId, ScoreDao scoreDao) {
    Map<String, EventData> eventDataMap = new HashMap<>();

    int totalCount = 0;
    LocalDateTime startDate = null;
    LocalDateTime endDate = null;

    List<ChallanDao> challanDaos = null;
    if (Objects.isNull(scoreDao))
      challanDaos = challanRepository.findByAssetId(assetId);
    else
      challanDaos = challanRepository.findByAssetIdAndUpdatedDateBetween(assetId,startDate,endDate);


    for (ChallanDao challanDao:challanDaos){
      for (ViolationDetail violationDetail:challanDao.getViolationDetails()){
        EventData eventData = eventDataMap.get(violationDetail.getOffense());
        if (Objects.isNull(eventData)){
          eventData = new EventData();
          eventData.setEventName(violationDetail.getOffense());
          eventData.setCount(1);
          List<BaseEventData> baseEventDataList = new ArrayList<>();
          baseEventDataList.add(BaseEventData.builder()
              .name(challanDao.getChallanNumber()).date(challanDao.getChallanDate()).build());
          eventData.setEventData(baseEventDataList);
          eventDataMap.put(violationDetail.getOffense(),eventData);
        }else {
          eventData.setCount(eventData.getCount()+1);
          List<BaseEventData> baseEventDataList = new ArrayList<>();
          baseEventDataList.add(BaseEventData.builder()
              .name(challanDao.getChallanNumber()).date(challanDao.getChallanDate()).build());
          eventData.setEventData(baseEventDataList);
        }
        totalCount++;
      }

    }

    List<EventData> eventDataList = new ArrayList<>();
    for (Map.Entry<String,EventData> eventData:eventDataMap.entrySet()){
      eventDataList.add(eventData.getValue());
    }
    KeyActivities keyActivities = KeyActivities.builder().activityId(Activities.CHALLANS.getActivityId())
        .activityName(Activities.CHALLANS.getActivityId()).total(totalCount).events(eventDataList).build();
    return Collections.singletonList(keyActivities);
  }

  @Override
  public List<KeyFactorDataScore> getKeyFactorData(String assetId, ScoreDao scoreDao) {
    Double initialScore = null;
    List<ChallanDao> challanDaos = null;
    if (Objects.isNull(scoreDao)) {
      initialScore = AppConstants.BASE_SCORE;
      challanDaos = challanRepository.findByAssetId(assetId);
    }
    else {
      initialScore = scoreDao.getKeyFactorScores().get(KeyFactors.CHALLAN);
      LocalDateTime startDate = scoreDao.getRefreshDate();
      LocalDateTime endDate = startDate.plusDays(AppConstants.THRESHOLD_DAYS);
      challanDaos = challanRepository.findByAssetIdAndUpdatedDateBetween(assetId,startDate,endDate);

    }
    Double calculatedScore = initialScore;
//    String lastDeltaStr = scoreDao.getKeyFactorsData().stream().filter(keyFactorsData -> keyFactorsData
//        .getFactorName().equals(Activities.CHALLANS.getActivity_id())).findFirst().get().getDelta();
//    Integer lastDelta = Integer.valueOf(lastDeltaStr);


    int totalEvents = 0;
    for (ChallanDao challanDao:challanDaos){
      for (ViolationDetail violationDetail:challanDao.getViolationDetails()){
        calculatedScore = calculatedScore + (Objects.isNull(ViolationType.get(violationDetail.getOffense()))?Integer.valueOf(0):
            ViolationType.get(violationDetail.getOffense()).getScore());
        totalEvents++;
      }
    }
    if (initialScore.equals(calculatedScore) ){
      calculatedScore = calculatedScore + AppConstants.NO_CHALLAN_BONUS_SCORE;
    }
    Double delta = calculatedScore - initialScore;
    KeyFactorsData keyFactorsData = new KeyFactorsData();
    keyFactorsData.setDelta(Integer.valueOf(delta.intValue()).toString());
    keyFactorsData.setUsageCategory(getImpactCategory(delta));
    keyFactorsData.setTotal(totalEvents);
    keyFactorsData.setKeyFactors(KeyFactors.CHALLAN);

    return Collections.singletonList(KeyFactorDataScore.builder().keyFactorsData(keyFactorsData).score(calculatedScore).build()) ;
  }

  public void save(ChallanDao challanDao){
    challanRepository.save(challanDao);
  }

  private ImpactCategory getImpactCategory(Double delta){
    if (delta>0){
      return ImpactCategory.EXCELLENT;
    }else if(delta>-30){
      return ImpactCategory.GOOD;
    }else {
      return ImpactCategory.POOR;
    }
  }
}
