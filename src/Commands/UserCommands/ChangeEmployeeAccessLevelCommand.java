
package Commands.UserCommands;

import Commands.CommandType;
import Exceptions.UserService.UserServiceException;
import Services.UserService;

public class ChangeEmployeeAccessLevelCommand extends UserCommand {
    private final String username;
    private final int accessLevel;

    public ChangeEmployeeAccessLevelCommand(String username, int accessLevel) {
        super(CommandType.CREATE_EMPLOYEE);
        this.username = username;
        this.accessLevel = accessLevel;
    }

    @Override
    protected void handler(UserService s) throws UserServiceException {
        s.changeAccessLevel(username, accessLevel);
    }
}
