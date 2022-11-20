/**
 * @author saurav roy
 * Date:20/11/22
 * Time:5:36 PM
 */
package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.models.VehicleServicingData;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class VehicleServicingDao extends VehicleServicingData {
    @Id
    private String assetId;
}
