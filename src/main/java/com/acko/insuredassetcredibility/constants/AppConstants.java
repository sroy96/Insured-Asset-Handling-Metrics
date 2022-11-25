/**
 * @author saurav roy
 * Date:20/11/22
 * Time:2:17 PM
 */
package com.acko.insuredassetcredibility.constants;

import lombok.Getter;

@Getter
public class AppConstants {
    public static final Integer BASE_SCORE                                      = 850;
    public static final Double OUTSTATION_COMMUTE__MONTHLY_THRESHOLD_IN_KM      = 500.0;
    public static final Integer VEHICLE_SERVICING_FACTOR_COUNT                  = 3;
    public static final Long REFRESH_PERIOD                                     = 45L;
    public static final Long REFRESH_PERIOD_MINUTES                             = 2L;

    public static final Integer THRESHOLD_DAYS                                  = 45;
    public static final Integer NO_CHALLAN_BONUS_SCORE                          = 30;
    public static final String NO_CHALLAN_BONUS                                 = "No Challan Bonus";
    public static final String RESPONSE_DATE_FORMAT                             = "yyyy/MM/dd";
    public static final Integer MIN_SCORE                                       = 300;
    public static final Integer MAX_SCORE                                       = 1000;
}
