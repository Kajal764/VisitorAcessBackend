package com.demo.Visitor.access.model;

import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@ToString
public class ODCList {

    private int odcId;

    @Indexed(unique = true)
    private String odcName;

    public ODCList() {
    }

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
