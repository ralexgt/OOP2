package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class AccountWithdrawCommand extends AccountCommand {
    private final String from;
    private final double amount;

    public AccountWithdrawCommand(String from, double amount) {
        super(CommandType.WITHDRAW_ACCOUNT);
        this.from = from;
        this.amount = amount;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.withdraw(from, amount);
    }
}