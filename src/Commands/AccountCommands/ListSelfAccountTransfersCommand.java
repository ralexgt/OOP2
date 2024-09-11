package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;
import Services.UserService;

public class ListSelfAccountsCommand extends AccountCommand {
    public ListSelfAccountsCommand() {
        super(CommandType.LIST_SELF_ACCOUNTS);
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        String currentUsername = UserService.getInstance().getAuthenticatedUser().getUsername();
        s.getUserAccounts(currentUsername).forEach(account -> System.out.println("- " + account.toString()));
    }
}