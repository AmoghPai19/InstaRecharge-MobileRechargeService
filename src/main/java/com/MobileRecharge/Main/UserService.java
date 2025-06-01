package com.MobileRecharge.Main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public User authenticate(String userId, String password) {
        return userRepository.findByUserIdAndPassword(userId, password);
    }
}

