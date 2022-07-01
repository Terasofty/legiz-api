Feature: Client Make an API Custom Case
  Background:
    Given the client credentials "hyper" "12345"
  Scenario: Add Custom Case
    Given the client click login button
    When the client fills the login form
    Then the client navigates to Custom Cases page
    Given the following case "john" "Представительство Представительство"
    When the client clicks on the Add Custom Case button