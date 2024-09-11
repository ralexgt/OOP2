package Models;

import DataStore.CsvStreamable;

public abstract class User implements CsvStreamable {
    protected final String username;
    protected String password;
    protected final UserType type;

    public User(String username, String password, UserType type) {
        this.username = username;
        setPassword(password);
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public UserType getType() {
        return type;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (checkPassword(oldPassword)) {
            setPassword(newPassword);
            return true;
        }

        return false;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", type=" + type + "]";
    }
}