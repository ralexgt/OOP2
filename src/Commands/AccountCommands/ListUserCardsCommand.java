package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class ListUserCardsCommand extends AccountCommand {
    private final String username;

    public ListUserCardsCommand(String username) {
        super(CommandType.LIST_USER_CARDS);
        this.username = username;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.listUserCards(username).forEach(c -> System.out.println("- " + c));
    }
}