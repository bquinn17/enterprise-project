Feature: to gather total revenue for the whole ERP
  Scenario: a user attempts to get the total revenue
    Given a list of wholesale and retail orders
    When getTotalRevenue is called
    Then getTotalRevenue will return the total revenue