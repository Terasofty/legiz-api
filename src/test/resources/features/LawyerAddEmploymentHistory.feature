Feature: Lawyers add employment history
  Scenario: As a Lawyer I can add a list of cases that I have done
    Given I am a Lawyer in the Profile page
    When I click on the "Edit" button
    Then I should see the "Add Employment History" form