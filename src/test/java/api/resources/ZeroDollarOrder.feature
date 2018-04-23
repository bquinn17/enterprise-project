Feature: Support for Customer Relations to refund orders
  Scenario: a user calls the refund endpoint and the price is anything but 0 dollars
    Given a retail order with the price set to 42 dollars
    When zeroDollarOrder is called
    Then zeroDollarOrder will return null

  Scenario: a user calls the refund endpoint and the price is 0 dollars
    Given a retail order with the price set to 0 dollars
    When zeroDollarOrder is called
    Then zeroDollarOrder will return a newly created order