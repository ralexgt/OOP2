package Models;

import java.util.Map;
import java.util.TreeMap;

public class Employee extends User {
    protected int accessLevel;

    public Employee(String username, String password, int accessLevel) {
        super(username, password, UserType.Employee);
        this.accessLevel = accessLevel;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Map<String, String> toCsvKeyValuePairs() {
        Map<String, String> csvKeyValuePairs = new TreeMap<>();
        csvKeyValuePairs.put("username", username);
        csvKeyValuePairs.put("password", password);
        csvKeyValuePairs.put("accessLevel", String.valueOf(accessLevel));
        csvKeyValuePairs.put("type", "employee");
        return csvKeyValuePairs;
    }
}