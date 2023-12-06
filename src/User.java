public class User {
    private String username;
    private String password; // Note: In real applications, store hashed passwords
    private Account account;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.account = new Account(); // Initialize with a new account
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; // In real applications, hash the password
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
