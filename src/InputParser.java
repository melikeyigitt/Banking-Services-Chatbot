public class InputParser {

    public Command parse(String input) {
        String[] parts = input.trim().split("\\s+");
        String commandName = parts[0].toLowerCase();

        switch (commandName) {
            case "deposit":
                return createDepositCommand(parts);
            case "withdraw":
                return createWithdrawCommand(parts);
            case "balance":
                return new Command(CommandType.BALANCE);
            case "history":
                return new Command(CommandType.HISTORY);
            case "logout":
                return new Command(CommandType.LOGOUT);
            default:
                return new Command(CommandType.UNKNOWN);
        }
    }

    private Command createDepositCommand(String[] parts) {
        if (parts.length < 2) return new Command(CommandType.INVALID);

        try {
            double amount = Double.parseDouble(parts[1]);
            return new Command(CommandType.DEPOSIT, amount);
        } catch (NumberFormatException e) {
            return new Command(CommandType.INVALID);
        }
    }

    private Command createWithdrawCommand(String[] parts) {
        if (parts.length < 2) return new Command(CommandType.INVALID);

        try {
            double amount = Double.parseDouble(parts[1]);
            return new Command(CommandType.WITHDRAW, amount);
        } catch (NumberFormatException e) {
            return new Command(CommandType.INVALID);
        }
    }

    // Add additional parsing methods as needed
}
