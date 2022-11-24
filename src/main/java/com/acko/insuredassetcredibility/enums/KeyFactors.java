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
    CHALLAN("challan"),
    DISTANCE_COMMUTED("distance_commute"),
    SERVICING("servicing"),
    CLAIMS("claims"),
    FIR("FIR");

    private String keyFactorsValue;

}
