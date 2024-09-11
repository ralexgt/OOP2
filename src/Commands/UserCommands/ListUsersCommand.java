package Commands.UserCommands;

import Commands.CommandType;
import Exceptions.UserService.UserServiceException;
import Services.UserService;

public class ListUsersCommand extends UserCommand {
    public ListUsersCommand() {
        super(CommandType.LIST_USERS);
    }

    @Override
    protected void handler(UserService s) throws UserServiceException {
        s.getUsers().forEach(u -> System.out.println(u.toString()));
    }
}