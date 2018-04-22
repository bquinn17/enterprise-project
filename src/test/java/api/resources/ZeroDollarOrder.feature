Feature: Support for Customer Relations to refund orders
#  Scenario: The price of the order is not set
#    Given a retail order with the price set to -1 dollars
#    When a user adds a new "retailOrder" "/orders/new/refund"
#    Then the api will return 400

#  Scenario: The price of the order is not $0
#    Given a retail order with the price set to 42 dollars
#    When a user adds a new "retailOrder" "/orders/new/refund"
#    Then the api will return 400

#  Scenario: The price of the order is $0
#    Given a retail order with the price set to 0 dollars
#    When a user adds a new "retailOrder" "/orders/new/refund"
#    Then the api will return 201
  Scenario: a user calls the refund endpoint and the price is anything but 0 dollars
    Given a retail order with the price set to 42 dollars
    When zeroDollarOrder is called
    Then zeroDollarOrder will return null

  Scenario: a user calls the refund endpoint and the price is 0 dollars
    Given a retail order with the price set to 0 dollars
    When zeroDollarOrder is called
    Then zeroDollarOrder will return a newly created order