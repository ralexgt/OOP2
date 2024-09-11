package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;
import Services.UserService;

public class ListSelfAccountTransfersCommand extends AccountCommand {
    private final String number;

    public ListSelfAccountTransfersCommand(String number) {
        super(CommandType.TRANSFER_TO_ACCOUNT);
        this.number = number;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        String currentUsername = UserService.getInstance().getAuthenticatedUser().getUsername();
        if (s.getUserAccounts(currentUsername).noneMatch(a -> a.getNumber().equals(number))) {
            throw new AccountServiceException("Current user does not own account " + number);
        }

        s.listAccountTransfers(number).forEach(t -> System.out.println("- " + t.toString()));
    }
}