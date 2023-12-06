import java.util.Scanner;

public class Chatbot {
    private BankingService bankingService;
    private SessionManager sessionManager;
    private User currentUser;

    public Chatbot() {
        this.bankingService = new BankingService();
        this.sessionManager = new SessionManager();
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Banking Chatbot!");

        while (true) {
            if (currentUser == null) {
                System.out.println("Please login to continue. Format: [username] [password]");
                String input = scanner.nextLine().trim();

                // Check if the input starts with "login"
                String[] parts = input.split("\\s+");
                // Ensure that there are exactly 3 parts: "login", username, password
                if (parts.length == 2){
                    String username = parts[0];
                    String password = parts[1];
                    User user = bankingService.authenticate(username, password);

                    if (user != null) {
                        currentUser = user;
                        System.out.println("Logged in successfully as " + currentUser.getUsername());
                        // Proceed with the next part of the application
                    } else {
                        System.out.println("Authentication failed. Please try again.");
                    }
                } else if (parts.length >2) {
                    System.out.println("Invalid format. Please use: login [username] [password]");
                }
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting chatbot. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid command. Please use: login [username] [password]");
                }
            } else {
                System.out.println("Enter a command (balance, deposit [amount], withdraw [amount], history, logout):");
                String command = scanner.nextLine().trim();

                if (command.equalsIgnoreCase("balance")) {
                    double balance = bankingService.checkBalance(currentUser);
                    System.out.println("Your current balance: $" + balance);
                } else if (command.startsWith("deposit")) {
                    String[] parts = command.split("\\s+");
                    if (parts.length == 2) {
                        try {
                            double amount = Double.parseDouble(parts[1]);
                            bankingService.deposit(currentUser, amount);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid amount. Please enter a valid number.");
                        }
                    } else {
                        System.out.println("Invalid command format. Use: deposit [amount]");
                    }
                } else if (command.startsWith("withdraw")) {
                    String[] parts = command.split("\\s+");
                    if (parts.length == 2) {
                        try {
                            double amount = Double.parseDouble(parts[1]);
                            bankingService.withdraw(currentUser, amount);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid amount. Please enter a valid number.");
                        }
                    } else {
                        System.out.println("Invalid command format. Use: withdraw [amount]");
                    }
                } else if (command.equalsIgnoreCase("history")) {
                    bankingService.printTransactionHistory(currentUser);
                } else if (command.equalsIgnoreCase("logout")) {
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                } else {
                    System.out.println("Invalid command. Available commands are: balance, deposit, withdraw, history, logout.");
                }
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Chatbot().start();
    }
}
