package Commands.UserCommands;

import Commands.CommandType;
import Services.UserService;

public class LogoutUserCommand extends UserCommand {
    public LogoutUserCommand() {
        super(CommandType.LOGOUT_USER);
    }

    @Override
    protected void handler(UserService s) {
        s.logout();
    }
}