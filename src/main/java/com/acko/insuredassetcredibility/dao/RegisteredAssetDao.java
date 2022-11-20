/**
 * @author saurav roy
 * Date:21/11/22
 * Time:12:35 AM
 */
package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.enums.FuelType;
import com.acko.insuredassetcredibility.enums.TransmissionType;
import com.acko.insuredassetcredibility.models.Insurance;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class RegisteredAssetDao {
    @Id
    private String assetId;
    private Date ServicingDate;
    private Date registrationDate;
    private Insurance insuranceInfo;
    private Integer ownershipCount;
    private Boolean isCommercial;
    private String make;
    private String model;
    private String variant;
    private String variantMarketReleaseDate;
    private Double brandValueIndia;
    private TransmissionType transmissionType;
    private FuelType fuelType;
}
