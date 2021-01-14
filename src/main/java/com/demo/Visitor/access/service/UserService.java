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

import java.util.ArrayList;
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
        if (userInfo.getRole().equals("odcManager"))
            userInfo.setManagerName("admin admin");
        userInfo.setAccountActive(false);
        userInfo.setFlag(true);
        userRepository.save(userInfo);
        return true;
    }

    public UserInfo login(String empId, String password) throws BusinessException {

        Optional<UserInfo> user = userRepository.findByEmpId(empId);
        if (user.isPresent()) {
            if (user.get().getEmpId().equals(empId)) {
                if (bcryptPasswordEncoder.matches(password, user.get().getPassword())) {
                    if (user.get().isAccountActive()) {
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
        userList.removeIf(user -> user.isFlag() == false);
        userList.removeIf(userInfo -> userInfo.isAccountActive() == false);
        userList.removeIf(value -> value.getRole().equals("Admin"));
        return userList;
    }

    public void deleteUser(String empId) {
        Optional<UserInfo> user = userRepository.findByEmpId(empId);
        userRepository.deleteByEmpId(empId);
        user.get().setFlag(false);
        userRepository.save(user.get());
    }

    public boolean insertIntoVisitorRequest(VisitorRequest visitorRequest) throws BusinessException {
        Optional<UserInfo> user = userRepository.findByEmpId(visitorRequest.getEmpId());
        int random_id;
//        if (visitorRequest.getEmployee() == 0) {
//            visitorRequest.setManagerEmpID("Admin");
//        }else if (visitorRequest.getEmployee() == 2) {
//            visitorRequest.setManagerEmpID("7777777");
//        }
//        else {
        String[] name = user.get().getManagerName().split(" ");
        Optional<UserInfo> manager = userRepository.findByFirstName(name[0]);
        visitorRequest.setManagerEmpID(manager.get().getEmpId());
//        }
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

    public List<VisitorRequest> viewOdcRequest(String empId) throws BusinessException {
        List<VisitorRequest> visitorRequests = visitorRequestRepository.findAllByEmpId(empId);
        visitorRequests.removeIf(value -> value.isOdcExist() == false);
        if (visitorRequests == null)
            throw new BusinessException("No requests has been raised for this employee Id");
        else
            return visitorRequests;
    }

    public List<UserInfo> findAllODCManagerByOdcName(String odcName) throws BusinessException {
        List<UserInfo> odcManagers = userRepository.findAllByOdc(odcName);
        if (odcManagers == null)
            throw new BusinessException("No ODC Manager is present");
        else
            return odcManagers;
    }

    public List<ODCList> findAllODC() throws BusinessException {
        List<ODCList> odcLists = odcRepository.findAll();
        odcLists.removeIf(odc -> odc.getFlag() == false);
        if (odcLists == null)
            throw new BusinessException("No ODC is present");
        else
            return odcLists;
    }

    public List<VisitorRequest> findAllAcceptedRequestedByManager(String empId) throws BusinessException {
        Optional<UserInfo> user = userRepository.findByEmpId(empId);
        List<VisitorRequest> visitorRequestList = new ArrayList<VisitorRequest>();
        if (user.isPresent()) {
            List<String> odcList = user.get().getOdc();
            odcList.stream().forEach(odc -> {
                List<VisitorRequest> accepted_by_manager = visitorRequestRepository.findAllByOdcAndStatus(odc, "Accepted By Manager");
                visitorRequestList.addAll(accepted_by_manager);
            });
            return visitorRequestList;
        }
        return null;
    }

    public boolean addOdc(ODCList odc) throws BusinessException {
        Optional<ODCList> odcName = odcRepository.findByOdcName(odc.getOdcName());
        if (odcName.isPresent() && odcName.get().getFlag() == true)
            throw new LoginException("Odc Already Exist", 400);
        if(odcName.get().getFlag()==false && odcName.isPresent()){
            odcRepository.deleteByOdcName(odcName.get().getOdcName());
        }
        odc.setFlag(true);
        ODCList odcAdded = odcRepository.save(odc);
        List<VisitorRequest> allByOdc = visitorRequestRepository.findAllByOdc(odc.getOdcName());
        allByOdc.forEach(req -> {
            req.setOdcExist(true);
            visitorRequestRepository.deleteByVisitorRequestId(req.getVisitorRequestId());
            visitorRequestRepository.save(req);
        });
        if (odcAdded == null)
            throw new BusinessException("Something went wrong!!!Please try again");
        else
            return true;
    }

//    public boolean approveOrRejectOdcRequest(VisitorRequest visitorRequest) throws BusinessException {
//        visitorRequestRepository.deleteByVisitorRequestId(visitorRequest.getVisitorRequestId());
//        if (visitorRequest.getStatus().equals("Accepted By Manager"))
//            visitorRequest.setStatus("Accepted By Manager");
//        else if (visitorRequest.getStatus().equals("Approved"))
//            visitorRequest.setStatus("Approved");
//        else if (visitorRequest.getStatus().equals("Rejected By Manager"))
//            visitorRequest.setStatus("Rejected By Manager");
//        else if (visitorRequest.getStatus().equals("Rejected"))
//            visitorRequest.setStatus("Rejected");
//        VisitorRequest success = visitorRequestRepository.save(visitorRequest);
//        if (success == null)
//            throw new BusinessException("Request Cannot be Approved");
//        else
//            return true;
//    }
    
    public boolean approveOrRejectOdcRequest(List<VisitorRequest> visitorRequests) throws BusinessException {
    	boolean success = false;
    	for (VisitorRequest request : visitorRequests) {
    		visitorRequestRepository.deleteByVisitorRequestId(request.getVisitorRequestId());
            if (request.getStatus().equals("Accepted By Manager"))
            	request.setStatus("Accepted By Manager");
            else if (request.getStatus().equals("Pending Approval"))
            	request.setStatus("Accepted By Manager");
            else if (request.getStatus().equals("Approved"))
            	request.setStatus("Approved");
            else if (request.getStatus().equals("Rejected By Manager"))
            	request.setStatus("Rejected By Manager");
            else if (request.getStatus().equals("Rejected"))
            	request.setStatus("Rejected");
            VisitorRequest vr = visitorRequestRepository.save(request);
            if (vr == null)
            	{success = false;
                throw new BusinessException("Request Cannot be Approved");}
            else
                success =true;
		}
        return success;
    }

    public List<UserInfo> getManagerList() {
        List<UserInfo> userInfoList = userRepository.findAll();
        userInfoList.removeIf(user -> user.getRole().equals("Employee"));
        userInfoList.removeIf(user -> user.isAccountActive() == true);
        return userInfoList;
    }

    public ResponseDto registrationRequest(List<RegistrationRequest> registrationRequest) {
        int count = 0;
        for (int i = 0; i < registrationRequest.size(); i++) {
            Optional<UserInfo> userInfo = userRepository.findByEmpId(registrationRequest.get(i).empId);
            if (userInfo.isPresent() && registrationRequest.get(i).status == true) {
                userRepository.deleteByEmpId(userInfo.get().getEmpId());
                userInfo.get().setAccountActive(true);
                userRepository.save(userInfo.get());
                count++;
            } else {
                userRepository.deleteByEmpId(userInfo.get().getEmpId());
                userInfo.get().setFlag(false);
                userRepository.save(userInfo.get());
                count--;
            }
        }
        if (count == registrationRequest.size())
            return new ResponseDto("All request accepted", 200);
        return new ResponseDto("Some request rejected", 200);
    }

    public List<UserInfo> getRegistrationRequestOfEmployee(String empId) {
        Optional<UserInfo> manager = userRepository.findByEmpId(empId);
        if(manager.isPresent()){
            String name = manager.get().getFirstName() + " " + manager.get().getLastName();
            List<UserInfo> userInfoList = userRepository.findAllByManagerName(name);
            userInfoList.removeIf(value -> value.isAccountActive() == true);
            userInfoList.removeIf(value -> value.isFlag() == false);
            return userInfoList;
        }
        return null;
    }

    public List<UserInfo> managerList() {
        List<UserInfo> manager = userRepository.findAllByRole("Manager");
        manager.removeIf(user -> user.getRole().contains("Admin"));
        manager.removeIf(userInfo -> userInfo.isAccountActive() == false);
        manager.removeIf(value -> value.isFlag() == false);
        return manager;
    }

    public boolean deleteOdc(String odcName) {
        Optional<ODCList> odc = odcRepository.findByOdcName(odcName);
        odcRepository.deleteByOdcName(odcName);
        odc.get().setFlag(false);
        odcRepository.save(odc.get());
        List<VisitorRequest> allByOdc = visitorRequestRepository.findAllByOdc(odcName);
        allByOdc.forEach(value -> {
            value.setOdcExist(false);
            visitorRequestRepository.deleteByVisitorRequestId(value.getVisitorRequestId());
            visitorRequestRepository.save(value);
        });
        List<UserInfo> userInfos = userRepository.findAllByOdc(odcName);
        userInfos.forEach(userInfo ->{
            userInfo.getOdc().remove(odcName);
            userRepository.deleteByEmpId(userInfo.getEmpId());
            userRepository.save(userInfo);
        } );
        return true;
    }

    public List<VisitorRequest> getPendingVisitorRequest(String empId) {
        Optional<UserInfo> user = userRepository.findByEmpId(empId);
        List<VisitorRequest> visitorRequestList = visitorRequestRepository.findByManagerEmpID(empId);
        if (user.isPresent()) {
            visitorRequestList.removeIf(value -> value.isOdcExist() == false);
            return visitorRequestList;
        }
        return null;
    }

    public List<UserInfo> getEmployeesList(String manager) {
        List<UserInfo> userInfoList = userRepository.findAllByManagerName(manager);
        return userInfoList;
    }


//    public List<UserInfo> getEmployeesList(String manager) {
//        List<UserInfo> userInfoList = userRepository.findAllByManagerName(manager);
//        return userInfoList;
//    }

}
