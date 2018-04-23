Feature: Returns all orders in the system
  Scenario: a user attempts to get all orders
    Given orders exist
    When getAllOrders is called
    Then getAllOrders will return a list of basic order objects