package Commands.UserCommands;

import Commands.CommandType;
import Exceptions.UserService.UserServiceException;
import Services.UserService;

public class ChangeUserPasswordCommand extends UserCommand {
    private final String username;
    private final String oldPassword;
    private final String newPassword;

    public ChangeUserPasswordCommand(String username, String oldPassword, String newPassword) {
        super(CommandType.CHANGE_USER_PASSWORD);
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    protected void handler(UserService s) throws UserServiceException {
        s.changeUserPassword(username, oldPassword, newPassword);
    }
}