# Documentation of the REST Endpoints of Account Transaction Service application
| Verb   | Resource URI with Request Params | Effect                                   |Example URL|
| ------ | -------------------------------- | ---------------------------------------- |----------------------------|
| GET    | /api/users/{id}/accounts                    | Get all the accounts belongs to the user with given **user Id**. Requires, that the current user is the owner of the given account.                    |http://localhost:8080/api/users/1/accounts|
| GET    | /api/accounts/{id}/transactions   | Get all the transactions belongs to the account with given **account Id**.Requires, the current account is the owner of the given transactions. Get transactions also can be pagination to improve performance. |http://localhost:8080/api/accounts/3/transactions|
