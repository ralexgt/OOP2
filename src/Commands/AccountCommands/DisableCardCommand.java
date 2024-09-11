package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class DisableCardCommand extends AccountCommand {
    private final String cardNumber;

    public DisableCardCommand(String cardNumber) {
        super(CommandType.DISABLE_CARD);
        this.cardNumber = cardNumber;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.disableCard(cardNumber);
    }
}