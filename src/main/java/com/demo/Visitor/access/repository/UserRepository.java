package com.demo.Visitor.access.repository;

import com.demo.Visitor.access.model.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Pattern;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<UserInfo, Integer> {

    Optional<UserInfo> findByEmpId(@Pattern(regexp = "^[0-9]\\d{6}$", message = "Employee Id should be 7 digit") int email);
}
