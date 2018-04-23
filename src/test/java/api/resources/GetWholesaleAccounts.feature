Feature: Support for getting all wholesale accounts
  Scenario: attempting to get a list of wholesale accounts
    When getWholesaleAccounts is called
    Then getWholesaleAccounts will return a list of all wholesale accounts