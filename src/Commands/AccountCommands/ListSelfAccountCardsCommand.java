package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;
import Services.UserService;

public class ListSelfAccountCardsCommand extends AccountCommand {
    private final String accountNumber;

    public ListSelfAccountCardsCommand(String accountNumber) {
        super(CommandType.LIST_SELF_ACCOUNT_CARDS);
        this.accountNumber = accountNumber;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        String currentUsername = UserService.getInstance().getAuthenticatedUser().getUsername();
        if (s.getUserAccounts(currentUsername).noneMatch(a -> a.getNumber().equals(accountNumber))) {
            throw new AccountServiceException("Current user does not own account " + accountNumber);
        }

        s.listAccountCards(accountNumber).forEach(c -> System.out.println("- " + c.toString()));
    }
}