/**
 * @author saurav roy
 * Date:20/11/22
 * Time:12:01 AM
 */
package com.acko.insuredassetcredibility.models;

import com.acko.insuredassetcredibility.enums.Activities;
import com.acko.insuredassetcredibility.enums.KeyFactors;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssetScores {
    private String assetId;
    private Double score;
    private List<KeyActivities> keyActivities;
    private List<KeyFactorsData> keyFactorsData;
}
