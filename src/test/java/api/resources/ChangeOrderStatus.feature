Feature: Support for changing the status of a Retail Order
#  Scenario: The order is not found in the database
#    Given an order id not in the database
#    When a user changes a "retailOrder" status "/orders/update/status"
#    Then the api will return 404


#  Scenario: The status passed in is not valid
#    Given an invalid order status
#    When a user changes a "retailOrder" status "/orders/update/status"
#    Then the api will return 304
#
#  TODO not yet implemented
#  Scenario: The order is updated with a new status
#    Given a valid status of an existing order
#    When a user changes a "retailOrder" status "/orders/update/status"
#    Then the api will return 202