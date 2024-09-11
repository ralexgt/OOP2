package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class ListUserAccountTransfersCommand extends AccountCommand {
    private final String number;

    public ListUserAccountTransfersCommand(String number) {
        super(CommandType.LIST_USER_ACCOUNT_TRANSFERS);
        this.number = number;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.listAccountTransfers(number).forEach(t -> System.out.println("- " + t.toString()));
    }
}