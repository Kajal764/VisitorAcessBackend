package com.demo.Visitor.access.service;

import com.demo.Visitor.access.dto.RegisterUserDto;
import com.demo.Visitor.access.dto.RegistrationRequest;
import com.demo.Visitor.access.dto.ResponseDto;
import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.exception.LoginException;
import com.demo.Visitor.access.model.ODCList;
import com.demo.Visitor.access.model.UserInfo;
import com.demo.Visitor.access.model.VisitorRequest;
import com.demo.Visitor.access.repository.ODCRepository;
import com.demo.Visitor.access.repository.UserRepository;
import com.demo.Visitor.access.repository.VisitorRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ODCRepository odcRepository;

    @Autowired
    VisitorRequestRepository visitorRequestRepository;

    @Autowired
    BCryptPasswordEncoder bcryptPasswordEncoder;

    public boolean register(RegisterUserDto registerUserDto) {
        registerUserDto.password = bcryptPasswordEncoder.encode(registerUserDto.password);
        UserInfo userInfo = new UserInfo(registerUserDto);
        Optional<UserInfo> user = userRepository.findByEmpId(registerUserDto.empId);
        if (user.isPresent())
            return false;
        userInfo.setAccountActive(false);
        userRepository.save(userInfo);
        return true;
    }

    public UserInfo login(int empId, String password) throws BusinessException {
        Optional<UserInfo> user = userRepository.findByEmpId(empId);
        if (user.isPresent()) {
            if (user.get().getEmpId() == empId) {
                if (bcryptPasswordEncoder.matches(password, user.get().getPassword())) {
                    if (user.get().getAccountActive()) {
                        return user.get();
                    }
                    throw new BusinessException("Registration request not approved");
                }
                throw new BusinessException("Invalid credentials..");
            }
        }
        throw new BusinessException("Account not exist");
    }

    public List<UserInfo> getAllUserData() {
        List<UserInfo> userList = userRepository.findAll();
        userList.removeIf(value -> value.getRole().equals("Admin"));
        return userList;
    }

    public void deleteUser(int empId) {
        userRepository.deleteByEmpId(empId);
    }

    public boolean insertIntoVisitorRequest(VisitorRequest visitorRequest) throws BusinessException {
        Optional<UserInfo> user = userRepository.findByEmpId(visitorRequest.getEmpId());
        int random_id;
        if (visitorRequest.getEmployee() == 0) {
            visitorRequest.setManagerEmpID(6666666);
        } else {
            String[] name = user.get().getManagerName().split(" ");
            Optional<UserInfo> manager = userRepository.findByFirstName(name[0]);
            visitorRequest.setManagerEmpID(manager.get().getEmpId());
        }
        Random random = new Random();
        random_id = random.nextInt();
        Optional<VisitorRequest> byVisitorRequestId = visitorRequestRepository.findByVisitorRequestId(random_id);
        if (byVisitorRequestId.isPresent()) {
            random_id = random.nextInt();
        }
        visitorRequest.setVisitorRequestId(random_id);
        VisitorRequest visitorRequestAdded = visitorRequestRepository.insert(visitorRequest);
        if (visitorRequestAdded == null)
            throw new BusinessException("Something went wrong!!!Please try again");
        else
            return true;
    }

    public List<VisitorRequest> viewOdcRequest(int empId) throws BusinessException {
        List<VisitorRequest> visitorRequests = visitorRequestRepository.findAllByEmpId(empId);
        if (visitorRequests == null)
            throw new BusinessException("No requests has been raised for this employee Id");
        else
            return visitorRequests;
    }

    public List<ODCList> findAllODC() throws BusinessException {
        List<ODCList> odcLists = odcRepository.findAll();
        if (odcLists == null)
            throw new BusinessException("No ODC is present");
        else
            return odcLists;
    }

    public boolean addOdc(ODCList odc) throws BusinessException {
        Optional<ODCList> odcName = odcRepository.findByOdcName(odc.getOdcName());
        if (odcName.isPresent())
            throw new LoginException("Odc Already Exist", 400);
        ODCList odcAdded = odcRepository.insert(odc);
        if (odcAdded == null)
            throw new BusinessException("Something went wrong!!!Please try again");
        else
            return true;
    }


    public boolean approveOdcRequest(VisitorRequest visitorRequest) throws BusinessException {
        visitorRequestRepository.deleteByVisitorRequestId(visitorRequest.getVisitorRequestId());
        visitorRequest.setStatus("Approved");
        VisitorRequest success = visitorRequestRepository.save(visitorRequest);
        if (success == null)
            throw new BusinessException("Request Cannot be Approved");
        else
            return true;
    }

    public boolean rejectOdcRequest(VisitorRequest visitorRequest) throws BusinessException {
        visitorRequestRepository.deleteByVisitorRequestId(visitorRequest.getVisitorRequestId());
        visitorRequest.setStatus("Rejected");
        VisitorRequest success = visitorRequestRepository.save(visitorRequest);
        if (success == null)
            throw new BusinessException("Request Cannot be Rejected");
        else
            return true;
    }

    public List<UserInfo> getManagerList() {
        List<UserInfo> userInfoList = userRepository.findAll();
        userInfoList.removeIf(user -> user.getRole().equals("Employee"));
        userInfoList.removeIf(user -> user.getAccountActive() == true);
        return userInfoList;
    }

    public ResponseDto registrationRequest(RegistrationRequest registrationRequest) {
        Optional<UserInfo> userInfo = userRepository.findByEmpId(registrationRequest.empId);
        if (registrationRequest.status == true) {
            userRepository.deleteByEmpId(userInfo.get().getEmpId());
            userInfo.get().setAccountActive(true);
            userRepository.save(userInfo.get());
            System.out.println(userInfo.get());
            return new ResponseDto("Request accept", 200);
        }
        userRepository.deleteByEmpId(registrationRequest.empId);
        return new ResponseDto("Request reject", 200);
    }

    public List<UserInfo> getRegistrationRequestOfEmployee(int empId) {
        Optional<UserInfo> manager = userRepository.findByEmpId(empId);
        String name = manager.get().getFirstName() + " " + manager.get().getLastName();
        List<UserInfo> userInfoList = userRepository.findAllByManagerName(name);
        userInfoList.removeIf(value -> value.getAccountActive()==true);
        return userInfoList;
    }

    public List<UserInfo> managerList() {
        List<UserInfo> userList = userRepository.findAll();
        userList.removeIf((value -> (value.getRole().equals("Employee") | value.getRole().equals("Admin"))));
        return userList;
    }

    public boolean deleteOdc(String odcId) {
        odcRepository.deleteByOdcName(odcId);
        return true;
    }

    public List<VisitorRequest> getPendingVisitorRequest(int empId) throws BusinessException {
        Optional<UserInfo> user = userRepository.findByEmpId(empId);
        List<VisitorRequest> visitorRequestList = visitorRequestRepository.findByManagerEmpID(empId);
        if (user.get().getRole().equals("Manager")) {
            visitorRequestList.removeIf(value -> value.getEmployee() == 0);
        } else {
            visitorRequestList.removeIf(value -> value.getEmployee() == 1);
        }
        if (visitorRequestList == null)
            throw new BusinessException("No Requests Raised!!");
        return visitorRequestList;
    }

}
