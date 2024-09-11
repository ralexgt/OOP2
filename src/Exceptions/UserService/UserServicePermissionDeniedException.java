package Exceptions.UserService;

public class UserServicePermissionDeniedException extends UserServiceException {
    public UserServicePermissionDeniedException() {
        super("Permission denied");
    }
}