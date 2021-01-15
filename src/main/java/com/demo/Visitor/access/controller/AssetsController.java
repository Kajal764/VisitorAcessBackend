package com.demo.Visitor.access.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.model.Assets;
import com.demo.Visitor.access.service.AssetsService;

//@RestController
//@RequestMapping("assets")
//@CrossOrigin("*")
public class AssetsController {
//
//	@Autowired
//	AssetsService assestsService;
//	
//    @PostMapping(value = "/addAssets", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> addODC(@RequestBody Assets assets) throws BusinessException {
//        ResponseEntity<?> responseEntity = null;
//        try {
//            boolean success = assestsService.addAssets(assets);
//            responseEntity = new ResponseEntity<>(success, HttpStatus.CREATED);
//        } catch (BusinessException e) {
//            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
//        }
//        return responseEntity;
//    }

}
