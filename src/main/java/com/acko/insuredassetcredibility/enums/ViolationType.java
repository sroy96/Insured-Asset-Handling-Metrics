package com.acko.insuredassetcredibility.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

@AllArgsConstructor
@Getter
public enum ViolationType {

  DRINK_AND_DRIVE("Drink and Drive",-100,ImpactType.HIGH),
  OVER_SPEEDING("Over Speeding",-20,ImpactType.MEDIUM),
  SIGNAL_JUMPING("Signal jumping",-20,ImpactType.LOW),
  RIDING_WITHOUT_HELMET("Riding without helmet",-5,ImpactType.LOW),
  BONUS_SCORE("Bonus score",50,ImpactType.LOW),
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
