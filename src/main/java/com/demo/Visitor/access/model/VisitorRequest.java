package com.demo.Visitor.access.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Getter
@Setter
@ToString
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
    private int employee;

    public VisitorRequest() {
    }


}
