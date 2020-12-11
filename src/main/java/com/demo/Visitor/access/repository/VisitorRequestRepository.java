package com.demo.Visitor.access.repository;

import com.demo.Visitor.access.model.VisitorRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRequestRepository extends MongoRepository<VisitorRequest, String> {

	public List<VisitorRequest> findAllByEmpId(int empId);

	public VisitorRequest insert(VisitorRequest visitorRequest);

	public VisitorRequest save(VisitorRequest visitorRequest);

	public void deleteByEmpIdAndStatus(int empId, String status);

	public List<VisitorRequest> findByManagerEmpID(int empId);

}
