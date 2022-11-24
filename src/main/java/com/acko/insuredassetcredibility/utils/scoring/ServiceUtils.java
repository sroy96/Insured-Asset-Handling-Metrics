/**
 * @author saurav roy
 * Date:23/11/22
 * Time:11:02 PM
 */
package com.acko.insuredassetcredibility.utils.scoring;

import com.acko.insuredassetcredibility.constants.AppConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceUtils {

    public static LocalDateTime getNewRefreshDate(LocalDateTime lastRefreshDate){
        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        LocalDateTime now = LocalDateTime.now();
        return now.minusDays(AppConstants.REFRESH_PERIOD);
    }
}
