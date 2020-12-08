package com.demo.Visitor.access.controller;

import com.demo.Visitor.access.dto.RegisterUserDto;
import com.demo.Visitor.access.dto.RegistrationRequest;
import com.demo.Visitor.access.dto.ResponseDto;
import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.exception.LoginException;
import com.demo.Visitor.access.model.ODCList;
import com.demo.Visitor.access.model.UserInfo;
import com.demo.Visitor.access.model.VisitorRequest;
import com.demo.Visitor.access.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseDto register(@Valid @RequestBody RegisterUserDto registerUserDto) throws LoginException {
		boolean register = userService.register(registerUserDto);
		if (register)
			return new ResponseDto("Registration Successful", 200);
		throw new LoginException("Employee Id Exist", 400);
	}

	@PostMapping(value = "/login/{empId}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<?> userLogin(@PathVariable int empId, @PathVariable String password) {
		UserInfo users;
		try {
			users = userService.login(empId, password);
			return new ResponseEntity<>(users, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(value = "/list")
	private List<UserInfo> getUserList() {
		List<UserInfo> allUserData = userService.getAllUserData();
		if (allUserData.size() == 0) {
			throw new LoginException("User Not Present", 400);
		}
		return allUserData;
	}

	@DeleteMapping(value = "/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseDto deleteUser(@PathVariable int empId) {
		userService.deleteUser(empId);
		return new ResponseDto("Employee data deleted", 200);
	}

	@RequestMapping(value = "/raiseOdcRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> raiseOdcRequest(@RequestBody VisitorRequest visitorRequest) throws BusinessException {
		ResponseEntity<?> responseEntity = null;
		try {
			System.out.println("HIIIiii");
			System.out.println(visitorRequest);
			boolean success = userService.insertIntoVisitorRequest(visitorRequest);
			responseEntity = new ResponseEntity<>(success, HttpStatus.CREATED);
		} catch (BusinessException e) {
			responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
	}

	@RequestMapping(value = "/viewUserRequests/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewUserRequest(@PathVariable int empId) throws BusinessException {
		ResponseEntity<?> responseEntity = null;
		try {
			List<VisitorRequest> visitorRequests = userService.findAllByEmpId(empId);
			responseEntity = new ResponseEntity<>(visitorRequests, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
	}

	@RequestMapping(value = "/odcList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> odcLists() throws BusinessException {
		ResponseEntity<?> responseEntity = null;
		try {
			List<ODCList> odcLists = userService.findAllODC();
			if (odcLists != null)
				responseEntity = new ResponseEntity<>(odcLists, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/addOdc", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addODC(@RequestBody ODCList odc) throws BusinessException {
		ResponseEntity<?> responseEntity = null;
		try {
			boolean success = userService.addOdc(odc);
			responseEntity = new ResponseEntity<>(success, HttpStatus.CREATED);
		} catch (BusinessException e) {
			responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/visitorRequestByStatus/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> visitorRequestByStatus(@PathVariable String status) throws BusinessException {
		ResponseEntity<?> responseEntity = null;
		try {
			List<VisitorRequest> visitorRequests = userService.getAllByStatus(status);
			if (visitorRequests != null)
				responseEntity = new ResponseEntity<>(visitorRequests, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
	}
	
	@PostMapping(value = "/approveAccess", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> approveAccess(@RequestBody VisitorRequest visitorRequest) throws BusinessException {
		ResponseEntity<?> responseEntity = null;
		try {
			boolean success = userService.approveOdcRequest(visitorRequest);
			if (success)
				responseEntity = new ResponseEntity<>(success, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
	}
	@PostMapping(value = "/rejectAccess", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> rejectAccess(@RequestBody VisitorRequest visitorRequest) throws BusinessException {
		ResponseEntity<?> responseEntity = null;
		try {
			boolean success = userService.rejectOdcRequest(visitorRequest);
			if (success)
				responseEntity = new ResponseEntity<>(success, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
	}
	
}
