package com.demo.Visitor.access.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.model.AssetList;
import com.demo.Visitor.access.model.VisitorRequest;
import com.demo.Visitor.access.service.AssetService;

@RestController
@RequestMapping("visitor/asset")
@CrossOrigin("*")
public class AssetController {
	
	@Autowired
	AssetService assetService;
	
	 @PostMapping(value = "/addAsset")
	    public ResponseEntity<?> addAsset(@RequestBody AssetList asset) {
	        ResponseEntity<?> responseEntity = null;
	        try {
	            boolean success = assetService.addAsset(asset);
	            responseEntity = new ResponseEntity<>(success, HttpStatus.CREATED);
	        } catch (BusinessException e) {
	            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
	        }
	        return responseEntity;
	    }
	 
	 @PostMapping(value = "/approveOrRejectRequest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> approveOrRejectRequest(@RequestBody List<AssetList> assetList) {
	        ResponseEntity<?> responseEntity = null;
	        try {
	            boolean success = assetService.approveAndRejectAssetRequest(assetList);
	            if (success)
	                responseEntity = new ResponseEntity<>(success, HttpStatus.ACCEPTED);
	        } catch (BusinessException e) {
	            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
	        }
	        return responseEntity;
	    }

	 @GetMapping(value = "/pendingAssetRequest/{odcName}/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> getAssetRequest(@PathVariable String odcName,@PathVariable String role)  {
	        ResponseEntity<?> responseEntity = null;
	        try {
	        List<AssetList> assetLists = assetService.getAssetRequests(odcName, role);
	        if (assetLists != null)
	            responseEntity = new ResponseEntity<>(assetLists, HttpStatus.ACCEPTED);
	        } catch (BusinessException e) {
	            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
	        }
	        return responseEntity;
	    }
}
