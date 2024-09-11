package Models;

import DataStore.CsvStreamable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

public class Log implements CsvStreamable {
    protected String actionName;
    protected LocalDateTime timestamp;

    public Log(String actionName, LocalDateTime timestamp) {
        this.actionName = actionName;
        this.timestamp = timestamp;
    }

    @Override
    public Map<String, String> toCsvKeyValuePairs() {
        Map<String, String> csvKeyValuePairs = new TreeMap<>();
        csvKeyValuePairs.put("name_of_action", actionName);
        csvKeyValuePairs.put("timestamp", timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return csvKeyValuePairs;
    }

    public String getActionName() {
        return actionName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}