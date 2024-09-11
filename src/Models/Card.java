package Models;

import DataStore.CsvStreamable;

import java.util.HashMap;
import java.util.Map;

public class Card implements CsvStreamable {
    private final String number;
    private final String account;
    private boolean active;

    public Card(String number, String account, boolean active) {
        this.number = number;
        this.account = account;
        this.active = active;
    }

    public String getNumber() {
        return number;
    }

    public String getAccount() {
        return account;
    }

    public boolean isActive() {
        return active;
    }

    public void disable() {
        active = false;
    }

    @Override
    public String toString() {
        return "Card [number=" + number + ", account=" + account + ", " + (active ? "active" : "disabled") + "]";
    }

    @Override
    public Map<String, String> toCsvKeyValuePairs() {
        Map<String, String> csvKeyValuePairs = new HashMap<>();
        csvKeyValuePairs.put("number", number);
        csvKeyValuePairs.put("account", account);
        csvKeyValuePairs.put("active", active ? "active" : "disabled");
        return csvKeyValuePairs;
    }
}