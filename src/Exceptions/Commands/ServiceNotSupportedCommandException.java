package Exceptions.Commands;

import Commands.CommandType;
import Services.ServiceInterface;

public class ServiceNotSupportedCommandException extends CommandException {
    public ServiceNotSupportedCommandException(CommandType id, ServiceInterface s) {
        super(id, "Service " + s.getName() + " is not supported by this command");
    }
}