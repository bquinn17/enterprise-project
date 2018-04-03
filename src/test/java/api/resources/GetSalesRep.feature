Feature: Support for getting a sales rep by id
  Scenario: The sales rep id is not found
    Given an invalid sales rep id
    When a user calls "/orders"
    Then the api will return 404

  Scenario: The sales rep id is found
    Given a valid sales rep id
    When a user calls "/orders"
    Then the api will return 302