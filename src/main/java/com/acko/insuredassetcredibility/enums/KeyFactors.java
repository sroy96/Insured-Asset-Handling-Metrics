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
    CHALLAN("challan",0.3),
    DISTANCE_COMMUTED("distance_commute",0.6),
    SERVICING("servicing",0.1),
    CLAIMS("claims",0.0),
    FIR("FIR",0.0);

    private String keyFactorsValue;
    private Double weightage;

}
