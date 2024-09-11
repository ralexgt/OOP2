package Commands.UserCommands;

import Commands.CommandType;
import Exceptions.UserService.UserServiceException;
import Services.UserService;

public class CreateClientCommand extends UserCommand {
    private final String username;
    private final String password;

    public CreateClientCommand(String username, String password) {
        super(CommandType.CREATE_CLIENT);
        this.username = username;
        this.password = password;
    }

    @Override
    protected void handler(UserService s) throws UserServiceException {
        s.createClient(username, password);
    }
}