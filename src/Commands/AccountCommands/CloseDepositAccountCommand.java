package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class CloseDepositAccountCommand extends AccountCommand {
    private final String number;
    private final String to;

    public CloseDepositAccountCommand(String number, String to) {
        super(CommandType.CLOSE_DEPOSIT_ACCOUNT);
        this.number = number;
        this.to = to;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.closeDepositAccount(number, to);
    }
}