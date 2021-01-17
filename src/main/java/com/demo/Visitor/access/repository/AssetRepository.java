package com.demo.Visitor.access.repository;

import com.demo.Visitor.access.dto.AssetInfo;
import com.demo.Visitor.access.model.AssetData;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRepository extends MongoRepository<AssetData, String> {

    Optional<AssetData> findBySerialNumber(String name);

    Optional<AssetData> findBySerialNumberAndOdcName(String no, String odc);

//    Optional<AssetData> findByAssetInfosAndOdcName(AssetInfo info, String odc);

    Optional<AssetData> findByRequestId(int id);

    @DeleteQuery
	void deleteBySerialNumber(String string);

//    List<AssetData> findByOdcNameAndStatus(String odcName, String status);
//
//    List<AssetData> findByStatus(String status);
//
//    public void deleteBySerialNumber(String serialNumber);

}
