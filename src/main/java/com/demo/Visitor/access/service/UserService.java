package com.demo.Visitor.access.service;

import com.demo.Visitor.access.dto.RegisterUserDto;
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


    public UserInfo findByUsernameAndPassword(int empId, String password) throws BusinessException {
        UserInfo usr = userRepository.findByEmpIdAndPassword(empId, password);
        if (usr == null)
            throw new BusinessException("Invalid credentials..");
        return usr;
        //if (usr.get().getEmpId()==(empId))
        // if (bcryptPasswordEncoder.matches(password, usr.get().getPassword()))
        // if(usr.get().getAccountActive()==true)
    }

    public List<UserInfo> getAllUserData() {
        List<UserInfo> userList = userRepository.findAll();
        userList.removeIf(value -> value.getRole().equals("Admin"));
        return userList;
    }
}
