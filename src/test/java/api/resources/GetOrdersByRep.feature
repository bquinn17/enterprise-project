Feature: Support for getting list of orders by a rep
  Scenario: a user attempts to get a list of orders by an invalid sales rep id
    Given an incorrect sales rep id
    When getOrdersByRep is called
    Then getOrdersByRep will return nothing

  Scenario: a user attempts to get a list of orders with a valid sales rep id
    Given a valid sales rep id
    When getOrdersByRep is called
    Then getOrdersByRep will return a list of wholesaleOrders