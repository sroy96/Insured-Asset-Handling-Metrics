/**
 * @author saurav roy
 * Date:19/11/22
 * Time:6:40 PM
 */
package com.acko.insuredassetcredibility.controller;

import com.acko.insuredassetcredibility.dao.ChallanDao;
import com.acko.insuredassetcredibility.dto.requests.AssetScoringRequest;
import com.acko.insuredassetcredibility.dto.responses.AssetScoringResponse;
import com.acko.insuredassetcredibility.models.KeyActivities;
import com.acko.insuredassetcredibility.models.KeyFactorsData;
import com.acko.insuredassetcredibility.services.ApplicationServiceImpl;
import com.acko.insuredassetcredibility.services.ChallanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    ApplicationServiceImpl applicationService;

    @Autowired
    ChallanServiceImpl challanService;

    @GetMapping("/asset/details")
    public ResponseEntity<AssetScoringResponse> getAssetScores(@RequestBody AssetScoringRequest assetScoringRequest) {
        AssetScoringResponse assetScoringResponse =applicationService.getAssetScoringDetails(assetScoringRequest);
        return new ResponseEntity<>(assetScoringResponse,HttpStatus.OK);
    }

    @PostMapping("/challan/details")
    public void saveChallanData(@RequestBody ChallanDao challanDao) {
        challanService.save(challanDao);
    }

}
