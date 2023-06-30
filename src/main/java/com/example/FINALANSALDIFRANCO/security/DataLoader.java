package com.example.FINALANSALDIFRANCO.security;

import com.example.FINALANSALDIFRANCO.entity.AppUser;
import com.example.FINALANSALDIFRANCO.entity.AppUserRole;
import com.example.FINALANSALDIFRANCO.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception{

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String userPassword = "1234";
        String encodedPassword = passwordEncoder.encode(userPassword);

        BCryptPasswordEncoder passwordEncoderAdmin = new BCryptPasswordEncoder();
        String adminPass = "123456";
        String encodedPasswordAdmin = passwordEncoderAdmin.encode(adminPass);

        AppUser user = new AppUser("testUser", "tUsername", "testUser@test.com", encodedPassword, AppUserRole.USER);
        AppUser adminUser = new AppUser("adminUser", "aUsername", "adminUser@admin.com", encodedPasswordAdmin, AppUserRole.ADMIN);

        userRepository.save(user);
        userRepository.save(adminUser);
    }
}
