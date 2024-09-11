package Models;

import java.util.Map;

public class DepositAccount extends Account {
    double interest;

    public DepositAccount(String number, String owner, double balance, double interest) {
        super(number, owner, balance, AccountType.DEPOSIT_ACCOUNT);
        this.interest = interest;
    }

    public double getInterest() {
        return interest;
    }

    @Override
    public Map<String, String> toCsvKeyValuePairs() {
        Map<String, String> csvKeyValuePairs = super.toCsvKeyValuePairs();
        csvKeyValuePairs.put("interest", String.valueOf(interest));
        return csvKeyValuePairs;
    }

    public String toString() {
        return "Account [number=" + number + ", owner=" + owner + ", balance=" + balance + ", interest=" + interest + ", accountType=" + accountType + "]";
    }
}