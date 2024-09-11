package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class AccountDepositCommand extends AccountCommand {
    private final String number;
    private final double amount;

    public AccountDepositCommand(String number, double amount) {
        super(CommandType.DEPOSIT_TO_ACCOUNT);
        this.number = number;
        this.amount = amount;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.deposit(number, amount);
    }
}