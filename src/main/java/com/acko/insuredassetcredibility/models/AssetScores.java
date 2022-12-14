/**
 * @author saurav roy
 * Date:20/11/22
 * Time:12:01 AM
 */
package com.acko.insuredassetcredibility.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssetScores {
    private String assetId;
    private String assetName;
    private Integer score;
    private List<KeyActivities> keyActivities;
    @JsonProperty("key_factor")
    private List<KeyFactorsData> keyFactorsData;
}
