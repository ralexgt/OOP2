package Services;

import Commands.CommandType;
import Config.PathsConfig;
import DataStore.UserCatalog;
import Exceptions.UserService.UserServiceException;
import Exceptions.UserService.UserServiceExistingUserException;
import Exceptions.UserService.UserServiceUserNotFoundException;
import Exceptions.UserService.UserServiceWrongPasswordException;
import Models.Client;
import Models.Employee;
import Models.User;
import Models.UserType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum UserService implements ServiceInterface {
    INSTANCE;

    private final UserCatalog users = new UserCatalog();
    private User authenticatedUser = null;

    UserService() {}

    public static UserService getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "User service";
    }

    @Override
    public void initialize() {
        users.loadData(PathsConfig.usersPath);
    }

    @Override
    public void deinitialize() {
        users.saveData(PathsConfig.usersPath);
    }

    @Override
    public List<CommandType> getAvailableCommands() {
        CommandType[] serviceCommands = {
            CommandType.LOGIN_USER,
            CommandType.LOGOUT_USER,
            CommandType.LIST_USERS,
            CommandType.CREATE_CLIENT,
            CommandType.CREATE_EMPLOYEE,
            CommandType.CHANGE_SELF_PASSWORD,
            CommandType.CHANGE_USER_PASSWORD,
            CommandType.CHANGE_EMPLOYEE_ACCESS_LEVEL,
            CommandType.DELETE_USER
        };

        return Arrays.stream(serviceCommands).filter(PermissionsService::hasPermission).toList();
    }

    public User getUser(String username) {
        return users.getEntry(username);
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void login(String username, String password) throws UserServiceException {
        if (users.getEntry(username) == null) {
            throw new UserServiceUserNotFoundException(username);
        }

        if (!users.getEntry(username).checkPassword(password)) {
            throw new UserServiceWrongPasswordException();
        }

        authenticatedUser = users.getEntry(username);
    }

    public void logout() {
        authenticatedUser = null;
    }

    public Stream<User> getUsers() {
        return users.getAllEntries().values().stream();
    }

    public void createClient(String username, String password) throws UserServiceException {
        if (users.getEntry(username) != null) {
            throw new UserServiceExistingUserException(username);
        }

        User u = new Client(username, password);
        users.setEntry(u.getUsername(), u);
    }

    public void createEmployee(String username, String password, int accessLevel) throws UserServiceException {
        if (users.getEntry(username) != null) {
            throw new UserServiceExistingUserException(username);
        }

        User u = new Employee(username, password, accessLevel);
        users.setEntry(u.getUsername(), u);
    }

    public void changeUserPassword(String username, String oldPassword, String newPassword) throws UserServiceException {
        User u = users.getEntry(username);
        if (u == null) {
            throw new UserServiceUserNotFoundException(username);
        }

        if (!u.changePassword(oldPassword, newPassword)) {
            throw new UserServiceWrongPasswordException();
        }
    }

    public void changeAccessLevel(String username, int accessLevel) throws UserServiceException {
        User u = users.getEntry(username);
        if (u == null || u.getType() != UserType.Employee) {
            throw new UserServiceUserNotFoundException(username);
        }

        ((Employee) u).setAccessLevel(accessLevel);
    }

    public void deleteUser(String username) throws UserServiceException {
        User u = users.getEntry(username);
        if (u == null) {
            throw new UserServiceUserNotFoundException(username);
        }

        if (u == authenticatedUser) {
            authenticatedUser = null;
        }

        users.removeEntry(username);
    }
}