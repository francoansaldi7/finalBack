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
    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = "password";
        String encodedPassword = passwordEncoder.encode("password");

        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String pass1 = "password1";
        String encodedPassword2 = passwordEncoder2.encode("password2");

        userRepository.save(new AppUser("Joaquin", "joaquin123", "joaquin@gmail.com", encodedPassword, AppUserRole.ADMIN));
        userRepository.save(new AppUser("Federica", "federica123", "federica@gmail.com", encodedPassword2, AppUserRole.USER));
    }
}
