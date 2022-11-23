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

import java.util.Date;
import java.util.List;

@Data
@Document
public class OutStationActivity {
    @Id
    private String assetId;
    private String eventName;
    private FastTagData data;

    @Data
    @Document
    private static class FastTagData {
        String tollId;
        Date eventDate;
    }
}
