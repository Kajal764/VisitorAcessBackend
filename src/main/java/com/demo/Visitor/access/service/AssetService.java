package com.demo.Visitor.access.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.exception.LoginException;
import com.demo.Visitor.access.model.AssetList;
import com.demo.Visitor.access.model.ODCList;
import com.demo.Visitor.access.model.VisitorRequest;
import com.demo.Visitor.access.repository.AssetRepository;

@Service
public class AssetService {
	
	@Autowired
	AssetRepository assetRepository;
	
	public boolean addAsset(AssetList asset) throws BusinessException{
		Optional<AssetList> serialNumber = assetRepository.findBySerialNumber(asset.getSerialNumber());
		if(serialNumber.isPresent())
			throw new BusinessException("An Asset already exists with this serial number");
		asset.setFlag(true);
		AssetList assetAdded = assetRepository.save(asset);
		if(assetAdded == null)
			throw new BusinessException("Something went wrong!!!Please try again");
		else
			return true;
	}
}
