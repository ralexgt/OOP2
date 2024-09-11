package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class ListUserAccountsCommand extends AccountCommand {
    private final String username;

    public ListUserAccountsCommand(String username) {
        super(CommandType.LIST_USER_ACCOUNTS);
        this.username = username;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.getUserAccounts(username).forEach(account -> System.out.println("- " + account.toString()));
    }
}