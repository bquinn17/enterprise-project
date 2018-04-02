Feature: Support for Customer Relations to refund orders
  Scenario: The price of the order is not set
    Given a retail order with price of the order not set
    When a user calls "/orders/new/refund"
    Then the api will return 400

  Scenario: The price of the order is not $0
    Given a retail order with the price set to 42 dollars
    When a user calls "/orders/new/refund"
    Then the api will return 400

  Scenario: The price of the order is $0
    Given a retail order with the price set to 0 dollars
    When a user calls "/orders/new/refund"
    Then the api will return 201