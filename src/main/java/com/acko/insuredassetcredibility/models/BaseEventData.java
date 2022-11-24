/**
 * @author saurav roy
 * Date:22/11/22
 * Time:4:53 PM
 */
package com.acko.insuredassetcredibility.models;

import com.acko.insuredassetcredibility.constants.AppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BaseEventData {
    String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.RESPONSE_DATE_FORMAT)
    LocalDateTime date;
}
