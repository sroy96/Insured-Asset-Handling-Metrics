/**
 * @author saurav roy
 * Date:24/11/22
 * Time:5:23 PM
 */
package com.acko.insuredassetcredibility.dto.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MapDirection {
    List<GeoCodedWayPoints> geocodedWaypoints;
    List<Routes>routes;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class GeoCodedWayPoints {
        String geocoderStatus;
        String placeId;
        List<String>types;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Routes {
        Map<String, Cords> bounds;
        List<Legs>legs;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Cords {
        String lat;
        String lng;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Legs {
        Distance distance;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Distance {
        String text;
        Integer value;

    }
}
