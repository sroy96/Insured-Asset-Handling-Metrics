/**
 * @author saurav roy
 * Date:20/11/22
 * Time:4:35 PM
 */
package com.acko.insuredassetcredibility.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class FastTagActivity {
    @Id
    private String assetId;
    private String tollId;
    private Integer tollAmount;
    private Double longitude;
    private Double latitude;
    private Date tollEntryDate;
}
