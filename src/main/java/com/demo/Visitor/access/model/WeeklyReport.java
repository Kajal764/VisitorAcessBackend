package com.demo.Visitor.access.model;

public class WeeklyReport {
	
	public String empId;
	public int reportId;
	public String managerEmpId;
	public String startDate;
	public String endDate;
	public String sprintData;
	public String report;
	
	
	public WeeklyReport() {
	}
	
	public WeeklyReport(String empId, int reportId, String managerEmpId, String startDate, String endDate,
			String sprintDetails, String report) {
		this.empId = empId;
		this.reportId = reportId;
		this.managerEmpId = managerEmpId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sprintData = sprintDetails;
		this.report = report;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	public String getManagerEmpId() {
		return managerEmpId;
	}
	public void setManagerEmpId(String managerEmpId) {
		this.managerEmpId = managerEmpId;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
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
	public String getSprintDetails() {
		return sprintData;
	}
	public void setSprintDetails(String sprintDetails) {
		this.sprintData = sprintDetails;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	@Override
	public String toString() {
		return "WeeklyReport [empId=" + empId + ", reportId=" + reportId + ", managerEmpId=" + managerEmpId
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", sprintData=" + sprintData + ", report="
				+ report + "]";
	}
	
	
	
	

	

	

}
