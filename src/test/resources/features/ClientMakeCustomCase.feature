Feature: Client Make a Custom Case
  As a Client
  I want to add a Custom Case
  Background:
    Given The Endpoint "http://localhost:8080/api/cases" is available
    Given I am an Authorized User
    Scenario: Add Custom Case
      When  A Custom Case Request is sent with values "hyper" "john" "custom" "Test Test Test"
      Then A Response with 200 is received
      And A Custom Case Resource is created