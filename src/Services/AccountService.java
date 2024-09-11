package Services;

import Commands.CommandType;

import Config.PathsConfig;
import DataStore.AccountsCatalog;
import DataStore.CardCatalog;
import DataStore.TransactionsCatalog;
import Exceptions.AccountService.AccountServiceException;
import Models.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum AccountsService implements ServiceInterface {
    INSTANCE;

    private final AccountsCatalog accounts = new AccountsCatalog();
    private final TransactionsCatalog transactions = new TransactionsCatalog();
    private final CardCatalog cards = new CardCatalog();

    AccountsService() {}

    public static AccountsService getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Accounts service";
    }

    @Override
    public void initialize() {
        accounts.loadData(PathsConfig.accountsPath);
        transactions.loadData(PathsConfig.transactionsPath);
        cards.loadData(PathsConfig.cardsPath);
    }

    @Override
    public void deinitialize() {
        accounts.saveData(PathsConfig.accountsPath);
        transactions.saveData(PathsConfig.transactionsPath);
        cards.saveData(PathsConfig.cardsPath);
    }

    @Override
    public List<CommandType> getAvailableCommands() {
        CommandType[] serviceCommands = {
            CommandType.CREATE_CURRENT_ACCOUNT,
            CommandType.CREATE_DEPOSIT_ACCOUNT,
            CommandType.LIST_ACCOUNTS,
            CommandType.LIST_SELF_ACCOUNTS,
            CommandType.LIST_USER_ACCOUNTS,
            CommandType.CLOSE_CURRENT_ACCOUNT,
            CommandType.CLOSE_DEPOSIT_ACCOUNT,
            CommandType.TRANSFER_TO_ACCOUNT,
            CommandType.DEPOSIT_TO_ACCOUNT,
            CommandType.WITHDRAW_ACCOUNT,
            CommandType.LIST_SELF_ACCOUNT_TRANSFERS,
            CommandType.LIST_USER_ACCOUNT_TRANSFERS,
            CommandType.CREATE_CARD,
            CommandType.DISABLE_CARD,
            CommandType.DELETE_CARD,
            CommandType.LIST_SELF_ACCOUNT_CARDS,
            CommandType.LIST_USER_ACCOUNT_CARDS,
            CommandType.LIST_SELF_CARDS,
            CommandType.LIST_USER_CARDS
        };

        return Arrays.stream(serviceCommands).filter(PermissionsService::hasPermission).toList();
    }

    public void createCurrentAccount(String number, String ownerName) throws AccountServiceException {
        User ownerUser = UserService.getInstance().getUser(ownerName);

        if (!(ownerUser instanceof Client)) {
            throw new AccountServiceException("Client " + ownerName + " does not exist");
        }

        if (accounts.getEntry(number) != null) {
            System.out.println("Account " + number + " already exists");
            throw new AccountServiceException("Account " + number + " already exists");
        }

        Account a = new CurrentAccount(number, ownerName, 0);
        accounts.setEntry(number, a);
    }

    public void createDepositAccount(String number, String ownerName, double interest, String from, double balance) throws AccountServiceException {
        User ownerUser = UserService.getInstance().getUser(ownerName);
        Account fromAccount = accounts.getEntry(from);

        if (!(ownerUser instanceof Client)) {
            throw new AccountServiceException("Client " + ownerName + " does not exist");
        }

        if (accounts.getEntry(number) != null) {
            throw new AccountServiceException("Account " + number + " already exists");
        }

        if (!(fromAccount instanceof CurrentAccount)) {
            throw new AccountServiceException("Current account " + number + " does not exist");
        }

        if (!fromAccount.getOwner().equals(ownerName)) {
            throw new AccountServiceException("Deposit should have the same owner as source account");
        }

        if (balance <= 0) {
            throw new AccountServiceException("Deposit amount must be greater than zero");
        }

        if (interest <= 0) {
            throw new AccountServiceException("Deposit interest must be greater than zero");
        }

        if (fromAccount.getBalance() < balance) {
            throw new AccountServiceException("Insufficient balance in source account");
        }

        Transaction t = new Transaction(from, number, balance);
        transactions.addEntry(t);
        ((CurrentAccount)fromAccount).setBalance(fromAccount.getBalance() - balance);

        Account a = new DepositAccount(number, ownerName, balance, interest);
        accounts.setEntry(number, a);
    }

    public Stream<Account> getAccounts() {
        return accounts.getAllEntries().values().stream();
    }

    public Stream<Account> getUserAccounts(String username) throws AccountServiceException {
        if (!(UserService.getInstance().getUser(username) instanceof Client)) {
            throw new AccountServiceException("Client " + username + " does not exist");
        }

        return getAccounts().filter(x -> x.getOwner().equals(username));
    }

    public void createTransfer(String from, String to, double amount) throws AccountServiceException {
        User currentUser = UserService.getInstance().getAuthenticatedUser();
        Account fromAccount = accounts.getEntry(from);
        Account toAccount = accounts.getEntry(to);

        if (!(fromAccount instanceof CurrentAccount)) {
            throw new AccountServiceException("Current account " + from + " does not exist");
        }

        if (!(toAccount instanceof CurrentAccount)) {
            throw new AccountServiceException("Current account " + to + " does not exist");
        }

        if (!fromAccount.getOwner().equals(currentUser.getUsername())) {
            throw new AccountServiceException("Current user does not own account " + fromAccount.getNumber());
        }

        if (amount <= 0) {
            throw new AccountServiceException("Invalid transaction amount");
        }

        if (fromAccount.getBalance() < amount) {
            throw new AccountServiceException("Insufficient balance in source account");
        }

        Transaction t = new Transaction(from, to, amount);
        transactions.addEntry(t);
        ((CurrentAccount)fromAccount).setBalance(fromAccount.getBalance() - amount);
        ((CurrentAccount)toAccount).setBalance(toAccount.getBalance() + amount);
    }

    public void deposit(String to, double amount) throws AccountServiceException {
        User currentUser = UserService.getInstance().getAuthenticatedUser();
        Account toAccount = accounts.getEntry(to);

        if (!(toAccount instanceof CurrentAccount)) {
            throw new AccountServiceException("Current account " + to + " does not exist");
        }

        if (!toAccount.getOwner().equals(currentUser.getUsername())) {
            throw new AccountServiceException("Current user does not own account " + toAccount.getNumber());
        }

        if (amount <= 0) {
            throw new AccountServiceException("Invalid deposit amount");
        }

        Transaction t = new Transaction(null, to, amount);
        transactions.addEntry(t);
        ((CurrentAccount)toAccount).setBalance(toAccount.getBalance() + amount);
    }

    public void withdraw(String from, double amount) throws AccountServiceException {
        User currentUser = UserService.getInstance().getAuthenticatedUser();
        Account fromAccount = accounts.getEntry(from);

        if (!(fromAccount instanceof CurrentAccount)) {
            throw new AccountServiceException("Current account " + from + " does not exist");
        }

        if (!fromAccount.getOwner().equals(currentUser.getUsername())) {
            throw new AccountServiceException("Current user does not own account " + fromAccount.getNumber());
        }

        if (amount <= 0) {
            throw new AccountServiceException("Invalid withdraw amount");
        }

        if (fromAccount.getBalance() < amount) {
            throw new AccountServiceException("Insufficient balance");
        }

        Transaction t = new Transaction(from, null, amount);
        transactions.addEntry(t);
        ((CurrentAccount)fromAccount).setBalance(fromAccount.getBalance() - amount);
    }

    public Stream<Transaction> listAccountTransfers(String number) {
        return transactions.getAllEntries().stream().filter(x -> x.destination() != null && x.destination().equals(number) || x.source() != null && x.source().equals(number));
    }

    public void closeCurrentAccount(String number) throws AccountServiceException {
        Account account = accounts.getEntry(number);
        if (!(account instanceof CurrentAccount)) {
            throw new AccountServiceException("Current account " + number + " does not exist");
        }

        accounts.removeEntry(number);
    }

    public void closeDepositAccount(String number, String to) throws AccountServiceException {
        Account deposit = accounts.getEntry(number);
        Account toAccount = accounts.getEntry(to);

        if (!(deposit instanceof DepositAccount)) {
            throw new AccountServiceException("Deposit account " + number + " does not exist");
        }

        if (!(toAccount instanceof CurrentAccount)) {
            throw new AccountServiceException("Current account " + to + " does not exist");
        }

        if (!(deposit.getOwner().equals(toAccount.getOwner()))) {
            throw new AccountServiceException("Deposit account should have the same owner as destination account");
        }

        double depositAmount = deposit.getBalance() * (1 + ((DepositAccount) deposit).getInterest());
        Transaction t = new Transaction(number, to, depositAmount);
        transactions.addEntry(t);
        ((CurrentAccount) toAccount).setBalance(toAccount.getBalance() + depositAmount);
        accounts.removeEntry(number);
    }

    public void createCard(String accountNumber, String cardNumber) throws AccountServiceException {
        Account account = accounts.getEntry(accountNumber);

        if (cards.getEntry(cardNumber) != null) {
            throw new AccountServiceException("Card " + cardNumber + " already exists");
        }

        if (!(account instanceof CurrentAccount)) {
            throw new AccountServiceException("Current account " + accountNumber + " does not exist");
        }

        Card c = new Card(cardNumber, accountNumber, true);
        cards.setEntry(cardNumber, c);
    }

    public void disableCard(String cardNumber) throws AccountServiceException {
        Card card = cards.getEntry(cardNumber);
        if (card == null) {
            throw new AccountServiceException("Card " + cardNumber + " does not exist");
        }

        card.disable();
    }

    public void deleteCard(String cardNumber) throws AccountServiceException {
        Card card = cards.getEntry(cardNumber);
        if (card == null) {
            throw new AccountServiceException("Card " + cardNumber + " does not exist");
        }

        cards.removeEntry(cardNumber);
    }

    public Stream<Card> listAccountCards(String accountNumber) throws AccountServiceException {
        if (!(accounts.getEntry(accountNumber) instanceof CurrentAccount)) {
            throw new AccountServiceException("Current account " + accountNumber + " does not exist");
        }

        return cards.getAllEntries().values().stream().filter(c -> c.getAccount().equals(accountNumber));
    }

    public Stream<Card> listUserCards(String username) throws AccountServiceException {
        if (!(UserService.getInstance().getUser(username) instanceof Client)) {
            throw new AccountServiceException("Client " + username + " does not exist");
        }

        List<String> userAccounts = getUserAccounts(username).map(Account::getNumber).toList();
        return cards.getAllEntries().values().stream().filter(c -> userAccounts.contains(c.getAccount()));
    }
}