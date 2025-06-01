package com.MobileRecharge.Main;

// RechargeService.java

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechargeService {

    @Autowired
    private UserRepository userRepository;
    @Transactional
    public Transaction recharge(User user, RechargePlan plan) throws InsufficientBalanceException {
        if (!user.deductBalance(plan.getAmount())) {
            throw new InsufficientBalanceException("Not enough balance");
        }
        Transaction tx = new Transaction(plan.getDescription(), plan.getAmount());
        user.addTransaction(tx);
        userRepository.save(user);
        return tx;
    }
}

