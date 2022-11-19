/**
 * @author saurav roy
 * Date:20/11/22
 * Time:12:02 AM
 */
package com.acko.insuredassetcredibility.models;

import com.acko.insuredassetcredibility.enums.ImpactCategory;
import com.acko.insuredassetcredibility.enums.ImpactType;
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
public class KeyFactorsDelta<T> {
    private String factor;
    private ImpactType impactType;
    private Double impactValue;
    private ImpactCategory impactCategory;
    private String descriptions;
    private T meta;

}
