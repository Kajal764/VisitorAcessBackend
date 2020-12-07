package com.demo.Visitor.access.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.Visitor.access.model.ODCList;

@Repository
public interface ODCRepository extends MongoRepository<ODCList, String>{
	public List<ODCList> findAll();

}