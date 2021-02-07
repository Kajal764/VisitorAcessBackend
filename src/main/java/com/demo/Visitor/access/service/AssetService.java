package com.demo.Visitor.access.service;

import com.demo.Visitor.access.dto.AssetDto;
import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.model.AssetData;
import com.demo.Visitor.access.model.UserInfo;
import com.demo.Visitor.access.repository.AssetRepository;
import com.demo.Visitor.access.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    UserRepository userRepository;

    public List<String> addAsset(AssetDto assetDto) throws BusinessException {

        int requestId = 256000;

        int size = assetDto.assetInfos.size();
        if (assetDto.movement.equals("Outward") || assetDto.movement.equals("Transferred")) {
            do {
                List<AssetData> list = assetRepository.findAllBySerialNumber(assetDto.assetInfos.get(size - 1).serialNumber);
                if (list.isEmpty())
                    assetDto.assetInfos.remove(size - 1);
                size--;
            } while (size != 0);
        }
        if (assetDto.assetInfos.size() == 0) {
            throw new BusinessException("Asset not present in odc !!!");
        }
        List<String> addedAssets = new ArrayList<>();
        assetDto.assetInfos.forEach(value -> {
            Optional<AssetData> asset = assetRepository.findBySerialNumberAndOdcName(value.serialNumber, assetDto.odcName);
            if (asset.isEmpty()) {
                AssetData assetData = new AssetData(assetDto);
                assetData.setSerialNumber(value.serialNumber);
                assetData.setDescription(value.description);
                assetData.setType(value.type);
                assetData.setStatus(value.status);
                List<AssetData> allAssets = assetRepository.findAll();
                assetData.setRequestId(requestId + allAssets.size() + 1);
                assetData.setRequestStatus("Pending Approval");
                Optional<AssetData> exist = assetRepository.findBySerialNumberAndIsCurrentOdc(value.serialNumber, true);
                if (exist.isPresent()) {
                    assetRepository.deleteByRequestId(exist.get().getRequestId());
                    exist.get().setCurrentOdc(false);
                    if(exist.get().getRequestStatus().equals("Approved"))
                    exist.get().setTillDate(LocalDateTime.now());
                    
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

    public List<AssetData> getAssetList(String empId) throws BusinessException {
        List<AssetData> assetDataList = new ArrayList<>();
        if (empId.equals("Admin")) {
            assetDataList.addAll(assetRepository.findAllByIsCurrentOdc(true));
        } else {
            Optional<UserInfo> userInfo = userRepository.findByEmpId(empId);
            if (userInfo.isPresent()) {
                List<String> odc = userInfo.get().getOdc();
                odc.forEach(odcName -> assetDataList.addAll(assetRepository.findAllByOdcName(odcName)));
                assetDataList.removeIf(value -> value.isCurrentOdc() == false);
                assetDataList.removeIf(value -> value.getRequestStatus().equals("Rejected") || value.getRequestStatus().equals("Pending Approval"));
            } else {
                throw new BusinessException("User Not Present !!!");
            }
        }
        if (assetDataList.size() == 0) {
            throw new BusinessException("Assets Not Added !!!");
        }
        return assetDataList;
    }

    public List<AssetData> getAssetRequests(String empId) throws BusinessException {
        Optional<UserInfo> userInfo = userRepository.findByEmpId(empId);
        List<AssetData> assetDataList = new ArrayList<>();
        if (userInfo.isPresent()) {
            List<String> odc = userInfo.get().getOdc();
            odc.forEach(odcName -> assetDataList.addAll(assetRepository.findAllByOdcName(odcName)));
            assetDataList.removeIf(data -> data.isCurrentOdc() == false);
            if (assetDataList.size() == 0)
                throw new BusinessException("No Pending Request !!!");
            return assetDataList;
        }
        throw new BusinessException("User Not Present !!!");
    }

    public boolean approveAndRejectAssetRequest(List<AssetData> assetList) throws BusinessException {
        boolean success = false;
        for (AssetData asset : assetList) {
            assetRepository.deleteByRequestId(asset.getRequestId());
            if (asset.getRequestStatus().equals("Approved"))
                asset.setFromDate(LocalDateTime.now());
            AssetData assetSave = assetRepository.save(asset);
            if (assetSave == null)
                throw new BusinessException("Request Cannot be processed");
            else
                success = true;
        }
        return success;
    }

    public List<AssetData> getAllMovements(String serialNumber) throws BusinessException {
        List<AssetData> assetRequests = assetRepository.findAllBySerialNumber(serialNumber);
        if (assetRequests == null)
            throw new BusinessException("No information available regarding the asset");
        else
            return assetRequests;
    }

    public List<AssetData> getAssetListToView(String empId) throws BusinessException {
        List<AssetData> assetDataList = assetRepository.findByEmpId(empId);
        if (assetDataList.size() == 0)
            throw new BusinessException("Request Not Raised For Assets !!!");
        return assetDataList;
    }
}



