Feature: for getting total revenue of a specific region
  Scenario: a user successfully gets the revenue of a specific region
    Given a region has wholesale orders
    When getRevenueFromRegion is called
    Then getRevenueFromRegion will return the total revenue from that region