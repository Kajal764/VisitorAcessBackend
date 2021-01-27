package com.demo.Visitor.access.model;

import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@ToString
public class ODCList {

	@Id
	private String _id;
  
	private int odcId;

    @Indexed(unique = true)
    private String odcName;

    private boolean flag;

    public ODCList() {
    }

    public ODCList(int odcId, String odcName, boolean flag) {
        super();
        this.odcId = odcId;
        this.odcName = odcName;
        this.flag = flag;
    }
    
    public String get_id() {
  		return _id;
  	}

  	public void set_id(String _id) {
  		this._id = _id;
  	}

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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
        return "ODCList [odcId=" + odcId + ", odcName=" + odcName + ", flag=" + flag + "]";
    }

}
