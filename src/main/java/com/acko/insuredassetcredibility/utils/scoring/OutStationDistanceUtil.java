/**
 * @author saurav roy
 * Date:24/11/22
 * Time:3:25 PM
 */
package com.acko.insuredassetcredibility.utils.scoring;

import com.acko.insuredassetcredibility.dto.responses.MapDirection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class OutStationDistanceUtil {
    private static final String apiKey= "AIzaSyC7-kLktgvYCGphxl5ss7V-5Tmo3mDvCtY";

    public static Double getDistanceBetweenToll(Double fromLat,Double fromLong,Double toLat, Double toLong){
        StringBuilder stringBuilder = new StringBuilder();
        double distance = 0.0;
        stringBuilder.append("https://maps.googleapis.com/maps/api/directions/json?origin=").append(String.valueOf(fromLat)).append(",").append(String.valueOf(fromLong))
                .append("&destination=").append(String.valueOf(toLat)).append(",").append(String.valueOf(toLong)).append("&key=").append(apiKey);
        String url = stringBuilder.toString();
        //        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&key=AIzaSyCJFnNBrKG0580eBSe-n21SdLjIy8llNxs";
        ResponseEntity<MapDirection>response = new RestTemplate().getForEntity(url,MapDirection.class);
        if(response.getStatusCodeValue()==200){
            MapDirection mapDirection=  response.getBody();
            assert mapDirection != null;
            distance = mapDirection.getRoutes().get(0).getLegs().get(0).getDistance().getValue().doubleValue() /1000;

        }
        else{
            log.info("error in getting data from google maps api");
        }
        return distance;
    }
}


