package com.demo.Visitor.access.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VisitorRequest {

    private int empId;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String odc;
    private String status;
    private int managerEmpID;
    private int employee;

    public VisitorRequest() {
    }
//
//    public VisitorRequest(int empId, String startDate, String endDate, String startTime, String endTime, String odc, String status, int managerEmpID, boolean isEMP) {
//        this.empId = empId;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.odc = odc;
//        this.status = status;
//        this.managerEmpID = managerEmpID;
//        this.isEMP = isEMP;
//    }
//
//    public int getEmpId() {
//        return empId;
//    }
//
//    public void setEmpId(int empId) {
//        this.empId = empId;
//    }
//
//    public String getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(String startDate) {
//        this.startDate = startDate;
//    }
//
//    public String getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(String endDate) {
//        this.endDate = endDate;
//    }
//
//    public String getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(String startTime) {
//        this.startTime = startTime;
//    }
//
//    public String getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(String endTime) {
//        this.endTime = endTime;
//    }
//
//    public String getOdc() {
//        return odc;
//    }
//
//    public void setOdc(String odc) {
//        this.odc = odc;
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
//    public int getManagerEmpID() {
//        return managerEmpID;
//    }
//
//    public void setManagerEmpID(int managerEmpID) {
//        this.managerEmpID = managerEmpID;
//    }
//
//    public boolean isEMP() {
//        return isEMP;
//    }
//
//    public void setEMP(boolean EMP) {
//        isEMP = EMP;
//    }
//
//    @Override
//    public String toString() {
//        return "VisitorRequest{" +
//                "empId=" + empId +
//                ", startDate='" + startDate + '\'' +
//                ", endDate='" + endDate + '\'' +
//                ", startTime='" + startTime + '\'' +
//                ", endTime='" + endTime + '\'' +
//                ", odc='" + odc + '\'' +
//                ", status='" + status + '\'' +
//                ", managerEmpID=" + managerEmpID +
//                ", isEMP=" + isEMP +
//                '}';
//    }
}
