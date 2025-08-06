import java.util.Scanner;

// Step 4: BankAccount class
class Task_3 {
    private double balance;

    public Task_3(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
        } else {
            balance += amount;
            System.out.println("Successfully deposited ₹" + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance! Withdrawal failed.");
        } else {
            balance -= amount;
            System.out.println("Successfully withdrew ₹" + amount);
        }
    }
}

// Step 1: ATM class
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    // Step 3: ATM operations
    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    public void checkBalance() {
        System.out.printf("Your current balance is: ₹%.2f%n", account.getBalance());
    }

    // Step 2 + 7: UI and interaction
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("===== Welcome to Pankaj's ATM =====");

        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using Pankaj's ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1 to 4.");
            }

        } while (choice != 4);
    }
}

// Step 6 + Final glue: Main method to launch ATM
public class ATMInterface {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(1000.00); // Initial balance
        ATM atm = new ATM(myAccount);
        atm.start();
    }
}
