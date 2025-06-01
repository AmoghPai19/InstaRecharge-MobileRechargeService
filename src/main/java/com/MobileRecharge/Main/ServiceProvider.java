package com.MobileRecharge.Main;

import java.util.ArrayList;
import java.util.List;

public class ServiceProvider {
    private String providerName;
    private List<RechargePlan> plans = new ArrayList<>();

    public ServiceProvider(String providerName) {
        this.providerName = providerName;
    }

    public void addPlan(RechargePlan plan) {
        plans.add(plan);
    }

    public List<RechargePlan> getPlans() {
        return plans;
    }

    public String getProviderName() {
        return providerName;
    }
}
