package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class DeleteCardCommand extends AccountCommand {
    private final String cardNumber;

    public DeleteCardCommand(String cardNumber) {
        super(CommandType.DELETE_CARD);
        this.cardNumber = cardNumber;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.deleteCard(cardNumber);
    }
}