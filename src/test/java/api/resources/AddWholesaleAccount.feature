Feature: Support for adding new wholesale accounts
  Scenario: A user creates a new wholesale account
    When addWholesaleAccount is called
    Then addWholesaleAccount will return a newly created wholesaleAccount