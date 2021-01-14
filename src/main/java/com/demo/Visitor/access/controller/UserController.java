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
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<?> userLogin(@PathVariable String empId, @PathVariable String password) {
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
    private ResponseDto deleteUser(@PathVariable String empId) {
        userService.deleteUser(empId);
        return new ResponseDto("Employee data deleted", 200);
    }

    @PostMapping(value = "/raiseOdcRequest")
    public ResponseEntity<?> raiseOdcRequest(@RequestBody VisitorRequest visitorRequest) {
        ResponseEntity<?> responseEntity = null;
        try {
            boolean success = userService.insertIntoVisitorRequest(visitorRequest);
            responseEntity = new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @GetMapping(value = "/viewUserRequests/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewUserRequest(@PathVariable String empId) {
        ResponseEntity<?> responseEntity = null;
        try {
            List<VisitorRequest> visitorRequests = userService.viewOdcRequest(empId);
            responseEntity = new ResponseEntity<>(visitorRequests, HttpStatus.ACCEPTED);
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @GetMapping(value = "/viewOdcManagers/{odcName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewOdcManagers(@PathVariable String odcName) {
        ResponseEntity<?> responseEntity = null;
        try {
            List<UserInfo> odcManagers = userService.findAllODCManagerByOdcName(odcName);
            responseEntity = new ResponseEntity<>(odcManagers, HttpStatus.ACCEPTED);
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @GetMapping(value = "/odcList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> odcLists() {
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

    @GetMapping(value = "/getAllOdcManagerRequests/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOdcManagerRequests(@PathVariable String empId) throws BusinessException {
        ResponseEntity<?> responseEntity = null;
        List<VisitorRequest> visitorRequests = userService.findAllAcceptedRequestedByManager(empId);
        if (visitorRequests != null)
            responseEntity = new ResponseEntity<>(visitorRequests, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @GetMapping(value = "/pendingVisitorRequest/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> visitorRequestByStatus(@PathVariable String empId) throws BusinessException {
        ResponseEntity<?> responseEntity = null;
        List<VisitorRequest> visitorRequests = userService.getPendingVisitorRequest(empId);
        if (visitorRequests != null)
            responseEntity = new ResponseEntity<>(visitorRequests, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @PostMapping(value = "/approveOrRejectAccess", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> approveAccess(@RequestBody VisitorRequest visitorRequest) throws BusinessException {
        ResponseEntity<?> responseEntity = null;
        try {
            boolean success = userService.approveOrRejectOdcRequest(visitorRequest);
            if (success)
                responseEntity = new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @GetMapping(value = "manager/registration-request/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<UserInfo> getRegistrationRequestOfEmployee(@PathVariable String empId) {
        List<UserInfo> registrationRequestOfEmployee = userService.getRegistrationRequestOfEmployee(empId);
        if (registrationRequestOfEmployee.size() == 0) {
            throw new LoginException("No pending request", 400);
        }
        return registrationRequestOfEmployee;
    }

    @GetMapping(value = "/managerList", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<UserInfo> getmanagers() {
        List<UserInfo> usr = userService.managerList();
        return usr;
    }

    @PostMapping(value = "/registration-request", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseDto acceptOrRejectRequest(@RequestBody List<RegistrationRequest> registrationRequest) {
        return userService.registrationRequest(registrationRequest);
    }

    @PostMapping(value = "/addOdc", consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @DeleteMapping(value = "/deleteOdc/{odcName}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseDto deleteOdcById(@PathVariable String odcName) {
        userService.deleteOdc(odcName);
        return new ResponseDto("ODC deleted from the list", 200);
    }

}
