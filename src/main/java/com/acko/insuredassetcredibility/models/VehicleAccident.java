package com.acko.insuredassetcredibility.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VehicleAccident {

    @Id
    private String assetId;
    private LocalDateTime majorAccidentDate;
    private LocalDateTime minorAccidentDate;
    private LocalDateTime lastRefreshedTime;
}
