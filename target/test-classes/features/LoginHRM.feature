
Feature: Login OrangeHRM

  Background:
    Given I am on home page

  @smoke
  Scenario: Login to HRM with valid credentials
    When And i verify the Login text
    And I also verify the logo
    And I enter the details
    |username|password|
    |Admin   |admin123 |
    And I click on continue button
    Then Page should be redirected to the dashboard

  @regression
  Scenario Outline: Login to HRM with valid credentials using scenario outline
    When And i verify the Login text
    And I also verify the logo
    And I enter the details as "<username>" and "<password>"
    And I click on continue button
    Then Page should be redirected to the dashboard

    Examples:
    |username|password|
    |admin |password1 |



