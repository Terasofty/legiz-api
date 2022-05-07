Feature: Login Action
  Scenario: Successful Login with Valid Credentials
    Given User is on Home Page
    When User Navigate to LogIn Page
    And User enters "hyper" and "12345"
    Then Message displayed Login Successfully
    Scenario: Successful Log out
      When User Log out from the Application
      Then Message displayed Log out Successfully