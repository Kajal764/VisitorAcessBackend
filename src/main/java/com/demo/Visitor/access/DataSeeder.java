package com.demo.Visitor.access;


import com.demo.Visitor.access.dto.RegisterUserDto;
import com.demo.Visitor.access.model.UserInfo;
import com.demo.Visitor.access.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@Configuration
public class DataSeeder {

    @Autowired
    BCryptPasswordEncoder bcryptPasswordEncoder;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmpId("Admin").isEmpty()) {
                RegisterUserDto adminDTO = new RegisterUserDto("Admin", "", "admin@gmail.com", "Admin@123", "Admin", 9090909090L,
                        Arrays.asList("Admin", "Manager", "Employee", "Odc-Manager"), "Admin", "");
                adminDTO.password = bcryptPasswordEncoder.encode(adminDTO.password);
                UserInfo userInfo = new UserInfo(adminDTO);
                userInfo.setFlag(true);
                userInfo.setAccountActive(true);
                userRepository.save(userInfo);
            }
            if (userRepository.findByEmpId("7777777").isEmpty()) {
                RegisterUserDto adminDTO = new RegisterUserDto("Chandrashekhar", "C", "Manager@gmail.com", "Manager@123", "7777777", 9090909090L, Arrays.asList("Manager", "Employee"), "Admin", "");
                adminDTO.password = bcryptPasswordEncoder.encode(adminDTO.password);
                UserInfo userInfo = new UserInfo(adminDTO);
                userInfo.setFlag(true);
                userInfo.setAccountActive(true);
                userRepository.save(userInfo);
            }
        };
    }
}
