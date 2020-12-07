package com.demo.Visitor.access.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ODCList {
	
	@Id
	private int odcId;
	private String odcName;
	
	public ODCList(int odcId, String odcName) {
		super();
		this.odcId = odcId;
		this.odcName = odcName;
	}
	public int getOdcId() {
		return odcId;
	}
	public void setOdcId(int odcId) {
		this.odcId = odcId;
	}
	public String getOdcName() {
		return odcName;
	}
	public void setOdcName(String odcName) {
		this.odcName = odcName;
	}
	
	@Override
	public String toString() {
		return "ODCList [odcId=" + odcId + ", odcName=" + odcName + "]";
	}
	
}
