package Services;

import Commands.Command;
import Commands.CommandType;
import Exceptions.Commands.CommandException;
import Exceptions.Commands.CommandNotSupportedInServiceException;

import java.util.List;

public interface ServiceInterface {
    String getName();
    void initialize();
    void deinitialize();

    default void runCommand(Command command) throws CommandException {
        if (getAvailableCommands().contains(command.getType())) {
            command.execute(this);
        } else {
            throw new CommandNotSupportedInServiceException(command.getType(), this);
        }
    }

    List<CommandType> getAvailableCommands();
}