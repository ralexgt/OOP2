package DataStore;

import java.util.Map;

public interface CsvStreamable {
    Map<String, String> toCsvKeyValuePairs();
}