/**
 * @author saurav roy
 * Date:20/11/22
 * Time:12:02 AM
 */
package com.acko.insuredassetcredibility.models;

import com.acko.insuredassetcredibility.enums.ImpactCategory;
import com.acko.insuredassetcredibility.enums.ImpactType;
import com.acko.insuredassetcredibility.enums.KeyFactors;
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
public class KeyFactorsData {
    private KeyFactors keyFactor;
    private ImpactType impact;
    private ImpactCategory usageCategory;
    private String delta;
    private Integer total;
}
