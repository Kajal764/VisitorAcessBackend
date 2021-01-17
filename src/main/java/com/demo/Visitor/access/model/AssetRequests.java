package com.demo.Visitor.access.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AssetRequests {
	
	@Indexed(unique=true)
	private String requestId;
	private String serialNumber;
	private String name;
	private String type;
	@Indexed(unique=true)
	private String empId;
	private String dateAndTime;
	private String odcName;
	private String reason;
	private boolean flag;
	private String status;
	
	public AssetRequests() {
		
	}

	public AssetRequests(String requestId, String serialNumber, String name, String type, String empId,
			String dateAndTime, String odcName, String reason, boolean flag, String status) {
		super();
		this.requestId = requestId;
		this.serialNumber = serialNumber;
		this.name = name;
		this.type = type;
		this.empId = empId;
		this.dateAndTime = dateAndTime;
		this.odcName = odcName;
		this.reason = reason;
		this.flag = flag;
		this.status = status;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AssetRequests [requestId=" + requestId + ", serialNumber=" + serialNumber + ", name=" + name + ", type="
				+ type + ", empId=" + empId + ", dateAndTime=" + dateAndTime + ", odcName=" + odcName + ", reason="
				+ reason + ", flag=" + flag + ", status=" + status + "]";
	}


}
