package Exceptions.Commands;

import Commands.CommandType;

public class CommandException extends Exception {
    public CommandException(CommandType id, String message) {
        super("Error executing command " + id + ": " + message);
    }
}