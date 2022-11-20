/**
 * @author saurav roy
 * Date:20/11/22
 * Time:2:29 PM
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
public class FastTag {
    private String tollId;
    private Integer tollAmount;
    private Date tollEntryDate;
    private Long distanceTravelled;
}
