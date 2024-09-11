package DataStore;

import Models.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LogsCatalog extends DataListCatalog<Log> {
    @Override
    protected Log createFromCsvData(Map<String, String> csvKeyValuePairs) {
        String actionName = csvKeyValuePairs.get("name_of_action");
        LocalDateTime timestamp = LocalDateTime.parse(csvKeyValuePairs.get("timestamp"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new Log(actionName, timestamp);
    }

    @Override
    protected String[] getHeaders() {
        return new String[]{"name_of_action", "timestamp"};
    }
}