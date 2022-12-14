/**
 * @author saurav roy
 * Date:19/11/22
 * Time:6:40 PM
 */
package com.acko.insuredassetcredibility.controller;

import com.acko.insuredassetcredibility.dao.ChallanDao;
import com.acko.insuredassetcredibility.dao.NationalTollRegistry;
import com.acko.insuredassetcredibility.dao.acitivity.OutStationActivity;
import com.acko.insuredassetcredibility.dao.RegisteredAssetDao;
import com.acko.insuredassetcredibility.dto.requests.AckathonDTO;
import com.acko.insuredassetcredibility.dto.requests.AssetScoringRequest;
import com.acko.insuredassetcredibility.dto.responses.AssetScoringResponse;
import com.acko.insuredassetcredibility.models.KeyActivities;
import com.acko.insuredassetcredibility.models.KeyFactorsData;
import com.acko.insuredassetcredibility.services.ApplicationServiceImpl;
import com.acko.insuredassetcredibility.services.ChallanServiceImpl;
import com.acko.insuredassetcredibility.services.DistanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    ApplicationServiceImpl applicationService;

    @Autowired
    ChallanServiceImpl challanService;

    @Autowired
    DistanceServiceImpl distanceService;

    @GetMapping("/asset/details/{phone}")
    public ResponseEntity<AssetScoringResponse> getAssetScores(@PathVariable("phone") String phone) {
        AssetScoringResponse assetScoringResponse =applicationService.getAssetScoringDetails(phone);
        return new ResponseEntity<>(assetScoringResponse,HttpStatus.OK);
    }

    @PostMapping("/challan/details")
    public void saveChallanData(@RequestBody ChallanDao challanDao) {
        challanService.save(challanDao);
    }

    @PostMapping("/activity/fastag")
    public ResponseEntity<HttpStatus> addFastTagActivity(@RequestBody OutStationActivity outStationActivity){
        distanceService.addActivity(outStationActivity);
        return  new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/resource/toll")
    public ResponseEntity<HttpStatus>addNationalTollData(@RequestBody NationalTollRegistry nationalTollRegistry) {
        distanceService.addNationalToll(nationalTollRegistry);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/asset/onboarding")
    public void saveAssest(@RequestBody RegisteredAssetDao registeredAssetDao) {
        applicationService.saveAsset(registeredAssetDao);
    }

    @PostMapping("/hack")
    public ResponseEntity<HttpStatus> createActivities(@RequestBody AckathonDTO ackathonDTO){
        applicationService.saveActivities(ackathonDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
