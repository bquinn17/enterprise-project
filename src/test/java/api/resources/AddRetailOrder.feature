Feature: Support for adding new retail orders
  Scenario: The products list is empty
    Given an order without products
    When a user calls "/orders/retail/new"
    Then the api will return 400

  Scenario: The customer email is empty
    Given an order without an email
    When a user calls "/orders/retail/new"
    Then the api will return 400

  Scenario: The customer address is empty
    Given an order without an address
    When a user calls "/orders/retail/new"
    Then the api will return 400

  Scenario: A new retail order is created
    Given a valid order
    When a user calls "/orders/retail/new"
    Then the api will return 201