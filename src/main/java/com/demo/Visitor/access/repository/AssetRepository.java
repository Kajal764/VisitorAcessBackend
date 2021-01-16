package com.demo.Visitor.access.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.Visitor.access.model.AssetList;

@Repository
public interface AssetRepository extends MongoRepository<AssetList, String>{
		
	Optional<AssetList> findBySerialNumber(String name);
	List<AssetList> findByOdcNameAndStatus(String odcName,String status);
	List<AssetList> findByStatus(String status);
	public void deleteBySerialNumber(String serialNumber);
	
}
