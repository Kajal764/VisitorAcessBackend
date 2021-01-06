package com.demo.Visitor.access.repository;

import com.demo.Visitor.access.model.UserInfo;
import com.demo.Visitor.access.model.VisitorRequest;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserInfo, Integer> {

	Optional<UserInfo> findByEmpId(int empId);

	@DeleteQuery
	void deleteByEmpId(int empId);

	public List<UserInfo> findByRole(String role);

    List<UserInfo> findAllByManagerName(String firstName);

	Optional<UserInfo> findByFirstName(String name);

	List<UserInfo> findAllByOdc(String odc);

	void insert(Optional<UserInfo> user);
	


}
