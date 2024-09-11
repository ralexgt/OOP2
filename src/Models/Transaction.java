package Models;

import DataStore.CsvStreamable;

import java.util.Map;
import java.util.TreeMap;

public record Transaction(String source, String destination, double amount) implements CsvStreamable {

    @Override
    public Map<String, String> toCsvKeyValuePairs() {
        Map<String, String> csvKeyValuePairs = new TreeMap<>();
        csvKeyValuePairs.put("source", source);
        csvKeyValuePairs.put("destination", destination);
        csvKeyValuePairs.put("amount", String.valueOf(amount));
        return csvKeyValuePairs;
    }

    @Override
    public String toString() {
        return "Transaction [" + "source=" + source + ", destination=" + destination + ", amount=" + amount + ']';
    }
}