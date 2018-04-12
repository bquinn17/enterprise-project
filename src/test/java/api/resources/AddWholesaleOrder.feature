Feature: Support for adding wholesale orders
  Scenario: A new wholesale order is created
    Given a correctly defined wholesale order
    When a user adds a new "wholesaleOrder" "/orders/wholesale/new"
    Then the api will return 201