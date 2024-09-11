package Exceptions.UserService;

public class UserServiceUserNotFoundException extends UserServiceException {
    public UserServiceUserNotFoundException(String username) {
        super("User with name " + username + " not found");
    }
}