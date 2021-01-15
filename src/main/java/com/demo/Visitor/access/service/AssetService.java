package com.demo.Visitor.access.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.model.AssetList;
import com.demo.Visitor.access.repository.AssetRepository;

@Service
public class AssetService {

	@Autowired
	AssetRepository assetRepository;

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

	public List<AssetList> getAssetRequests(String role, String odcName) throws BusinessException {
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

	public boolean approveAndRejectAssetRequest(List<AssetList> assetList) throws BusinessException {
		boolean success = false;
		for (AssetList asset : assetList) {
			assetRepository.deleteBySerialNumber(asset.getSerialNumber());
			AssetList assetSave = assetRepository.save(asset);
			if (assetSave == null)
				throw new BusinessException("Request Cannot be processed");
			else
				success = true;
		}
		return success;
	}

}
