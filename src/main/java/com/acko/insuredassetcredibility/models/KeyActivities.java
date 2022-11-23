/**
 * @author saurav roy
 * Date:20/11/22
 * Time:12:03 AM
 */
package com.acko.insuredassetcredibility.models;

import com.acko.insuredassetcredibility.enums.KeyFactors;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;
import java.util.Map;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KeyActivities {
    private Integer total;
    private String activityName;
    private String unitOfMeasurement;
    private List<EventData> events;
}
