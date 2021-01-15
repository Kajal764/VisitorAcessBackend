package com.demo.Visitor.access.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.exception.LoginException;
import com.demo.Visitor.access.model.Assets;

import com.demo.Visitor.access.repository.AssetsRepository;


@Service
public class AssetsService {

	@Autowired
	AssetsRepository assetsRepository;
	
	
	 public boolean addAssets(Assets assets) throws BusinessException {
	        Optional<Assets> assetSerialNumber = assetsRepository.findByAssetSerialNumber(assets.getSerialNumber());
	        if (assetSerialNumber.isPresent())
	            throw new LoginException("Asset Already Exist", 400);
	        
	       
	        Assets assetAdded = assetsRepository.save(assets);
	       
	        if (assetAdded == null)
	            throw new BusinessException("Something went wrong!!!Please try again");
	        else
	            return true;
	    }
}
