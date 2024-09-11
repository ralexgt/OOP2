package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class CloseCurrentAccountCommand extends AccountCommand {
    private final String number;

    public CloseCurrentAccountCommand(String number) {
        super(CommandType.CLOSE_CURRENT_ACCOUNT);
        this.number = number;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.closeCurrentAccount(number);
    }
}