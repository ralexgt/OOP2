package Models;

import java.util.Map;

public class UserFactory {
    public static User fromCsv(Map<String,String> data) {
        String username = data.get("username");
        String password = data.get("password");

        return switch (data.get("type")) {
            case "Models.Client" -> new Client(username, password);
            case "Models.Employee" -> {
                int accessLevel = Integer.parseInt(data.get("accessLevel"));
                yield new Employee(username, password, accessLevel);
            }
            default ->
                throw new IllegalArgumentException("Invalid user type");
        };
    }
}