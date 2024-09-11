package Commands;

import Exceptions.Commands.CommandException;
import Exceptions.Commands.CommandNotSupportedInServiceException;
import Services.LoggingService;
import Services.ServiceInterface;

public abstract class Command {
    CommandType type;

    public Command(CommandType id) {
        this.type = id;
    }

    public void execute(ServiceInterface s) throws CommandException {
        if (s.getAvailableCommands().contains(type)) {
            handler(s);
            log();
        } else {
            throw new CommandNotSupportedInServiceException(type, s);
        }
    }

    abstract protected void handler(ServiceInterface s) throws CommandException;

    protected void log()
    {
        LoggingService.getInstance().log(this.type.toString());
    }

    public CommandType getType() {
        return type;
    }
}