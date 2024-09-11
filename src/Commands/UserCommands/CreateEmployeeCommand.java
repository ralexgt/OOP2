package Commands.UserCommands;

import Commands.CommandType;
import Exceptions.UserService.UserServiceException;
import Services.UserService;

public class CreateEmployeeCommand extends UserCommand {
    private final String username;
    private final String password;
    private final int accessLevel;

    public CreateEmployeeCommand(String username, String password, int accessLevel) {
        super(CommandType.CREATE_EMPLOYEE);
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    @Override
    protected void handler(UserService s) throws UserServiceException {
        s.createEmployee(username, password, accessLevel);
    }
}