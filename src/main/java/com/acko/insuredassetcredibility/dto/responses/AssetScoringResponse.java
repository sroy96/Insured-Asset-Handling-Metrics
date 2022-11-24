/**
 * @author saurav roy
 * Date:19/11/22
 * Time:11:53 PM
 */
package com.acko.insuredassetcredibility.dto.responses;

import com.acko.insuredassetcredibility.models.AssetScores;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssetScoringResponse {
    @JsonProperty(value = "data")
    List<AssetScores> assetScoresList;
    private String status = "200";
}
