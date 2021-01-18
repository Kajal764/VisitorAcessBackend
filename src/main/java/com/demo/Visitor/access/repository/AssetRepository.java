package com.demo.Visitor.access.repository;

import com.demo.Visitor.access.dto.AssetInfo;
import com.demo.Visitor.access.model.AssetData;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends MongoRepository<AssetData, String> {

    List<AssetData> findAllBySerialNumber(String name);

    Optional<AssetData> findBySerialNumberAndIsCurrentOdc(String no,boolean data);

    Optional<AssetData> findBySerialNumberAndOdcName(String no, String odc);

    Optional<AssetData> findByRequestId(int id);

    @DeleteQuery
    void deleteByRequestId(int requestId);

    List<AssetData> findAllByOdcName(String odcName);

    List<AssetData> findByEmpId(String empId);

    List<AssetData> findAllByIsCurrentOdc(boolean data);
}
