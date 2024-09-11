package DataStore;

import Models.Client;
import Models.Employee;
import Models.User;

import java.util.Map;

public class UserCatalog extends DataMapCatalog<User> {
    public UserCatalog() {}

    @Override
    protected User createFromCsvData(Map<String, String> csvKeyValuePairs) {
        String username = csvKeyValuePairs.get("username");
        String password = csvKeyValuePairs.get("password");

        return switch (csvKeyValuePairs.get("type")) {
            case "client" -> new Client(username, password);
            case "employee" -> {
                int accessLevel;
                try {
                    accessLevel = Integer.parseInt(csvKeyValuePairs.get("accessLevel"));
                } catch (NumberFormatException e) {
                    accessLevel = 0;
                }
                yield new Employee(username, password, accessLevel);
            }
            default ->
                throw new IllegalArgumentException("Invalid user type");
        };
    }

    @Override
    protected String getKeyFromCsvData(Map<String, String> csvKeyValuePairs) {
        return csvKeyValuePairs.get("username");
    }

    @Override
    protected String[] getHeaders() {
        return new String[]{"username", "password", "accessLevel", "type"};
    }
}