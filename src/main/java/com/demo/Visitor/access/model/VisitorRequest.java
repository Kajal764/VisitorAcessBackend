package com.demo.Visitor.access.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VisitorRequest {

    @Indexed(unique = true)
    private int visitorRequestId;
    private String empId;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String odc;
    private String status;
    private Object managerEmpID;
    private boolean odcExist=true;

    public VisitorRequest() {
    }

    public int getVisitorRequestId() {
        return visitorRequestId;
    }

    public void setVisitorRequestId(int visitorRequestId) {
        this.visitorRequestId = visitorRequestId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOdc() {
        return odc;
    }

    public void setOdc(String odc) {
        this.odc = odc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getManagerEmpID() {
        return managerEmpID;
    }

    public void setManagerEmpID(Object managerEmpID) {
        this.managerEmpID = managerEmpID;
    }

    public boolean isOdcExist() {
        return odcExist;
    }

    public void setOdcExist(boolean odcExist) {
        this.odcExist = odcExist;
    }

    @Override
    public String toString() {
        return "VisitorRequest{" +
                "visitorRequestId=" + visitorRequestId +
                ", empId='" + empId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", odc='" + odc + '\'' +
                ", status='" + status + '\'' +
                ", managerEmpID=" + managerEmpID +
                ", odcExist=" + odcExist +
                '}';
    }


    public VisitorRequest(int visitorRequestId, String empId, String startDate, String endDate, String startTime, String endTime, String odc, String status, Object managerEmpID, boolean odcExist) {
        this.visitorRequestId = visitorRequestId;
        this.empId = empId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.odc = odc;
        this.status = status;
        this.managerEmpID = managerEmpID;
        this.odcExist = odcExist;
    }
}
