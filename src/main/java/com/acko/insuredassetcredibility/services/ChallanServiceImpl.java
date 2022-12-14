package com.acko.insuredassetcredibility.services;

import com.acko.insuredassetcredibility.constants.AppConstants;
import com.acko.insuredassetcredibility.dao.ChallanDao;
import com.acko.insuredassetcredibility.dao.ScoreDao;
import com.acko.insuredassetcredibility.enums.*;
import com.acko.insuredassetcredibility.interfaces.ApplicationService;
import com.acko.insuredassetcredibility.models.*;
import com.acko.insuredassetcredibility.repository.ChallanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    List<ChallanDao> challanDaos = challanRepository.findByAssetId(assetId);;



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
    KeyActivities keyActivities = KeyActivities.builder().activityId(Activities.CHALLANS.name())
        .activityName(Activities.CHALLANS.getActivityId()).total(totalCount).unitOfMeasurement("number").events(eventDataList).build();
    return Collections.singletonList(keyActivities);
  }

  @Override
  public List<KeyFactorDataScore> getKeyFactorData(String assetId, ScoreDao scoreDao) {
    Integer initialScore = null;
    List<ChallanDao> challanDaos = challanRepository.findByAssetId(assetId);;

//    String lastDeltaStr = scoreDao.getKeyFactorsData().stream().filter(keyFactorsData -> keyFactorsData
//        .getFactorName().equals(Activities.CHALLANS.getActivity_id())).findFirst().get().getDelta();
//    Integer lastDelta = Integer.valueOf(lastDeltaStr);

    if (Objects.isNull(scoreDao)){
      initialScore = AppConstants.BASE_SCORE;
    }else {
      initialScore = Objects.isNull(scoreDao.getKeyFactorScores())?AppConstants.BASE_SCORE: scoreDao.getKeyFactorScores().get(KeyFactors.CHALLAN);
    }

    Integer calculatedScore = initialScore;
    int totalEvents = 0;
    for (ChallanDao challanDao:challanDaos){
      for (ViolationDetail violationDetail:challanDao.getViolationDetails()){
        calculatedScore = calculatedScore + (Objects.isNull(ViolationType.get(violationDetail.getOffense()))?Integer.valueOf(-5):
            ViolationType.get(violationDetail.getOffense()).getScore());
        totalEvents++;
      }
    }
    calculatedScore = calculatedScore>AppConstants.MAX_SCORE?AppConstants.MAX_SCORE:calculatedScore;
    calculatedScore = calculatedScore<AppConstants.MIN_SCORE?AppConstants.MIN_SCORE:calculatedScore;
    Integer delta = calculatedScore - initialScore;
    KeyFactorsData keyFactorsData = new KeyFactorsData();
    keyFactorsData.setDelta(delta>0? "+".concat(delta.toString()) :delta.toString());
    keyFactorsData.setKeyFactor(KeyFactors.CHALLAN);
    keyFactorsData.setImpact(ImpactType.MEDIUM);
    keyFactorsData.setUsageCategory(getImpactCategory(delta));
    keyFactorsData.setTotal(totalEvents);
    keyFactorsData.setKeyFactor(KeyFactors.CHALLAN);


    return Collections.singletonList(KeyFactorDataScore.builder().keyFactorsData(keyFactorsData).score(calculatedScore).build()) ;
  }

  public void save(ChallanDao challanDao){
    challanRepository.save(challanDao);
  }

  private ImpactCategory getImpactCategory(Integer delta){
    if (delta>0){
      return ImpactCategory.EXCELLENT;
    }else if(delta>-30){
      return ImpactCategory.GOOD;
    }else {
      return ImpactCategory.POOR;
    }
  }
}
