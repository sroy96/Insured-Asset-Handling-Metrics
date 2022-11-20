/**
 * @author saurav roy
 * Date:20/11/22
 * Time:4:38 PM
 */
package com.acko.insuredassetcredibility.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OutStationCommuteActivity extends ActivityData{
    private Double approximateOutStationDistanceCovered;
}
