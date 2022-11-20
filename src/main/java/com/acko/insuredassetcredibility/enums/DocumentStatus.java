/**
 * @author saurav roy
 * Date:20/11/22
 * Time:4:28 PM
 */
package com.acko.insuredassetcredibility.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum DocumentStatus {
    GOING_TO_EXPIRE_IN_60_DAYS,
    GOING_TO_EXPIRE_IN_10_DAYS,
    GOING_TO_EXPIRE_IN_30_DAYS,
    GOING_TO_EXPIRE_IN_90_DAYS,
    EXPIRED_10_DAYS_AGO,
    EXPIRED_30_DAYS_AGO,
    EXPIRED_60_DAYS_AGO,
    EXPIRED_90_DAYS_AGO

}
