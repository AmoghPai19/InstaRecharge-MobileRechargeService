package com.MobileRecharge.Main;

public interface Rechargeable {
    Transaction recharge(User user, RechargePlan plan) throws InsufficientBalanceException;
}
