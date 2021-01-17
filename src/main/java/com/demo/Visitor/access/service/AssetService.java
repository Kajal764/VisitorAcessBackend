package com.demo.Visitor.access.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.model.AssetList;
import com.demo.Visitor.access.model.AssetRequests;
import com.demo.Visitor.access.repository.AssetRepository;
import com.demo.Visitor.access.repository.AssetRequestRepository;

@Service
public class AssetService {

	@Autowired
	AssetRepository assetRepository;
	
	@Autowired
	AssetRequestRepository assetRequestRepository;

	public boolean addAsset(AssetList asset) throws BusinessException {
		Optional<AssetList> serialNumber = assetRepository.findBySerialNumber(asset.getSerialNumber());
		if (serialNumber.isPresent())
			throw new BusinessException("An Asset already exists with this serial number");
		asset.setFlag(true);
		AssetList assetAdded = assetRepository.save(asset);
		if (assetAdded == null)
			throw new BusinessException("Something went wrong!!!Please try again");
		else
			return true;
	}

	public List<AssetList> getAssetRequests() throws BusinessException {
		List<AssetList> assetList = null;
//		if (role == "odcManager") {
//			assetList = assetRepository.findByStatus("Pending Approval");
//			assetList.removeIf(odc -> odc.getOdcName() != odcName);
//		} else
//		{
//			assetList = assetRepository.findByStatus("Pending Approval");}
		assetList = assetRepository.findByStatus("Pending Approval");
		if (assetList == null)
			throw new BusinessException("No Asset request raised");
		else
			return assetList;
	}

	public boolean approveAndRejectAssetRequest(List<AssetList> assetList) throws BusinessException {
		boolean success = false;
		for (AssetList asset : assetList) {
			assetRepository.deleteBySerialNumber(asset.getSerialNumber());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			asset.setDateOfApproval(now.format(dtf));
			System.out.println(now.format(dtf));
			AssetList assetSave = assetRepository.save(asset);
			if (assetSave == null)
				throw new BusinessException("Request Cannot be processed");
			else
				success = true;
		}
		return success;
	}
	
	public boolean addAssetRequest(AssetRequests asset) throws BusinessException {
		asset.setFlag(true);
		AssetRequests assetAdded = assetRequestRepository.save(asset);
		if (assetAdded == null)
			throw new BusinessException("Something went wrong!!!Please try again");
		else
			return true;
	}

	public List<AssetRequests> getAssetRequestsForMovement(String role, String odcName) throws BusinessException {
		List<AssetRequests> assetRequests = null;
		if (role == "odcManager") {
			assetRequests = assetRequestRepository.findByStatus("Pending Approval");
			assetRequests.removeIf(odc -> odc.getName() != odcName);
		} else
			assetRequests = assetRequestRepository.findByStatus("Pending Approval");
		if (assetRequests == null)
			throw new BusinessException("No Asset request raised");
		else
			return assetRequests;
	}

	public boolean approveAndRejectRequestsForMovement(List<AssetRequests> assetRequest) throws BusinessException {
		boolean success = false;
		for (AssetRequests asset : assetRequest) {
			assetRequestRepository.setStatus("Approved");
				success = true;
		}
		return success;
	}

	public List<AssetList> getAssetRequestsToAdd(String role, String odcName) throws BusinessException {
			List<AssetList> assetList = null;
			if (role == "odcManager") {
				assetList = assetRepository.findByStatus("Pending Approval");
				assetList.removeIf(odc -> odc.getName() != odcName);
			} else
				assetList = assetRepository.findByStatus("Pending Approval");
			if (assetList == null)
				throw new BusinessException("No Asset request raised");
			else
				return assetList;
		}

	public List<AssetRequests> getAllMovements(String serialNumber) throws BusinessException {
		List<AssetRequests> assetRequests = assetRequestRepository.findBySerialNumber(serialNumber);
		if(assetRequests == null)
			throw new BusinessException("No information available regarding the asset");
		else
		   return assetRequests;
	}

}
