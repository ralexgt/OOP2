package Commands.UserCommands;

import Commands.CommandType;
import Exceptions.UserService.UserServiceException;
import Services.UserService;

public class LoginUserCommand extends UserCommand {
    private final String username;
    private final String password;

    public LoginUserCommand(String username, String password) {
        super(CommandType.LOGIN_USER);
        this.username = username;
        this.password = password;
    }

    @Override
    protected void handler(UserService s) throws UserServiceException {
        s.login(username, password);
    }
}