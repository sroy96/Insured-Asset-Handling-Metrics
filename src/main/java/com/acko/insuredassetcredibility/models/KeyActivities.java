/**
 * @author saurav roy
 * Date:20/11/22
 * Time:12:03 AM
 */
package com.acko.insuredassetcredibility.models;

import com.acko.insuredassetcredibility.enums.ActivityType;
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
public class KeyActivities {
    private Map<ActivityType, ActivityData> keyActivities;
}
