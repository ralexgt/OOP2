package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class ListUserAccountCardsCommand extends AccountCommand {
    private final String accountNumber;

    public ListUserAccountCardsCommand(String accountNumber) {
        super(CommandType.LIST_USER_ACCOUNT_CARDS);
        this.accountNumber = accountNumber;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.listAccountCards(accountNumber).forEach(c -> System.out.println("- " + c));
    }
}