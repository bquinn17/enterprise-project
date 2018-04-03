Feature: Support for getting all wholesale accounts
  Scenario: The user is requesting the list of wholesale accounts
    When a user calls "/wholesale/accounts"
    Then the api will return 302