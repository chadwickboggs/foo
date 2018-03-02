Feature: CashRegister

  Background:
    Given Price 20

  Scenario Outline:
    When Paid <payment>
    Then Change <change>

  Examples:
    | payment | change |
    | 20      | 0      |
    | 30      | 10     |
