/**
 * @author saurav roy
 * Date:20/11/22
 * Time:2:16 PM
 */
package com.acko.insuredassetcredibility.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ActivityType {
    CHALLAN,
    OUTSTATION_DISTANCE,
    AVG_MONTHLY_ODOMETER,
    SERVICING,
    CLAIMS,
    FIR
}
