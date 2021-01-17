//package com.demo.Visitor.access.model;
//
//import com.demo.Visitor.access.dto.AssetDto;
//import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDateTime;
//
//@Document
//public class AssetRequests {
//
//    @Indexed(unique = true)
//    private int requestId;
//    private String serialNumber;
//    private String name;
//    private String type;
//    private String empId;
//    private String odcName;
//    private String reason;
//    private String assetCondition;
//    private LocalDateTime dateOfApproval;
//    private String status;
//
//    public AssetRequests() {
//    }
//
//    public AssetRequests(AssetDto assetDto) {
//        this.serialNumber = assetDto.serialNumber;
//        this.name = assetDto.name;
//        this.type = assetDto.type;
//        this.empId = assetDto.empId;
//        this.odcName = assetDto.odcName;
//        this.reason = assetDto.reason;
//    }
//
//    public int getRequestId() {
//        return requestId;
//    }
//
//    public void setRequestId(int requestId) {
//        this.requestId = requestId;
//    }
//
//    public String getSerialNumber() {
//        return serialNumber;
//    }
//
//    public void setSerialNumber(String serialNumber) {
//        this.serialNumber = serialNumber;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getEmpId() {
//        return empId;
//    }
//
//    public void setEmpId(String empId) {
//        this.empId = empId;
//    }
//
//    public String getOdcName() {
//        return odcName;
//    }
//
//    public void setOdcName(String odcName) {
//        this.odcName = odcName;
//    }
//
//    public String getReason() {
//        return reason;
//    }
//
//    public void setReason(String reason) {
//        this.reason = reason;
//    }
//
//    public String getAssetCondition() {
//        return assetCondition;
//    }
//
//    public void setAssetCondition(String assetCondition) {
//        this.assetCondition = assetCondition;
//    }
//
//    public LocalDateTime getDateOfApproval() {
//        return dateOfApproval;
//    }
//
//    public void setDateOfApproval(LocalDateTime dateOfApproval) {
//        this.dateOfApproval = dateOfApproval;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Override
//    public String toString() {
//        return "AssetRequests{" +
//                "requestId='" + requestId + '\'' +
//                ", serialNumber='" + serialNumber + '\'' +
//                ", name='" + name + '\'' +
//                ", type='" + type + '\'' +
//                ", empId='" + empId + '\'' +
//                ", odcName='" + odcName + '\'' +
//                ", reason='" + reason + '\'' +
//                ", assetCondition='" + assetCondition + '\'' +
//                ", dateOfApproval=" + dateOfApproval +
//                ", status='" + status + '\'' +
//                '}';
//    }
//}
