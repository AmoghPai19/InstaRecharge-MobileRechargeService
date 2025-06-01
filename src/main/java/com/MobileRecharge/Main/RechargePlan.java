package com.MobileRecharge.Main;

public class RechargePlan {
    private String planId;
    private String description;
    private double amount;
    private int validity;

    public RechargePlan(String planId, String description, double amount, int validity) {
        this.planId = planId;
        this.description = description;
        this.amount = amount;
        this.validity = validity;
    }

    public String getPlanId() { return planId; }
    public double getAmount() { return amount; }
    public int getValidity() { return validity; }
    public String getDescription() { return description; }
}
