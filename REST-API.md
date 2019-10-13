# Documentation of the REST Endpoints of the Bank Application

| Verb   | Resource URI with Request Params | Effect                                                                    |Example url                                                                                |
| ------ | -------------------------------- | ------------------------------------------------------------------------- |http://localhost:8080/api/users/1/accounts                                         |
| GET    | /api/users/{id}/accounts         | Get all the accounts belongs to the user with given **user Id**.          |
                                            | Requires, that the current user is the owner of the given account.        |
| GET    | /api/accounts/{id}/transactions  | Get all the transactions belongs to the account with given **account Id**.| http://localhost:8080/api/accounts/3/transactions
                                            | Requires, the current account is the owner of the given transactions.     |
                                            | Get transactions also can be pagination to improve performance.  