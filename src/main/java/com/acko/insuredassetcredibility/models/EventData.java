/**
 * @author saurav roy
 * Date:22/11/22
 * Time:4:48 PM
 */
package com.acko.insuredassetcredibility.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EventData implements Serializable {
    private Integer count;
    private String eventName;
    private List<? extends BaseEventData> data;
}
