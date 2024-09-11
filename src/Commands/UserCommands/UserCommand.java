package Commands.UserCommands;

import Commands.Command;
import Commands.CommandType;
import Exceptions.Commands.CommandException;
import Exceptions.Commands.ServiceNotSupportedCommandException;
import Exceptions.UserService.UserServiceException;
import Services.ServiceInterface;
import Services.UserService;

public abstract class UserCommand extends Command {
    public UserCommand(CommandType commandType) {
        super(commandType);
    }

    @Override
    protected final void handler(ServiceInterface s) throws CommandException {
        if (s instanceof UserService) {
            try {
                handler((UserService) s);
            } catch (UserServiceException e) {
                throw new CommandException(getType(), e.getMessage());
            }
        } else {
            throw new ServiceNotSupportedCommandException(getType(), s);
        }
    }

    protected abstract void handler(UserService s) throws UserServiceException;
}