package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;
import Services.UserService;

public class ListSelfCardsCommand extends AccountCommand {
    public ListSelfCardsCommand() {
        super(CommandType.LIST_SELF_CARDS);
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        String currentUsername = UserService.getInstance().getAuthenticatedUser().getUsername();
        s.listUserCards(currentUsername).forEach(c -> System.out.println("- " + c.toString()));
    }
}