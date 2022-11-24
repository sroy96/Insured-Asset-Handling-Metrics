/**
 * @author saurav roy
 * Date:21/11/22
 * Time:12:35 AM
 */
package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.enums.FuelType;
import com.acko.insuredassetcredibility.enums.TransmissionType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document("assetdata")
public class RegisteredAssetDao {
    @Id
    private String assetId;
    private String ownerMobile;
    private String ownerEmail;
    private LocalDateTime registrationDate;
    private Integer ownershipCount;
    private Boolean isCommercial;
    private String make;
    private String model;
    private String variant;
    private TransmissionType transmissionType;
    private FuelType fuelType;
}
