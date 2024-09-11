package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class CreateCurrentAccountCommand extends AccountCommand {
    private final String number;
    private final String ownerName;

    public CreateCurrentAccountCommand(String number, String ownerName) {
        super(CommandType.CREATE_CURRENT_ACCOUNT);
        this.number = number;
        this.ownerName = ownerName;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.createCurrentAccount(number, ownerName);
    }
}