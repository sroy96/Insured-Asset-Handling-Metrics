/**
 * @author saurav roy
 * Date:20/11/22
 * Time:4:40 PM
 */
package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.enums.FuelType;
import com.acko.insuredassetcredibility.enums.TransmissionType;
import com.acko.insuredassetcredibility.models.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDao {
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
