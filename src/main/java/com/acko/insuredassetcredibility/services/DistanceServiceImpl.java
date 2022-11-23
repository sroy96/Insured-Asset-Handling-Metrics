/**
 * @author saurav roy
 * Date:23/11/22
 * Time:9:22 PM
 */
package com.acko.insuredassetcredibility.services;

import com.acko.insuredassetcredibility.dao.ScoreDao;
import com.acko.insuredassetcredibility.dao.acitivity.OutStationActivity;
import com.acko.insuredassetcredibility.interfaces.ApplicationService;
import com.acko.insuredassetcredibility.models.KeyActivities;
import com.acko.insuredassetcredibility.models.KeyFactorDataScore;
import com.acko.insuredassetcredibility.repository.OutStationCommuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class DistanceServiceImpl implements ApplicationService {

    @Autowired
    OutStationCommuteRepository outStationCommuteRepository;
    @Override
    public List<KeyActivities> getActivities(String assetId, ScoreDao scoreDao) {
        KeyActivities getOutStationCommute= this.getOutStationActivity(assetId, LocalDateTime.now(), scoreDao.getRefreshDate());
        return null;
    }

    private KeyActivities getOutStationActivity(String assetId, LocalDateTime from, LocalDateTime to){
        List<OutStationActivity> activities = outStationCommuteRepository.findAllByAssetId(from,to,assetId);

    }

    @Override
    public List<KeyFactorDataScore> getKeyFactorData(String assetId, ScoreDao scoreDao) {
        return null;
    }
}
