/**
 * @author saurav roy
 * Date:22/11/22
 * Time:4:43 PM
 */
package com.acko.insuredassetcredibility.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum Activities {
    OUTSTATION_COMMUTE("outstation"),
    CHALLANS("challan"),
    SERVICING("service"),
    ODOMETER_READING("odometre");

    private String activityId;

    Activities(String activityId) {
        this.activityId = activityId;
    }
}
