package com.demo.Visitor.access.dto;

public class RegistrationRequest {

    public String empId;
    public boolean status;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String empId, boolean status) {
        this.empId = empId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "empId='" + empId + '\'' +
                ", status=" + status +
                '}';
    }
}
