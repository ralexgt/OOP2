package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class CreateCardCommand extends AccountCommand {
    private final String cardNumber;
    private final String accountNumber;

    public CreateCardCommand(String cardNumber, String accountNumber) {
        super(CommandType.CREATE_CARD);
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.createCard(cardNumber, accountNumber);
    }
}