package com.demo.Visitor.access.model;

import com.demo.Visitor.access.dto.RegisterUserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document
@NoArgsConstructor
public class UserInfo {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Indexed(unique = true)
    private int empId;

    private long mobileNo;

    private String role;

    private String managerName;

    private boolean accountActive;
    
    private String odc;
    
    public UserInfo() {
    	
    }
    
    public UserInfo(String firstName, String lastName, String email, String password, int empId, long mobileNo,
			String role, String managerName, boolean accountActive, String odc) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.empId = empId;
		this.mobileNo = mobileNo;
		this.role = role;
		this.managerName = managerName;
		this.accountActive = accountActive;
		this.odc = odc;
	}


	public boolean getAccountActive() {
		return accountActive;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setAccountActive(boolean accountActive) {
		this.accountActive = accountActive;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOdc() {
		return odc;
	}

	public void setOdc(String odc) {
		this.odc = odc;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
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
    }
	
	@Override
	public String toString() {
		return "UserInfo [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", empId=" + empId + ", mobileNo=" + mobileNo + ", role=" + role + ", managerName="
				+ managerName + ", accountActive=" + accountActive + ", odc=" + odc + "]";
	}
}
