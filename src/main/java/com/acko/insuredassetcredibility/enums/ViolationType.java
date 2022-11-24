package com.acko.insuredassetcredibility.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

@AllArgsConstructor
@Getter
public enum ViolationType {

  DRINK_AND_DRIVE("Drink and Drive",-50,ImpactType.HIGH),
  OVER_LOADING_PILLION("Over loading pillion",-5,ImpactType.LOW),
  OVER_SPEEDING("Over Speeding",-20,ImpactType.MEDIUM),
  DANGEROUS_DRIVING("Dangerous Driving",-30,ImpactType.LOW),
  DRIVING_WITHOUT_LICENSE("Driving without license",-20,ImpactType.MEDIUM),
  DRIVING_WITHOUT_INSURANCE("Driving without insurance",-20,ImpactType.MEDIUM),
  SIGNAL_JUMPING("Signal jumping",-10,ImpactType.LOW),
  RIDING_WITHOUT_HELMET("Riding without helmet",-5,ImpactType.LOW),
  DRIVING_BY_MINOR("Driving by Minor",-20,ImpactType.MEDIUM),
  NO_PARKING("No parking",-10,ImpactType.LOW);

  private final String name;
  private final Integer score;
  private final ImpactType impactType;


  private static final HashMap<String,ViolationType> valueMap = new HashMap<>();

  static {
    for (ViolationType violationType:ViolationType.values()){
      valueMap.put(violationType.getName(), violationType);
    }
  }

  public static ViolationType get(String violationName){
    return valueMap.get(violationName);
  }

}
