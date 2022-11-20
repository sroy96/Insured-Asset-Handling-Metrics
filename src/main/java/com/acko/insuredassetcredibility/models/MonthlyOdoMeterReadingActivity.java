/**
 * @author saurav roy
 * Date:20/11/22
 * Time:5:25 PM
 */
package com.acko.insuredassetcredibility.models;

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
public class MonthlyOdoMeterReadingActivity extends ActivityData{
    Double averageMonthlyOdometerReading;
}
