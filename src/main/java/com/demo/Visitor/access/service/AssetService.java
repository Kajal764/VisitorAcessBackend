package com.demo.Visitor.access.service;

import com.demo.Visitor.access.dto.AssetDto;
import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.model.AssetData;
import com.demo.Visitor.access.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    public List<String> addAsset(AssetDto assetDto) throws BusinessException {
        List<String> addedAssets = new ArrayList<>();
        assetDto.assetInfos.forEach(value -> {
            Optional<AssetData> asset = assetRepository.findBySerialNumberAndOdcName(value.serialNumber, assetDto.odcName);
            if (asset.isEmpty()) {
                AssetData assetData = new AssetData(assetDto);
                assetData.setSerialNumber(value.serialNumber);
                assetData.setName(value.name);
                Random random = new Random();
                int random_id;
                Optional<AssetData> requests;
                do {
                    random_id = random.nextInt();
                    requests = assetRepository.findByRequestId(random_id);
                } while (requests.isPresent());
                assetData.setRequestId(random_id);
                assetData.setStatus("Pending Approval");
                if (assetDto.reason.equals("newAsset")) {
                    assetData.setAssetCondition("Working");
                } else {
                    assetData.setAssetCondition(assetDto.reason);
                }
                Optional<AssetData> exist = assetRepository.findBySerialNumber(value.serialNumber);
                if(exist.isPresent()){
                    assetRepository.deleteBySerialNumber(exist.get().getSerialNumber());
                    exist.get().setCurrentOdc(false);
                    assetRepository.save(exist.get());
                }
                assetData.setCurrentOdc(true);
                AssetData assetAdded = assetRepository.save(assetData);
                addedAssets.add(value.serialNumber);
                if (assetAdded == null) {
                    try {
                        throw new BusinessException("Something went wrong!!!Please try again");
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        if (addedAssets.size() == 0)
            throw new BusinessException("Asset not added!!!Please try again");
        return addedAssets;
    }
}



