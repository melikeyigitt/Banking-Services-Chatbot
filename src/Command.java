public class Command {
    private CommandType type;
    private Double amount; // used for deposit and withdraw commands

    public Command(CommandType type) {
        this.type = type;
        this.amount = null;
    }

    public Command(CommandType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    // Getters and other necessary methods
}
