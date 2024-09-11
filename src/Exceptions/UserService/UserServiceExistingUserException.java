package Exceptions.UserService;

public class UserServiceExistingUserException extends UserServiceException {
    public UserServiceExistingUserException(String username) {
        super("A user with username " + username + " already exists");
    }
}