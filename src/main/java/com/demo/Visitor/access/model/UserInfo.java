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
}
