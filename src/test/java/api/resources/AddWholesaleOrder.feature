Feature: Support for adding wholesale orders
  Scenario: fails to complete due to missing top level information
    Given a wholesale order missing top level information
    When addWholesaleOrder is called
    Then addWholesaleOrder will return the offending order

  Scenario: fails to complete due to missing sales rep info
    Given a wholesale order where salesrep is missing information
    When addWholesaleOrder is called
    Then addWholesaleOrder will return the offending order

  Scenario: fails to complete due to failure to contact external endpoints
    Given a wholesale order where our system fails to call an external endpoint
    When addWholesaleOrder is called
    Then addWholesaleOrder will return the offending order with the status changed to placed

  Scenario: succeeds with a valid wholesale order
    Given a valid wholesale order
    When addWholesaleOrder is called
    Then addWholesaleOrder will return the offending order with the status changed to placed