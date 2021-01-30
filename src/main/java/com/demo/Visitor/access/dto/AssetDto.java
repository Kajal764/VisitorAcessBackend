package com.demo.Visitor.access.dto;

import java.util.List;

public class AssetDto {

    public List<AssetInfo> assetInfos;
    public String empId;
    public String odcName;
    public String movement;

    public AssetDto() {
    }

    public AssetDto(String empId, String odcName, String movement) {
        this.empId = empId;
        this.odcName = odcName;
        this.movement = movement;
    }

    @Override
    public String toString() {
        return "AssetDto{" +
                "assetInfos=" + assetInfos +
                ", empId='" + empId + '\'' +
                ", odcName='" + odcName + '\'' +
                ", movement='" + movement + '\'' +
                '}';
    }
}
