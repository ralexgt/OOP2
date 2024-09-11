package DataStore;

import Models.Card;

import java.util.Map;

public class CardCatalog extends DataMapCatalog<Card> {
    @Override
    protected String getKeyFromCsvData(Map<String, String> csvKeyValuePairs) {
        return csvKeyValuePairs.get("number");
    }

    @Override
    protected Card createFromCsvData(Map<String, String> csvKeyValuePairs) {
        String number = csvKeyValuePairs.get("number");
        String account = csvKeyValuePairs.get("account");
        boolean active = csvKeyValuePairs.get("active").equals("active");
        return new Card(number, account, active);
    }

    @Override
    protected String[] getHeaders() {
        return new String[]{"number", "account", "active"};
    }
}