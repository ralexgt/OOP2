package Commands.AccountCommands;

import Commands.Command;
import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Exceptions.Commands.CommandException;
import Exceptions.Commands.ServiceNotSupportedCommandException;
import Services.AccountsService;
import Services.ServiceInterface;

public abstract class AccountCommand extends Command {
    public AccountCommand(CommandType commandType) {
        super(commandType);
    }

    @Override
    protected void handler(ServiceInterface s) throws CommandException {
        if (s instanceof AccountsService) {
            try {
                handler((AccountsService) s);
            } catch (AccountServiceException e) {
                throw new CommandException(getType(), e.getMessage());
            }
        } else {
            throw new ServiceNotSupportedCommandException(getType(), s);
        }
    }

    protected abstract void handler(AccountsService s) throws AccountServiceException;
}