/**
 * @author saurav roy
 * Date:25/11/22
 * Time:8:54 AM
 */
package com.acko.insuredassetcredibility.dto.requests;

import com.acko.insuredassetcredibility.dao.ChallanDao;
import com.acko.insuredassetcredibility.dao.acitivity.OutStationActivity;
import lombok.Data;

import java.util.List;

@Data
public class AckathonDTO {
    List<OutStationActivity>outStation;
    List<ChallanDao>challanDaos;
}
