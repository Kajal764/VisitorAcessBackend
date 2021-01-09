package com.demo.Visitor.access.repository;

import com.demo.Visitor.access.model.VisitorRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRequestRepository extends MongoRepository<VisitorRequest, String> {

    public List<VisitorRequest> findAllByEmpId(String empId);

    public VisitorRequest insert(VisitorRequest visitorRequest);

    public VisitorRequest save(VisitorRequest visitorRequest);

    public void deleteByVisitorRequestId(int visitorRequest);

    public List<VisitorRequest> findByManagerEmpID(String empId);

    Optional<VisitorRequest> findByVisitorRequestId(int id);

    public List<VisitorRequest> findAllByOdcAndStatus(String odcName, String status);

}
