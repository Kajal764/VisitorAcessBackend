package com.demo.Visitor.access.model;

import com.demo.Visitor.access.dto.RegisterUserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
public class UserInfo {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Indexed(unique = true)
    private String empId;

    private long mobileNo;

    private List<String> role;

    private String managerName;

    private boolean accountActive;

    private String odc;

    private boolean flag;

    public UserInfo() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public boolean isAccountActive() {
        return accountActive;
    }

    public void setAccountActive(boolean accountActive) {
        this.accountActive = accountActive;
    }

    public String getOdc() {
        return odc;
    }

    public void setOdc(String odc) {
        this.odc = odc;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public UserInfo(RegisterUserDto registerUserDto) {
        this.firstName = registerUserDto.firstName;
        this.lastName = registerUserDto.lastName;
        this.email = registerUserDto.email;
        this.password = registerUserDto.password;
        this.empId = registerUserDto.empId;
        this.mobileNo = registerUserDto.mobileNo;
        this.role = registerUserDto.role;
        this.managerName = registerUserDto.managerName;
        this.odc = registerUserDto.odc;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", empId='" + empId + '\'' +
                ", mobileNo=" + mobileNo +
                ", role=" + role +
                ", managerName='" + managerName + '\'' +
                ", accountActive=" + accountActive +
                ", odc='" + odc + '\'' +
                ", flag=" + flag +
                '}';
    }
}
