package com.acko.insuredassetcredibility.dao;

import com.acko.insuredassetcredibility.models.ViolationDetail;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Document("challans")
public class ChallanDao {

  @Id
  private String challanNumber;
  private String assetId;
  private List<ViolationDetail> violationDetails;
  private String dlNumber;
  private String status;
  private LocalDateTime challanDate;
  private String violatorContactNumber;
  private LocalDateTime updatedDate;
}
