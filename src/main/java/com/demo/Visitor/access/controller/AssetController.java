package com.demo.Visitor.access.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

}
