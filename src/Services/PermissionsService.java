package Services;

import Commands.CommandType;
import Models.Client;
import Models.Employee;
import Models.User;

public class PermissionsService {
    public static boolean hasPermission(CommandType commandType) {
        User u = UserService.getInstance().getAuthenticatedUser();
        boolean loggedIn = u != null;
        return switch (commandType) {
            case LOGIN_USER -> !loggedIn;
            case LOGOUT_USER, CHANGE_SELF_PASSWORD -> loggedIn;
            case LIST_USERS, LIST_ACCOUNTS, LIST_USER_ACCOUNTS, LIST_USER_ACCOUNT_TRANSFERS, LIST_USER_ACCOUNT_CARDS,
                 LIST_USER_CARDS -> employeeAccessLevel(u) >= 1;
            case CREATE_CLIENT, CHANGE_USER_PASSWORD, DELETE_USER -> employeeAccessLevel(u) >= 3;
            case CREATE_EMPLOYEE -> employeeAccessLevel(u) >= 5;
            case CHANGE_EMPLOYEE_ACCESS_LEVEL -> employeeAccessLevel(u) >= 4;
            case CREATE_CURRENT_ACCOUNT, CREATE_DEPOSIT_ACCOUNT, CLOSE_CURRENT_ACCOUNT, CLOSE_DEPOSIT_ACCOUNT,
                 CREATE_CARD, DISABLE_CARD, DELETE_CARD -> employeeAccessLevel(u) >= 2;
            case LIST_SELF_ACCOUNTS, TRANSFER_TO_ACCOUNT, DEPOSIT_TO_ACCOUNT, WITHDRAW_ACCOUNT,
                 LIST_SELF_ACCOUNT_TRANSFERS, LIST_SELF_ACCOUNT_CARDS, LIST_SELF_CARDS -> loggedIn && u instanceof Client;
            case EXIT -> true;
        };
    }

    static int employeeAccessLevel(User u) {
        if (!(u instanceof Employee e)) {
            return -1;
        }

        return e.getAccessLevel();
    }
}