package Exceptions.Commands;

import Commands.CommandType;
import Services.ServiceInterface;

public class CommandNotSupportedInServiceException extends CommandException {
    public CommandNotSupportedInServiceException(CommandType id, ServiceInterface s) {
        super(id, "Command " + id + " not supported in service " + s.getName());
    }
}