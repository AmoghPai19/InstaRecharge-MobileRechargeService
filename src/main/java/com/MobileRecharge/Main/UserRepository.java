package com.MobileRecharge.Main;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserIdAndPassword(String userId, String password);
}



