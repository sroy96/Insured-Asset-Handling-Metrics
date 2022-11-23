/**
 * @author saurav roy
 * Date:23/11/22
 * Time:7:53 PM
 */
package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.enums.KeyFactors;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Data
@Document
public class ScoreDao {
    @Id
    String assetId;
    Double score;
    Map<KeyFactors, Double> keyFactorScores;
    Date refreshDate;

}
