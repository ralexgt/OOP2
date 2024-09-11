package Commands;

public enum CommandType {
    // User service commands
    LOGIN_USER(0),
    LOGOUT_USER(1),
    LIST_USERS(2),

    CREATE_CLIENT(10),
    CREATE_EMPLOYEE(11),

    CHANGE_SELF_PASSWORD(20),
    CHANGE_USER_PASSWORD(21),
    CHANGE_EMPLOYEE_ACCESS_LEVEL(22),

    DELETE_USER(30),

    // Accounts service commands
    CREATE_CURRENT_ACCOUNT(50),
    CREATE_DEPOSIT_ACCOUNT(51),

    LIST_ACCOUNTS(60),
    LIST_SELF_ACCOUNTS(61),
    LIST_USER_ACCOUNTS(62),

    CLOSE_CURRENT_ACCOUNT(70),
    CLOSE_DEPOSIT_ACCOUNT(71),

    TRANSFER_TO_ACCOUNT(81),
    DEPOSIT_TO_ACCOUNT(82),
    WITHDRAW_ACCOUNT(83),
    LIST_SELF_ACCOUNT_TRANSFERS(84),
    LIST_USER_ACCOUNT_TRANSFERS(85),

    CREATE_CARD(90),
    DISABLE_CARD(91),
    DELETE_CARD(92),
    LIST_SELF_ACCOUNT_CARDS(93),
    LIST_USER_ACCOUNT_CARDS(94),
    LIST_SELF_CARDS(95),
    LIST_USER_CARDS(96),

    // Application commands
    EXIT(200);

    private final int id;

    CommandType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String printListItem() {
        return "(" + this.getId() + ") " + this;
    }

    public static CommandType fromId(int id) {
        CommandType[] values = CommandType.values();
        for (CommandType value : values) {
            if (value.getId() == id) {
                return value;
            }
        }

        return null;
    }
}