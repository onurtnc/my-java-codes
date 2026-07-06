import java.util.Scanner;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ATMSimulator {
    // Account information
    private static String accountHolder = "Onur Tunc";
    private static String cardNumber = "1234 5678 9012 3456";
    private static String pin = "1234";
    private static double balance = 5000.0;
    private static ArrayList<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome screen
        System.out.println("+======================================+");
        System.out.println("|      WELCOME TO THE ATM SIMULATOR     |");
        System.out.println("+======================================+");

        // PIN check
        if (!checkPin(scanner)) {
            System.out.println("\n[X] 3 incorrect attempts! Your card has been blocked.");
            scanner.close();
            return;
        }

        // Main menu
        boolean keepGoing = true;
        while (keepGoing) {
            keepGoing = mainMenu(scanner);
        }

        System.out.println("\n[!] Don't forget to take your card!");
        System.out.println("[+] Have a great day!");
        scanner.close();
    }

    // PIN check function
    private static boolean checkPin(Scanner scanner) {
        int attemptsLeft = 3;

        while (attemptsLeft > 0) {
            System.out.print("\nEnter your PIN: ");
            String enteredPin = scanner.nextLine();

            if (enteredPin.equals(pin)) {
                System.out.println("[OK] Login successful!");
                return true;
            } else {
                attemptsLeft--;
                if (attemptsLeft > 0) {
                    System.out.println("[X] Incorrect PIN! Attempts remaining: " + attemptsLeft);
                }
            }
        }
        return false;
    }

    // Main menu function
    private static boolean mainMenu(Scanner scanner) {
        System.out.println("\n+======================================+");
        System.out.println("|               MAIN MENU               |");
        System.out.println("+======================================+");
        System.out.println("|  1. Check Balance                     |");
        System.out.println("|  2. Deposit Money                     |");
        System.out.println("|  3. Withdraw Money                    |");
        System.out.println("|  4. Transaction History               |");
        System.out.println("|  5. Account Information               |");
        System.out.println("|  6. Change PIN                        |");
        System.out.println("|  7. Exit                              |");
        System.out.println("+======================================+");
        System.out.print("\nYour choice (1-7): ");

        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("[X] Invalid input! Please enter a number.");
            return true;
        }

        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                depositMoney(scanner);
                break;
            case 3:
                withdrawMoney(scanner);
                break;
            case 4:
                showTransactionHistory();
                break;
            case 5:
                accountInformation();
                break;
            case 6:
                changePin(scanner);
                break;
            case 7:
                return false;
            default:
                System.out.println("[X] Invalid choice! Please enter a number between 1 and 7.");
        }

        return true;
    }

    // Check balance
    private static void checkBalance() {
        System.out.println("\n+======================================+");
        System.out.println("|             CHECK BALANCE             |");
        System.out.println("+======================================+");
        System.out.printf("Your current balance: $%.2f\n", balance);
        recordTransaction("Balance Check: " + String.format("$%.2f", balance));
    }

    // Deposit money
    private static void depositMoney(Scanner scanner) {
        System.out.println("\n+======================================+");
        System.out.println("|              DEPOSIT MONEY            |");
        System.out.println("+======================================+");
        System.out.print("Enter the amount you want to deposit: ");

        try {
            double amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("[X] Invalid amount! Enter a positive value.");
                return;
            }

            if (amount > 10000) {
                System.out.println("[X] You can deposit a maximum of $10,000 at a time!");
                return;
            }

            balance += amount;
            System.out.printf("[OK] $%.2f has been deposited to your account.\n", amount);
            System.out.printf("Your new balance: $%.2f\n", balance);
            recordTransaction("Deposit: +" + String.format("$%.2f", amount));

        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("[X] Invalid amount entered!");
        }
    }

    // Withdraw money
    private static void withdrawMoney(Scanner scanner) {
        System.out.println("\n+======================================+");
        System.out.println("|             WITHDRAW MONEY            |");
        System.out.println("+======================================+");
        System.out.printf("Your current balance: $%.2f\n\n", balance);
        System.out.println("Quick Select:");
        System.out.println("1. $100    2. $200    3. $500");
        System.out.println("4. $1000   5. Other amount");
        System.out.print("\nYour choice: ");

        double amount = 0;

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: amount = 100; break;
                case 2: amount = 200; break;
                case 3: amount = 500; break;
                case 4: amount = 1000; break;
                case 5:
                    System.out.print("Enter the amount you want to withdraw: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("[X] Invalid choice!");
                    return;
            }

            if (amount <= 0) {
                System.out.println("[X] Invalid amount!");
                return;
            }

            if (amount > balance) {
                System.out.println("[X] Insufficient balance!");
                System.out.printf("Your available balance: $%.2f\n", balance);
                return;
            }

            if (amount > 5000) {
                System.out.println("[X] You can withdraw a maximum of $5,000 at a time!");
                return;
            }

            if (amount % 10 != 0) {
                System.out.println("[X] You can only withdraw multiples of $10!");
                return;
            }

            balance -= amount;
            System.out.printf("[OK] $%.2f has been withdrawn from your account.\n", amount);
            System.out.printf("Your remaining balance: $%.2f\n", balance);
            System.out.println("Please don't forget to take your cash!");
            recordTransaction("Withdrawal: -" + String.format("$%.2f", amount));

        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("[X] Invalid input!");
        }
    }

    // Transaction history
    private static void showTransactionHistory() {
        System.out.println("\n+======================================+");
        System.out.println("|           TRANSACTION HISTORY         |");
        System.out.println("+======================================+");

        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction history yet.");
        } else {
            for (int i = transactionHistory.size() - 1; i >= 0; i--) {
                System.out.println((transactionHistory.size() - i) + ". " + transactionHistory.get(i));
            }
        }
    }

    // Account information
    private static void accountInformation() {
        System.out.println("\n+======================================+");
        System.out.println("|           ACCOUNT INFORMATION         |");
        System.out.println("+======================================+");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Card Number: " + cardNumber);
        System.out.printf("Current Balance: $%.2f\n", balance);
        System.out.println("Account Type: Checking Account");
    }

    // Change PIN
    private static void changePin(Scanner scanner) {
        System.out.println("\n+======================================+");
        System.out.println("|              CHANGE PIN                |");
        System.out.println("+======================================+");
        System.out.print("Enter your current PIN: ");
        String currentPin = scanner.nextLine();

        if (!currentPin.equals(pin)) {
            System.out.println("[X] Incorrect PIN!");
            return;
        }

        System.out.print("Enter your new PIN (4 digits): ");
        String newPin = scanner.nextLine();

        if (newPin.length() != 4 || !newPin.matches("\\d+")) {
            System.out.println("[X] PIN must consist of 4 digits!");
            return;
        }

        System.out.print("Re-enter your new PIN: ");
        String newPinConfirm = scanner.nextLine();

        if (!newPin.equals(newPinConfirm)) {
            System.out.println("[X] PINs do not match!");
            return;
        }

        pin = newPin;
        System.out.println("[OK] Your PIN has been changed successfully!");
        recordTransaction("PIN Changed");
    }

    // Record a transaction
    private static void recordTransaction(String transaction) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = sdf.format(new Date());
        transactionHistory.add(date + " - " + transaction);
    }
}
