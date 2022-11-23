/**
 * @author saurav roy
 * Date:21/11/22
 * Time:2:48 PM
 */
package com.acko.insuredassetcredibility.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class NationalTollRegistry {
    @Id
    private String tollId;
    private Double latitude;
    private Double longitude;
    private List<String>exitTollIds;
}
