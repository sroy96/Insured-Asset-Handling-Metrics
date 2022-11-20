/**
 * @author saurav roy
 * Date:20/11/22
 * Time:2:35 PM
 */
package com.acko.insuredassetcredibility.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Map;
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OutstationCommuteFactorDelta extends KeyFactorsDelta{
    Map<Integer,Double> monthlyOutStationCommute;
}
