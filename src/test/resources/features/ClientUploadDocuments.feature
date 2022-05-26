Feature: The client uploads documents
  Scenario: As a Client I can upload documents for my legal case
    Given the client is in the legal case view
    When the client uploads a document
    Then the client should see the document in the documents list