Feature: Support for changing the status of a Retail Order
#  Scenario: The order is not found in the database
#    Given an order id not in the database
#    When a user changes a "retailOrder" status "/orders/update/status"
#    Then the api will return 404
#
#
#  Scenario: The status passed in is not valid
#    Given an invalid order status
#    When a user changes a "retailOrder" status "/orders/update/status"
#    Then the api will return 304
#
#  Scenario: The order is updated with a new status
#    Given a valid status of an existing order
#    When a user changes a "retailOrder" status "/orders/update/status"
#    Then the api will return 202

  Scenario: A status is attempted to be updated on a non-existent order
    Given a nonexisting order
    When changeOrderStatus is called with a status of ""
    Then changeOrderStatus will not return an order

  Scenario: A status is attempted to be updated on an existing order with an invalid status
    Given an existing order
    When changeOrderStatus is called with a status of "not_a_status"
    Then changeOrderStatus will not return an order

  Scenario: A status is attempted to be updated on an existing order with a valid status
    Given an existing order
    When changeOrderStatus is called with a status of "shipped"
    Then changeOrderStatus will return an updated order