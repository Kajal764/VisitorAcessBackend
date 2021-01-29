package com.demo.Visitor.access.model;

import com.demo.Visitor.access.dto.AssetDto;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class AssetData {

    @Indexed(unique = true)
    private int requestId;
    private String serialNumber;
    private String description;
    private String type;
    private String empId;
    private String odcName;
    private String status;
    private boolean isCurrentOdc;
    public String movement;
    private LocalDateTime fromDate;
    private LocalDateTime tillDate;
    private String requestStatus;

    public AssetData() {
    }

    public AssetData(AssetDto assetDto) {
        this.empId = assetDto.empId;
        this.odcName = assetDto.odcName;
        this.movement = assetDto.movement;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getOdcName() {
        return odcName;
    }

    public void setOdcName(String odcName) {
        this.odcName = odcName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCurrentOdc() {
        return isCurrentOdc;
    }

    public void setCurrentOdc(boolean currentOdc) {
        isCurrentOdc = currentOdc;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getTillDate() {
        return tillDate;
    }

    public void setTillDate(LocalDateTime tillDate) {
        this.tillDate = tillDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    @Override
    public String toString() {
        return "AssetData{" +
                "requestId=" + requestId +
                ", serialNumber='" + serialNumber + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", empId='" + empId + '\'' +
                ", odcName='" + odcName + '\'' +
                ", status='" + status + '\'' +
                ", isCurrentOdc=" + isCurrentOdc +
                ", movement='" + movement + '\'' +
                ", fromDate=" + fromDate +
                ", tillDate=" + tillDate +
                ", requestStatus='" + requestStatus + '\'' +
                '}';
    }
}
