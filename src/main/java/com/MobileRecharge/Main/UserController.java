package com.MobileRecharge.Main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return "User registered successfully!";
        } catch (Exception e) {
            return "Registration failed: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password) {
        User user = userService.authenticate(id, password);
        if (user != null) {
            return "Login successful for: " + user.getName();
        } else {
            return "Invalid credentials";
        }
    }
}

