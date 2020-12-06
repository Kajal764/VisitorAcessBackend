package com.demo.Visitor.access.repository;

import com.demo.Visitor.access.model.UserInfo;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserInfo, Integer> {

    Optional<UserInfo> findByEmpId(int empId);

    UserInfo findByEmpIdAndPassword(int empId, String password);

    @DeleteQuery
    void deleteByEmpId(int empId);

}
