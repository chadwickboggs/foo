Feature: Shopping

  Scenario: Pay exact price
    Given I buy a 20 dollar item
    When I pay 20 dollar
    Then I should receive 0 in change
