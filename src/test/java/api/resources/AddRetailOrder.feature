Feature: Support for adding new retail orders
  Scenario: The products list is empty
    When a user calls "/orders/retail/new" without products
    Then the api will return 400

  Scenario: The customer email is empty
    When a user calls "/orders/retail/new"
    Then the api will return 400

  Scenario: The customer address is empty
    When a user calls "/orders/retail/new"
    Then the api will return 400

  Scenario: A new retail order is created
    When a user calls "/orders/retail/new"
    Then the api will return 201