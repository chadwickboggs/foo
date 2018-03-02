Feature: CashRegister

  Scenario: Pay exact price
    Given Price 20
    When Paid 20
    Then Change 0
