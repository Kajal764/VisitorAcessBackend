package com.demo.Visitor.access.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.Visitor.access.model.AssetRequests;

@Repository
public interface AssetRequestRepository extends MongoRepository<AssetRequests, String> {

	List<AssetRequests> findByStatus(String string);

	@DeleteQuery
	void deleteByRequestId(String string);

	List<AssetRequests> findBySerialNumber(String serialNumber);
   
	
}
