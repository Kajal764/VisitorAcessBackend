package com.demo.Visitor.access.repository;

import com.demo.Visitor.access.model.UserInfo;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserInfo, Integer> {

	Optional<UserInfo> findByEmpId(String empId);

	List<UserInfo>  findAllByRole(String role);

	@DeleteQuery
	void deleteByEmpId(String empId);

    List<UserInfo> findAllByManagerName(String firstName);

	Optional<UserInfo> findByFirstName(String name);

	List<UserInfo> findAllByOdc(String odc);

	void insert(Optional<UserInfo> user);
	


}
