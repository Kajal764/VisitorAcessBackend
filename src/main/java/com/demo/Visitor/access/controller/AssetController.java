package com.demo.Visitor.access.controller;

import com.demo.Visitor.access.dto.AssetDto;
import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.model.AssetData;
import com.demo.Visitor.access.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("visitor/asset")
@CrossOrigin("*")
public class AssetController {

    @Autowired
    AssetService assetService;

    @PostMapping(value = "/addAsset")
    public ResponseEntity<?> addAsset(@RequestBody AssetDto assetDto) {
        ResponseEntity<?> responseEntity = null;
        try {
            List<String> success = assetService.addAsset(assetDto);
            responseEntity = new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @GetMapping(value = "/assetList/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAssetList(@PathVariable String empId) {
        ResponseEntity<?> responseEntity = null;
        try {
            List<AssetData> assetLists = assetService.getAssetList(empId);
            responseEntity = new ResponseEntity<>(assetLists, HttpStatus.CREATED);
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @GetMapping(value = "/pendingAssetRequest/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAssetRequest(@PathVariable String empId) {
        ResponseEntity<?> responseEntity = null;
        try {
            List<AssetData> assetLists = assetService.getAssetRequests(empId);
            responseEntity = new ResponseEntity<>(assetLists, HttpStatus.ACCEPTED);
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping(value = "/approveOrRejectRequest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> approveOrRejectRequest(@RequestBody List<AssetData> assetList) {
        ResponseEntity<?> responseEntity = null;
        try {
            boolean success = assetService.approveAndRejectAssetRequest(assetList);
            if (success)
                responseEntity = new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @GetMapping(value = "/assetTraker/{serialNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMovements(@PathVariable String serialNumber) {
        ResponseEntity<?> responseEntity = null;
        try {
            List<AssetData> assetRequest = assetService.getAllMovements(serialNumber);
            if (assetRequest != null)
                responseEntity = new ResponseEntity<>(assetRequest, HttpStatus.ACCEPTED);
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @GetMapping(value = "/view-assetList/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAssetsRequestList(@PathVariable String empId) {
        ResponseEntity<?> responseEntity = null;
        try {
            List<AssetData> assetListToView = assetService.getAssetListToView(empId);
            responseEntity = new ResponseEntity<>(assetListToView, HttpStatus.OK);
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
