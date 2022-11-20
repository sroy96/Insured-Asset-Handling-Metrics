/**
 * @author saurav roy
 * Date:20/11/22
 * Time:2:27 PM
 */
package com.acko.insuredassetcredibility.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Date;
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Insurance {
    private String insurerName;
    private Date expiryDate;
    //TODO: Praful Add these please.
//    private Covers covers;
//    private PlanType planType;
//    private ClaimData claimData;
}
