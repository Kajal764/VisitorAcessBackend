package com.demo.Visitor.access.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VisitorRequest {

	private int empId;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	@DBRef
	private List<ODCList> odc;
	private String status;
	
	public VisitorRequest(int empId, String startDate, String endDate, String startTime, String endTime,
			List<ODCList> odc, String status) {
		super();
		this.empId = empId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.odc = odc;
		this.status = status;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
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
	public List<ODCList> getOdc() {
		return odc;
	}
	public void setOdc(List<ODCList> odc) {
		this.odc = odc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "VisitorRequest [empId=" + empId + ", startDate=" + startDate + ", endDate=" + endDate + ", startTime="
				+ startTime + ", endTime=" + endTime + ", odc=" + odc + ", status=" + status + "]";
	}
	
	
	
	
}
