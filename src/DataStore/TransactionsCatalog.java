package DataStore;

import Models.Transaction;

import java.util.Map;

public class TransactionsCatalog extends DataListCatalog<Transaction> {
    @Override
    protected Transaction createFromCsvData(Map<String, String> csvKeyValuePairs) {
        String source = csvKeyValuePairs.get("source");
        if (source.equals("null")) {
            source = null;
        }

        String destination = csvKeyValuePairs.get("destination");
        if (destination.equals("null")) {
            destination = null;
        }

        double amount = Double.parseDouble(csvKeyValuePairs.get("amount"));
        return new Transaction(source, destination, amount);
    }

    @Override
    protected String[] getHeaders() {
        return new String[]{"source", "destination", "amount"};
    }
}