Feature: Support for adding wholesale orders
  Scenario: fails to complete due to missing top level information
    Given a retail order missing top level information
    When addRetailOrder is called
    Then addRetailOrder will return the offending order

  Scenario: fails to complete due to failure to contact external endpoints
    Given a retail order where our system fails to call an external endpoint
    When addRetailOrder is called
    Then addRetailOrder will return the offending order

  Scenario: succeeds with a valid retail order
    Given a valid retail order
    When addRetailOrder is called
    Then addRetailOrder will return a valid order