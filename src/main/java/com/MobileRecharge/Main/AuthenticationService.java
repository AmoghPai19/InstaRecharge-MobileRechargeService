package com.MobileRecharge.Main;

import com.MobileRecharge.Main.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public void register(User user) {
        userRepository.save(user);
    }

    public User login(String id, String password) {
        User user = userRepository.findByUserIdAndPassword(id, password);
        if (user == null) {
            throw new RechargeException("Invalid credentials");
        }
        return user;
    }
}
