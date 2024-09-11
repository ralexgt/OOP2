package Exceptions.UserService;

public class UserServiceWrongPasswordException extends UserServiceException{
    public UserServiceWrongPasswordException() {
        super("Wrong Password");
    }
}