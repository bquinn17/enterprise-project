Feature: Support for adding new wholesale accounts
  Scenario: A user creates a valid wholesale account
    Given an valid wholesale account
    When a user creates a wholesale account "/wholesale/account/new"
    Then the api will return 201