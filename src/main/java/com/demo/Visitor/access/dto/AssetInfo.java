package com.demo.Visitor.access.dto;

public class AssetInfo {

    public String serialNumber;
    public String description;
    public String type;
    public String status;

    public AssetInfo() {
    }

    public AssetInfo(String serialNumber, String description, String type, String status) {
        this.serialNumber = serialNumber;
        this.description = description;
        this.type = type;
        this.status = status;
    }

    @Override
    public String toString() {
        return "AssetInfo{" +
                "serialNumber='" + serialNumber + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
