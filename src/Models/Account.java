package Models;

import DataStore.CsvStreamable;

import java.util.Map;
import java.util.TreeMap;

public abstract class Account implements CsvStreamable {
    protected final String number;
    protected final String owner;
    protected double balance;
    protected AccountType accountType;

    public Account(String number, String owner, double balance, AccountType accountType) {
        this.number = number;
        this.owner = owner;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public Map<String, String> toCsvKeyValuePairs() {
        Map<String, String> csvKeyValuePairs = new TreeMap<>();
        csvKeyValuePairs.put("number", number);
        csvKeyValuePairs.put("owner", owner);
        csvKeyValuePairs.put("balance", Double.toString(balance));
        csvKeyValuePairs.put("accountType", accountType.toString());
        return csvKeyValuePairs;
    }

    @Override
    public String toString() {
        return "Account [number=" + number + ", owner=" + owner + ", balance=" + balance + ", accountType=" + accountType + "]";
    }
}