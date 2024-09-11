package Models;

import java.util.Map;
import java.util.TreeMap;

public class Client extends User {
    public Client(String username, String password) {
        super(username, password, UserType.Client);
    }

    @Override
    public Map<String, String> toCsvKeyValuePairs() {
        Map<String, String> csvKeyValuePairs = new TreeMap<>();
        csvKeyValuePairs.put("username", username);
        csvKeyValuePairs.put("password", password);
        csvKeyValuePairs.put("type", "client");
        return csvKeyValuePairs;
    }
}