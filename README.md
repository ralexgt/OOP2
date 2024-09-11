# Object-oriented programming II (JAVA)

Repository containing the project for Object-oriented programming II course.

Showcase of object-oriented principles and classes using java for a bank management system, having users, accounts, cards and transactions.

I used abstractization layers to separate each concern.
The data saving is handled through generic DataCatalogs (with support for List data and Map data).
There are: User service, Accounts serivce and a Logging service to log each commnad

Incorrect input error are handled through defined exceptions.

Program commands are:

- LOGIN_USER
- LOGOUT_USER
- LIST_USERS
- CREATE_CLIENT
- CREATE_EMPLOYEE
- CHANGE_SELF_PASSWORD
- CHANGE_USER_PASSWORD
- CHANGE_EMPLOYEE_ACCESS_LEVEL
- DELETE_USER
- CREATE_CURRENT_ACCOUNT
- CREATE_DEPOSIT_ACCOUNT
- LIST_ACCOUNTS
- LIST_SELF_ACCOUNTS
- LIST_USER_ACCOUNTS
- CLOSE_CURRENT_ACCOUNT
- CLOSE_DEPOSIT_ACCOUNT
- TRANSFER_TO_ACCOUNT
- DEPOSIT_TO_ACCOUNT
- WITHDRAW_ACCOUNT
- LIST_SELF_ACCOUNT_TRANSFERS
- LIST_USER_ACCOUNT_TRANSFERS
- CREATE_CARD
- DISABLE_CARD
- DELETE_CARD
- LIST_SELF_ACCOUNT_CARDS
- LIST_USER_ACCOUNT_CARDS
- LIST_SELF_CARDS
- LIST_USER_CARDS
