Feature: getting the total revenue for a given sales rep
  Scenario: a user attempts to get total revenue for a given sales rep
    Given a valid sales rep and a range of dates
    When getRevenueForSalesRep is called
    Then getRevenueForSalesRep will return the total revenue for that rep for that period