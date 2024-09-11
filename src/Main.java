import Commands.*;
import Commands.AccountCommands.*;
import Commands.UserCommands.*;
import Exceptions.Commands.CommandException;
import Models.User;
import Services.AccountsService;
import Services.LoggingService;
import Services.ServiceInterface;
import Services.UserService;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    protected static List<ServiceInterface> getServices() {
        List<ServiceInterface> services = new LinkedList<>();
        services.add(LoggingService.getInstance());
        services.add(UserService.getInstance());
        services.add(AccountsService.getInstance());
        return services;
    }

    protected static boolean initialize() {
        for (ServiceInterface service : getServices()) {
            try {
                service.initialize();
            } catch (Exception e) {
                System.out.println("Failed to initialize service " + service.getName() + ": " + e.getMessage());
                return false;
            }
        }

        return true;
    }

    protected static void deinitialize() {
        for (ServiceInterface service : getServices()) {
            service.deinitialize();
        }
    }

    protected static void printCommands() {
        for (ServiceInterface service : getServices()) {
            if (service.getAvailableCommands().isEmpty()) {
                continue;
            }

            System.out.println(service.getName());
            for (CommandType commandType : service.getAvailableCommands()) {
                System.out.println("- " + commandType.printListItem());
            }
            System.out.println();
        }

        System.out.println("Application");
        System.out.println("- " + CommandType.EXIT.printListItem());
        System.out.print("Select a command: ");
    }

    protected static void printCurrentUser() {
        User currentUser = UserService.getInstance().getAuthenticatedUser();
        if (currentUser == null) {
            System.out.println("> Not logged in");
        } else {
            System.out.println("> Logged in as " + currentUser.getUsername() + ", with type " + currentUser.getType());
        }
    }

    protected static void runtime() {
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        while (!done) {
            printCurrentUser();
            printCommands();
            CommandType selectedCommand = CommandType.fromId(scanner.nextInt());
            scanner.nextLine();

            boolean commandAvailable = false;
            for (ServiceInterface service : getServices()) {
                if (service.getAvailableCommands().contains(selectedCommand)) {
                    commandAvailable = true;
                    break;
                }
            }

            if (selectedCommand == CommandType.EXIT) {
                done = true;
            } else if (selectedCommand != null && commandAvailable) {
                executeCommand(selectedCommand, scanner);
            } else {
                System.out.println("> Unknown command");
            }

            System.out.println("-------------------------------------");
            System.out.println();
        }
    }

    protected static void executeCommand(CommandType commandId, Scanner s) {
        System.out.println("> " + commandId);
        try {
            switch (commandId) {
                case LOGIN_USER -> {
                    System.out.println("Please enter your username: ");
                    String username = s.nextLine().trim();
                    System.out.println("Please enter your password: ");
                    String password = s.nextLine().trim();

                    UserService.getInstance().runCommand(new LoginUserCommand(username, password));
                }

                case LOGOUT_USER -> UserService.getInstance().runCommand(new LogoutUserCommand());

                case LIST_USERS -> UserService.getInstance().runCommand(new ListUsersCommand());

                case CREATE_CLIENT -> {
                    System.out.println("Please enter new client username: ");
                    String username = s.nextLine().trim();
                    System.out.println("Please enter new client password: ");
                    String password = s.nextLine().trim();

                    UserService.getInstance().runCommand(new CreateClientCommand(username, password));
                }

                case CREATE_EMPLOYEE -> {
                    System.out.println("Please enter new client username: ");
                    String username = s.nextLine().trim();
                    System.out.println("Please enter new client password: ");
                    String password = s.nextLine().trim();
                    System.out.println("Please enter new employee access level: ");
                    int accessLevel = s.nextInt();
                    s.nextLine();

                    UserService.getInstance().runCommand(new CreateEmployeeCommand(username, password, accessLevel));
                }

                case CHANGE_SELF_PASSWORD -> {
                    System.out.println("Please enter old password: ");
                    String oldPassword = s.nextLine().trim();
                    System.out.println("Please enter new password: ");
                    String newPassword = s.nextLine().trim();

                    UserService.getInstance().runCommand(new ChangeSelfPasswordCommand(oldPassword, newPassword));
                }

                case CHANGE_USER_PASSWORD -> {
                    System.out.println("Please enter username: ");
                    String username = s.nextLine().trim();
                    System.out.println("Please enter old password: ");
                    String oldPassword = s.nextLine().trim();
                    System.out.println("Please enter new password: ");
                    String newPassword = s.nextLine().trim();

                    UserService.getInstance().runCommand(new ChangeUserPasswordCommand(username, oldPassword, newPassword));
                }

                case CHANGE_EMPLOYEE_ACCESS_LEVEL -> {
                    System.out.println("Please enter employee username: ");
                    String username = s.nextLine().trim();
                    System.out.println("Please enter new access level: ");
                    int accessLevel = s.nextInt();
                    s.nextLine();

                    UserService.getInstance().runCommand(new ChangeEmployeeAccessLevelCommand(username, accessLevel));
                }

                case DELETE_USER -> {
                    System.out.println("Please enter username: ");
                    String username = s.nextLine().trim();
                    UserService.getInstance().runCommand(new DeleteUserCommand(username));
                }

                case CREATE_CURRENT_ACCOUNT -> {
                    System.out.println("Please enter new account number: ");
                    String accountNumber = s.nextLine().trim();
                    System.out.println("Please enter new account owner: ");
                    String owner = s.nextLine().trim();

                    AccountsService.getInstance().runCommand(new CreateCurrentAccountCommand(accountNumber, owner));
                }

                case CREATE_DEPOSIT_ACCOUNT -> {
                    System.out.println("Please enter new deposit number: ");
                    String accountNumber = s.nextLine().trim();
                    System.out.println("Please enter new deposit owner: ");
                    String owner = s.nextLine().trim();
                    System.out.println("Please enter new deposit interest: ");
                    double interest = s.nextDouble();
                    s.nextLine();
                    System.out.println("Please enter source account number: ");
                    String source = s.nextLine().trim();
                    System.out.println("Please enter new deposit amount: ");
                    double amount = s.nextDouble();
                    s.nextLine();

                    AccountsService.getInstance().runCommand(new CreateDepositAccountCommand(accountNumber, owner, interest, source, amount));
                }

                case LIST_ACCOUNTS -> AccountsService.getInstance().runCommand(new ListAccountsCommand());

                case LIST_SELF_ACCOUNTS -> AccountsService.getInstance().runCommand(new ListSelfAccountsCommand());

                case LIST_USER_ACCOUNTS -> {
                    System.out.println("Please enter username: ");
                    String username = s.nextLine().trim();
                    AccountsService.getInstance().runCommand(new ListUserAccountsCommand(username));
                }

                case CLOSE_CURRENT_ACCOUNT -> {
                    System.out.println("Please enter current account number: ");
                    String number = s.nextLine().trim();
                    AccountsService.getInstance().runCommand(new CloseCurrentAccountCommand(number));
                }

                case CLOSE_DEPOSIT_ACCOUNT -> {
                    System.out.println("Please enter deposit account number: ");
                    String number = s.nextLine().trim();
                    System.out.println("Please enter destination account number: ");
                    String destination = s.nextLine().trim();
                    AccountsService.getInstance().runCommand(new CloseDepositAccountCommand(number, destination));
                }

                case TRANSFER_TO_ACCOUNT -> {
                    System.out.println("Please enter source account number: ");
                    String sourceAccountNumber = s.nextLine().trim();
                    System.out.println("Please enter destination account number: ");
                    String destinationAccountNumber = s.nextLine().trim();
                    System.out.println("Please enter amount to transfer: ");
                    double amount = s.nextDouble();
                    s.nextLine();
                    AccountsService.getInstance().runCommand(new AccountTransferCommand(sourceAccountNumber, destinationAccountNumber, amount));
                }

                case DEPOSIT_TO_ACCOUNT -> {
                    System.out.println("Please enter account number: ");
                    String destination = s.nextLine().trim();
                    System.out.println("Please enter amount to deposit: ");
                    double amount = s.nextDouble();
                    s.nextLine();
                    AccountsService.getInstance().runCommand(new AccountDepositCommand(destination, amount));
                }

                case WITHDRAW_ACCOUNT -> {
                    System.out.println("Please enter account number: ");
                    String source = s.nextLine().trim();
                    System.out.println("Please enter amount to withdraw: ");
                    double amount = s.nextDouble();
                    s.nextLine();
                    AccountsService.getInstance().runCommand(new AccountWithdrawCommand(source, amount));
                }

                case LIST_SELF_ACCOUNT_TRANSFERS -> {
                    System.out.println("Please enter account number: ");
                    String accountNumber = s.nextLine().trim();
                    AccountsService.getInstance().runCommand(new ListSelfAccountTransfersCommand(accountNumber));
                }

                case LIST_USER_ACCOUNT_TRANSFERS -> {
                    System.out.println("Please enter account number: ");
                    String accountNumber = s.nextLine().trim();
                    AccountsService.getInstance().runCommand(new ListUserAccountTransfersCommand(accountNumber));
                }

                case CREATE_CARD -> {
                    System.out.println("Please enter card number: ");
                    String cardNumber = s.nextLine().trim();
                    System.out.println("Please enter account: ");
                    String account = s.nextLine().trim();
                    AccountsService.getInstance().runCommand(new CreateCardCommand(cardNumber, account));
                }

                case DISABLE_CARD -> {
                    System.out.println("Please enter card number: ");
                    String cardNumber = s.nextLine().trim();
                    AccountsService.getInstance().runCommand(new DisableCardCommand(cardNumber));
                }

                case DELETE_CARD -> {
                    System.out.println("Please enter card number: ");
                    String cardNumber = s.nextLine().trim();
                    AccountsService.getInstance().runCommand(new DeleteCardCommand(cardNumber));
                }

                case LIST_SELF_ACCOUNT_CARDS -> {
                    System.out.println("Please enter account number: ");
                    String accountNumber = s.nextLine().trim();
                    AccountsService.getInstance().runCommand(new ListSelfAccountCardsCommand(accountNumber));
                }

                case LIST_USER_ACCOUNT_CARDS -> {
                    System.out.println("Please enter account number: ");
                    String accountNumber = s.nextLine().trim();
                    AccountsService.getInstance().runCommand(new ListUserAccountCardsCommand(accountNumber));
                }

                case LIST_SELF_CARDS -> AccountsService.getInstance().runCommand(new ListSelfCardsCommand());

                case LIST_USER_CARDS -> {
                    System.out.println("Please enter username: ");
                    String username = s.nextLine().trim();
                    AccountsService.getInstance().runCommand(new ListUserCardsCommand(username));
                }

                case EXIT -> {}
            }

            System.out.println("Command " + commandId + " executed successfully.");
        } catch (CommandException e) {
            System.out.println("> " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (!initialize()) {
            return;
        }
        runtime();
        deinitialize();
    }
}