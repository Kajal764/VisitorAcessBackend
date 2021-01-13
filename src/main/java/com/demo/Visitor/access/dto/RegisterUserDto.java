package com.demo.Visitor.access.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
	
	public RegisterUserDto() {
		
	}

    public RegisterUserDto(@Pattern(regexp = "^[A-Za-z]{2,}$", message = "First Name is invalid") String firstName,
			@Pattern(regexp = "^[A-Za-z]{2,}$", message = "Last Name is invalid") String lastName,
			@Pattern(regexp = "^([a-zA-Z0-9]{1,}[.]?[a-zA-Z0-9]{1,}?[@][a-zA-Z]{1,}([.][a-zA-Z]{2,4}){1,2})$", message = "Email must be valid") String email,
			@Pattern(regexp = "^((?=.*[A-Z])(?=.*[@#$%]).{6,})$", message = "Password Should contain One Uppercase and Symbol and greater than 6 character") String password,
			@Pattern(regexp = "^[0-9]\\d{6}$", message = "Employee Id should be 7 digit") String empId,
			@Pattern(regexp = "^[0-9]\\d{9}$", message = "Mobile no invalid") long mobileNo, String role,
			String managerName, String odc) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.empId = empId;
		this.mobileNo = mobileNo;
		this.role = role;
		this.managerName = managerName;
		this.odc = odc;
	}

	@Column(nullable = false)
    @Pattern(regexp = "^[A-Za-z]{2,}$", message = "First Name is invalid")
    public String firstName;

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-z]{2,}$", message = "Last Name is invalid")
    public String lastName;

    @Column(nullable = false)
    @Pattern(regexp = "^([a-zA-Z0-9]{1,}[.]?[a-zA-Z0-9]{1,}?[@][a-zA-Z]{1,}([.][a-zA-Z]{2,4}){1,2})$", message = "Email must be valid")
    public String email;

    @Column(nullable = false)
    @Pattern(regexp = "^((?=.*[A-Z])(?=.*[@#$%]).{6,})$", message = "Password Should contain One Uppercase and Symbol and greater than 6 character")
    public String password;

    @Column(nullable = false)
    @Pattern(regexp = "^[0-9]\\d{6}$", message = "Employee Id should be 7 digit")
    public String empId;

    @Column(nullable = false)
    @Pattern(regexp = "^[0-9]\\d{9}$", message = "Mobile no invalid")
    public long mobileNo;

    @Column(nullable = false)
    public String role;

    @Column(nullable = false)
    public String managerName;

    @Column(nullable = false)
    public String odc;

}
