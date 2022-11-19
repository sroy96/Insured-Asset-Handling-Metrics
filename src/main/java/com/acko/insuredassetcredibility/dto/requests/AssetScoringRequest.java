/**
 * @author saurav roy
 * Date:19/11/22
 * Time:11:52 PM
 */
package com.acko.insuredassetcredibility.dto.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssetScoringRequest {
    private String mobile;
    private String email;
}
