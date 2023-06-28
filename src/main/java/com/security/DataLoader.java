package com.security;

import com.entity.AppUser;
import com.entity.AppUserRole;
import com.repository.UserRepository;
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
        String pass = "password";
        String encodedPassword = passwordEncoder.encode(pass);

        BCryptPasswordEncoder passwordEncoderAdmin = new BCryptPasswordEncoder();
        String pass1 = "password1";
        String encodedPasswordAdmin = passwordEncoderAdmin.encode(pass1);

        AppUser user = new AppUser("testUser", "testUser", "testUser@gmail.com", encodedPassword, AppUserRole.USER);
        AppUser userAdmin = new AppUser("adminUser", "adminUser", "adminUser@gmail.com", encodedPasswordAdmin, AppUserRole.ADMIN);

       userRepository.save(user);
       userRepository.save(userAdmin);
    }
}
