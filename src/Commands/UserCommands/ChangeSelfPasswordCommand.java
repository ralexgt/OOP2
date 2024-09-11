package Commands.UserCommands;

import Commands.CommandType;
import Exceptions.UserService.UserServiceException;
import Models.User;
import Services.UserService;

public class ChangeSelfPasswordCommand extends UserCommand {
    private final String oldPassword;
    private final String newPassword;

    public ChangeSelfPasswordCommand(String oldPassword, String newPassword) {
        super(CommandType.CHANGE_SELF_PASSWORD);
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    protected void handler(UserService s) throws UserServiceException {
        User currentUser = UserService.getInstance().getAuthenticatedUser();
        s.changeUserPassword(currentUser.getUsername(), oldPassword, newPassword);
    }
}