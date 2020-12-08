package com.demo.Visitor.access.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.Visitor.access.model.VisitorRequest;

@Repository
public interface VisitorRequestRepository extends MongoRepository<VisitorRequest, String> {

	public List<VisitorRequest> findAllByEmpId(int empId);

	public VisitorRequest insert(VisitorRequest visitorRequest);

	public List<VisitorRequest> findAllByStatus(String status);

	public VisitorRequest findOneByEmpIdAndStatus(int empId, String status);

	public VisitorRequest save(VisitorRequest visitorRequest);

	public void deleteByEmpIdAndStatus(int empId, String status);

}
