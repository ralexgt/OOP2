package Commands.UserCommands;

import Commands.CommandType;
import Exceptions.UserService.UserServiceException;
import Services.UserService;

public class DeleteUserCommand extends UserCommand {
    private final String username;

    public DeleteUserCommand(String username) {
        super(CommandType.DELETE_USER);
        this.username = username;
    }

    @Override
    protected void handler(UserService s) throws UserServiceException {
        s.deleteUser(username);
    }
}