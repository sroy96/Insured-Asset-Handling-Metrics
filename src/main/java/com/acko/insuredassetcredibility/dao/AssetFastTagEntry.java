/**
 * @author saurav roy
 * Date:21/11/22
 * Time:3:02 PM
 */
package com.acko.insuredassetcredibility.dao;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document
public class AssetFastTagEntry {
    @Id
    private String assetId;
    @NonNull
    private String tollId;
    @NonNull
    private LocalDateTime recordDate;
}
