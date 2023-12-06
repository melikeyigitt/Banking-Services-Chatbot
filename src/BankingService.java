import java.util.HashMap;
import java.util.Map;

public class BankingService {
    private Map<String, User> users;

    public BankingService() {
        this.users = new HashMap<>();
        initializeDummyUsers();
    }

    private void initializeDummyUsers() {
        // Initialize with some dummy users for demonstration purposes
        users.put("john_doe", new User("john_doe", "password123"));
        users.put("jane_doe", new User("jane_doe", "password456"));
    }

    public User authenticate(String username, String password) {
        // Debugging: Print the username and password being checked
        System.out.println("Authenticating: " + username + " with password: " + password);

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Authentication successful for: " + username);
            return user;
        } else {
            System.out.println("Authentication failed for: " + username);
            return null;
        }
    }

    public void deposit(User user, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        user.getAccount().deposit(amount);
        System.out.println("Deposited $" + amount + " to " + user.getUsername() + "'s account.");
    }

    public void withdraw(User user, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }
        if (user.getAccount().getBalance() < amount) {
            System.out.println("Insufficient funds for withdrawal.");
            return;
        }
        user.getAccount().withdraw(amount);
        System.out.println("Withdrew $" + amount + " from " + user.getUsername() + "'s account.");
    }

    public double checkBalance(User user) {
        return user.getAccount().getBalance();
    }

    public void printTransactionHistory(User user) {
        user.getAccount().printTransactionHistory();
    }

    // Additional methods for handling other banking operations can be added here
}
