package com.demo.Visitor.access.controller;

import com.demo.Visitor.access.dto.AssetDto;
import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

}
