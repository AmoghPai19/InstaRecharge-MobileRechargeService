package com.MobileRecharge.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(MainApplication.class, args);

		Scanner sc = new Scanner(System.in);

		// ✅ Get beans from Spring context
		AuthenticationService authService = context.getBean(AuthenticationService.class);
		RechargeService rechargeService = context.getBean(RechargeService.class);

		// Simulate some plans and providers
		RechargePlan plan1 = new RechargePlan("P001", "1GB/day for 28 days", 149.0, 28);
		RechargePlan plan2 = new RechargePlan("P002", "2GB/day for 56 days", 299.0, 56);

		ServiceProvider provider = new ServiceProvider("Airtel");
		provider.addPlan(plan1);
		provider.addPlan(plan2);

		System.out.println("=== Welcome to Mobile Recharge System ===");
		System.out.println("1. Login to existing account");
		System.out.println("2. Create new account");
		System.out.print("Enter your choice: ");
		int n = sc.nextInt();
		sc.nextLine(); // clear newline

		User currentUser = null;

		if (n == 2) {
			System.out.print("Enter new User ID: ");
			String newId = sc.nextLine();
			System.out.print("Enter your name: ");
			String newName = sc.nextLine();
			System.out.print("Create password: ");
			String newPass = sc.nextLine();
			System.out.print("Enter initial wallet balance: ");
			double initialBalance = sc.nextDouble();
			sc.nextLine(); // consume newline

			currentUser = new User(newId, newName, initialBalance, newPass);
			authService.register(currentUser);
			System.out.println("Account created successfully! You are now logged in.");
		} else if (n == 1) {
			System.out.print("Enter User ID: ");
			String inputId = sc.nextLine();
			System.out.print("Enter Password: ");
			String inputPass = sc.nextLine();

			try {
				currentUser = authService.login(inputId, inputPass);
				System.out.println("Login successful. Welcome, " + currentUser.getName() + "!");
			} catch (RechargeException e) {
				System.out.println("Login failed: " + e.getMessage());
				return;
			}
		} else {
			System.out.println("Invalid choice.");
			return;
		}

		int choice;
		do {
			System.out.println("\n===== Main Menu =====");
			System.out.println("1. View Wallet Balance");
			System.out.println("2. Add Balance");
			System.out.println("3. View Available Plans");
			System.out.println("4. Recharge");
			System.out.println("5. View Transactions");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine(); // consume newline

			switch (choice) {
				case 1:
					System.out.println("Wallet Balance: ₹" + currentUser.getWalletBalance());
					break;

				case 2:
					System.out.print("Enter amount to add: ");
					double amount = sc.nextDouble();
					sc.nextLine();
					currentUser.addBalance(amount);
					System.out.println("Amount added successfully.");
					break;

				case 3:
					System.out.println("Available Plans from " + provider.getProviderName() + ":");
					List<RechargePlan> plans = provider.getPlans();
					for (int i = 0; i < plans.size(); i++) {
						RechargePlan p = plans.get(i);
						System.out.printf("%d. ₹%.2f - %s (%d days)\n", i + 1, p.getAmount(), p.getDescription(), p.getValidity());
					}
					break;

				case 4:
					List<RechargePlan> availablePlans = provider.getPlans();
					System.out.println("Select a plan number to recharge:");
					for (int i = 0; i < availablePlans.size(); i++) {
						RechargePlan p = availablePlans.get(i);
						System.out.printf("%d. ₹%.2f - %s (%d days)\n", i + 1, p.getAmount(), p.getDescription(), p.getValidity());
					}

					int planChoice = sc.nextInt();
					sc.nextLine();
					if (planChoice >= 1 && planChoice <= availablePlans.size()) {
						RechargePlan selectedPlan = availablePlans.get(planChoice - 1);
						try {
							Transaction tx = rechargeService.recharge(currentUser, selectedPlan);
							System.out.println("Recharge Successful:");
							tx.display();
						} catch (InsufficientBalanceException e) {
							System.out.println("Recharge Failed: " + e.getMessage());
						}
					} else {
						System.out.println("Invalid selection.");
					}
					break;

				case 5:
					List<Transaction> history = currentUser.getTransactionHistory();
					if (history.isEmpty()) {
						System.out.println("No transactions found.");
					} else {
						for (Transaction tx : history) {
							tx.display();
						}
					}
					break;

				case 0:
					System.out.println("Thank you for using the system!");
					break;

				default:
					System.out.println("Invalid choice.");
			}

		} while (choice != 0);
	}
}
