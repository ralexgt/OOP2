package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class CreateDepositAccountCommand extends AccountCommand {
    private final String number;
    private final String ownerName;
    private final double interest;
    private final String from;
    private final double balance;

    public CreateDepositAccountCommand(String number, String ownerName, double balance, String from, double interest) {
        super(CommandType.CREATE_CURRENT_ACCOUNT);
        this.number = number;
        this.ownerName = ownerName;
        this.balance = balance;
        this.from = from;
        this.interest = interest;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.createDepositAccount(number, ownerName, balance, from, interest);
    }
}