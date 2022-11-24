/**
 * @author saurav roy
 * Date:22/11/22
 * Time:11:45 PM
 */
package com.acko.insuredassetcredibility.dao.acitivity;

import com.acko.insuredassetcredibility.enums.Activities;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Document
public class OutStationActivity {
    @Id
    private String Id;
    private String assetId;
    private String tollId;
    private Integer tollAmount;
    private Double longitude;
    private Double latitude;
    private LocalDateTime tollEntryDate;

}
