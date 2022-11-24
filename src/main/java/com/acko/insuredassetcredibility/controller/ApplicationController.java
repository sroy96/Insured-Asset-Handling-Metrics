/**
 * @author saurav roy
 * Date:19/11/22
 * Time:6:40 PM
 */
package com.acko.insuredassetcredibility.controller;

import com.acko.insuredassetcredibility.dto.requests.AssetScoringRequest;
import com.acko.insuredassetcredibility.dto.responses.AssetScoringResponse;
import com.acko.insuredassetcredibility.services.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    ApplicationServiceImpl applicationService;
    @GetMapping("/asset/details")
    public ResponseEntity<AssetScoringResponse> getAssetScores(@RequestBody AssetScoringRequest assetScoringRequest) {
        AssetScoringResponse assetScoringResponse =applicationService.getAssetScoringDetails(assetScoringRequest);
        return new ResponseEntity<>(assetScoringResponse,HttpStatus.OK);
    }
}
