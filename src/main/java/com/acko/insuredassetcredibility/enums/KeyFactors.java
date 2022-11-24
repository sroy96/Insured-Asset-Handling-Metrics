/**
 * @author saurav roy
 * Date:20/11/22
 * Time:2:16 PM
 */
package com.acko.insuredassetcredibility.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@AllArgsConstructor
public enum KeyFactors {
    CHALLAN("challan",0.2),
    DISTANCE_COMMUTED("distance_commute",0.2),
    SERVICING("servicing",0.2),
    CLAIMS("claims",0.2),
    FIR("FIR",0.2);

    private String keyFactorsValue;
    private Double weightage;

}
