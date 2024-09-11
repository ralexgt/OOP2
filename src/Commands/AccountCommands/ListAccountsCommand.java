package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class ListAccountsCommand extends AccountCommand {
    public ListAccountsCommand() {
        super(CommandType.LIST_ACCOUNTS);
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.getAccounts().forEach(account -> System.out.println("- " + account.toString()));
    }
}