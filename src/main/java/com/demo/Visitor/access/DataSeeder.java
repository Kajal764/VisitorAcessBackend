package com.demo.Visitor.access;


import com.demo.Visitor.access.dto.RegisterUserDto;
import com.demo.Visitor.access.model.UserInfo;
import com.demo.Visitor.access.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataSeeder {

    @Autowired
    BCryptPasswordEncoder bcryptPasswordEncoder;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmpId(666666).isEmpty()) {
                RegisterUserDto adminDTO = new RegisterUserDto("admin", "admin", "admin@gmail.com", "Admin@123", 6666666, 9090909090L, "Admin", "Admin", "");
                adminDTO.password = bcryptPasswordEncoder.encode(adminDTO.password);
                UserInfo userInfo = new UserInfo(adminDTO);
                userInfo.setAccountActive(true);
                userRepository.save(userInfo);
            }
            if (userRepository.findByEmpId(7777777).isEmpty()) {
                RegisterUserDto adminDTO = new RegisterUserDto("Manager", "Manager", "Manager@gmail.com", "Manager@123", 7777777, 9090909090L, "Manager", "Admin", "");
                adminDTO.password = bcryptPasswordEncoder.encode(adminDTO.password);
                UserInfo userInfo = new UserInfo(adminDTO);
                userInfo.setAccountActive(true);
                userRepository.save(userInfo);
            }

        };
    }
}
