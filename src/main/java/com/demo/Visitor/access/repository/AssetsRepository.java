package com.demo.Visitor.access.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.Visitor.access.model.Assets;



@Repository
public interface AssetsRepository  extends MongoRepository<Assets, Integer> {

	
	 Optional<Assets> findByAssetSerialNumber(String serialNumber);
}
