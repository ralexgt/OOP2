package Commands.AccountCommands;

import Commands.CommandType;
import Exceptions.AccountService.AccountServiceException;
import Services.AccountsService;

public class AccountTransferCommand extends AccountCommand {
    private final String sourceNumber;
    private final String destinationNumber;
    private final double amount;

    public AccountTransferCommand(String sourceNumber, String destinationNumber, double amount) {
        super(CommandType.TRANSFER_TO_ACCOUNT);
        this.sourceNumber = sourceNumber;
        this.destinationNumber = destinationNumber;
        this.amount = amount;
    }

    @Override
    protected void handler(AccountsService s) throws AccountServiceException {
        s.createTransfer(sourceNumber, destinationNumber, amount);
    }
}