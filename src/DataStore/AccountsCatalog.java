package DataStore;

import Models.Account;
import Models.CurrentAccount;
import Models.DepositAccount;

import java.util.Map;

public class AccountsCatalog extends DataMapCatalog<Account> {
    @Override
    protected Account createFromCsvData(Map<String, String> csvKeyValuePairs) {
        String number = csvKeyValuePairs.get("number");
        String owner = csvKeyValuePairs.get("owner");
        double balance = Double.parseDouble(csvKeyValuePairs.get("balance"));

        return switch (csvKeyValuePairs.get("accountType")) {
            case "CURRENT_ACCOUNT" -> new CurrentAccount(number, owner, balance);
            case "DEPOSIT_ACCOUNT" -> {
                try {
                    double interest = Double.parseDouble(csvKeyValuePairs.get("interest"));
                    yield new DepositAccount(number, owner, balance, interest);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid interest");
                }
            }
            default -> throw new IllegalArgumentException("Invalid account type");
        };
    }

    @Override
    protected String getKeyFromCsvData(Map<String, String> csvKeyValuePairs) {
        return csvKeyValuePairs.get("number");
    }

    @Override
    protected String[] getHeaders() {
        return new String[]{"number", "owner", "balance", "accountType", "interest"};
    }
}