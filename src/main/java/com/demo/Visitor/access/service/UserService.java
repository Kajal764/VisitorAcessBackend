package com.demo.Visitor.access.service;

import com.demo.Visitor.access.dto.RegisterUserDto;
import com.demo.Visitor.access.dto.RegistrationRequest;
import com.demo.Visitor.access.dto.ResponseDto;
import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.model.UserInfo;
import com.demo.Visitor.access.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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

    public List<UserInfo> getManagerList() {
        List<UserInfo> userInfoList = userRepository.findAll();
        userInfoList.removeIf(user -> user.getRole().equals("Employee"));
        userInfoList.removeIf(user -> user.getAccountActive() == true);
        return userInfoList;
    }

    public ResponseDto registrationRequest(RegistrationRequest registrationRequest) {
        Optional<UserInfo> userInfo = userRepository.findByEmpId(registrationRequest.empId);
        if (registrationRequest.status == true) {
            userInfo.get().setAccountActive(true);
            userRepository.deleteByEmpId(userInfo.get().getEmpId());
            userRepository.save(userInfo.get());
            return new ResponseDto("Request accept", 200);
        }
        userRepository.deleteByEmpId(registrationRequest.empId);
        return new ResponseDto("Request reject", 200);
    }

    public List<UserInfo> getRegistrationRequestOfEmployee(int empId) {
        Optional<UserInfo> manager = userRepository.findByEmpId(empId);
        List<UserInfo> userInfoList = userRepository.findAllByManagerName(manager.get().getFirstName());
        return userInfoList;
    }


}
