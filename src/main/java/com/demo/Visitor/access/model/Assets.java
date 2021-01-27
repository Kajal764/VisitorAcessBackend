package com.demo.Visitor.access.model;

import java.sql.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Assets{
	
	@Indexed(unique = true)
	private String serialNumber;
	
	private String name;
	
	private String type;
	
    private int empId;
		
	private Date approvedDateAndTime;
	
	private String odcName;
	
	private String reason;
	
	public Assets() {
		
	}

	public Assets(String serialNumber, String name, String type, int empId, Date approvedDateAndTime, String odcName,
			String reason) {
		super();
		this.serialNumber = serialNumber;
		this.name = name;
		this.type = type;
		this.empId = empId;
		this.approvedDateAndTime = approvedDateAndTime;
		this.odcName = odcName;
		this.reason = reason;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public Date getapprovedDateAndTime() {
		return approvedDateAndTime;
	}

	public void setapprovedDateAndTime(Date approvedDateAndTime) {
		this.approvedDateAndTime = approvedDateAndTime;
	}

	public String getOdcName() {
		return odcName;
	}

	public void setOdcName(String odcName) {
		this.odcName = odcName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "AssetList [serialNumber=" + serialNumber + ", name=" + name + ", type=" + type + ", empId=" + empId
				+ ", approvedDateAndTime=" + approvedDateAndTime + ", odcName=" + odcName + ", reason=" + reason + "]";
	}


	

}
