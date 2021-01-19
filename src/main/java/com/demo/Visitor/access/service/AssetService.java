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

import javax.security.auth.login.LoginException;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    UserRepository userRepository;


//    public List<String> addAsset(AssetDto assetDto) throws BusinessException {
//        List<String> addedAssets = new ArrayList<>();
//        assetDto.assetInfos.forEach(value -> {
//            Optional<AssetData> asset = assetRepository.findBySerialNumberAndOdcName(value.serialNumber, assetDto.odcName);
//            if (asset.isEmpty()) {
//                AssetData assetData = new AssetData(assetDto);
//                assetData.setSerialNumber(value.serialNumber);
//                assetData.setName(value.name);
//                Random random = new Random();
//                int random_id;
//                Optional<AssetData> requests;
//                do {
//                    random_id = random.nextInt();
//                    requests = assetRepository.findByRequestId(random_id);
//                } while (requests.isPresent());
//                assetData.setRequestId(random_id);
//                assetData.setStatus("Pending Approval");
//                if (assetDto.reason.equals("newAsset")) {
//                    assetData.setAssetCondition("Working");
//                } else {
//                    assetData.setAssetCondition(assetDto.reason);
//                }
//                
//                Optional<AssetData> exist = assetRepository.findBySerialNumberAndIsCurrentOdc(value.serialNumber,true);
//                if (exist.isPresent()) {
//                    assetRepository.deleteByRequestId(exist.get().getRequestId());
//                    exist.get().setCurrentOdc(false);
//                    exist.get().setTillDate(LocalDateTime.now());
//                    assetRepository.save(exist.get());
//                }
//                assetData.setCurrentOdc(true);
//                AssetData assetAdded = assetRepository.save(assetData);
//                addedAssets.add(value.serialNumber);
//                if (assetAdded == null) {
//                    try {
//                        throw new BusinessException("Something went wrong!!!Please try again");
//                    } catch (BusinessException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        if (addedAssets.size() == 0)
//            throw new BusinessException("Asset not added!!!Please try again");
//        return addedAssets;
//    }

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
                Optional<AssetData> exist = assetRepository.findBySerialNumberAndIsCurrentOdc(value.serialNumber, true);
                if (exist.isPresent()) {
                    assetRepository.deleteByRequestId(exist.get().getRequestId());
                    exist.get().setCurrentOdc(false);
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


    public boolean addAssets(AssetData assetData) throws BusinessException{
    	Optional<AssetData> assetSerialNumber=assetRepository.findBySerialNumber(assetData.getSerialNumber());
    	if(assetSerialNumber.isPresent())
    	throw new BusinessException("Asset Already Exist");
    	AssetData assetAdded=assetRepository.save(assetData);
    	if(assetAdded==null)
    		throw new BusinessException("Something wrong");
    	else
    		return true;
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
            }
            throw new BusinessException("User Not Present !!!");
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
            assetDataList.removeIf(data -> !data.getStatus().equals("Pending Approval"));
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
            if (asset.getStatus().equals("Approved"))
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



