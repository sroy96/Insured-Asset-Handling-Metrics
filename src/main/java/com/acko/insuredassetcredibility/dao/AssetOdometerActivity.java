/**
 * @author saurav roy
 * Date:21/11/22
 * Time:3:11 PM
 */
package com.acko.insuredassetcredibility.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class AssetOdometerActivity {
    @Id
    private String assetId;
    private Double lastOdometerReading;
    private String source;
    private LocalDateTime recordData;

}
