/**
 * @author saurav roy
 * Date:20/11/22
 * Time:12:03 AM
 */
package com.acko.insuredassetcredibility.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KeyActivities {
    private Integer total;
    private String activityName;
    private String activityId;
    private String unitOfMeasurement;
    private List<EventData> events;
}
