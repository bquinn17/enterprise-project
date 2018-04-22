Feature: Support for getting all wholesale accounts
  Scenario: The user is requesting the list of wholesale accounts
    When a user gets a "wholesaleAccount" "/wholesale/accounts"
    Then the api will return 302