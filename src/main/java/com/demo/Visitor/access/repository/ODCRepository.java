package com.demo.Visitor.access.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.Visitor.access.model.ODCList;

@Repository
public interface ODCRepository extends MongoRepository<ODCList, String> {
    List<ODCList> findAll();

    Optional<ODCList> findByOdcName(String name);

    Optional<ODCList> findByOdcId(int odcId);

    @DeleteQuery
    void deleteByOdcName(String name);

    void insert(Optional<ODCList> odc);

}
