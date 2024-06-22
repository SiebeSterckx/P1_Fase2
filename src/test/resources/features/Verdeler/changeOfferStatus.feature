Feature: Change offer status

  As a distributor
  I want to change the status of an offer
  So that I can accept or reject an offer

  # Persona
  # Bob - Distributor

  Rule: Change offer status

    Scenario: Change offer status to "Accepted"
      Given I am logged in as an distributor
      And I am on the offer page of my company
      When I change the status to "Accepted"
      Then the offer status should be "Accepted"

    Scenario: Change offer status to "Rejected"
      Given I am logged in as an distributor
      And I am on the offer page of my company
      When I change the status to "Rejected"
      Then the offer status should be "Rejected"

    Scenario: Change offer status from "Rejected" to "Accepted"
      Given I am logged in as an distributor
      And I am on the offer page of my company
      When I try to change the status to "Accepted"
      Then the offer status should stay "Rejected"