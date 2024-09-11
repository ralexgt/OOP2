package Models;

public class CurrentAccount extends Account {

    public CurrentAccount(String number, String owner, double balance) {
        super(number, owner, balance, AccountType.CURRENT_ACCOUNT);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}