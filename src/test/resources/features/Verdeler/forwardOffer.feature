Feature: Pass along offer

  As a distributor
  I want to forward an offer to another distributor
  So that I don't have that offer anymore

  # Persona
  # Bob - Distributor

  Rule: Forward an offer to another distributor when status is "pending"

    Scenario: Forward an offer that is "pending" to another distributor
      Given I am logged in as an distributor
      And I am on the offer page of my company
      When I forward an offer that is "pending"
      Then the offer is forwarded to the other distributor

    Scenario: Try to forward an offer that is "Accepted" to another distributor
      Given I am logged in as an distributor
      And I am on the offer page of my company
      When I try to forward an offer that is "Accepted"
      Then an error message is shown that the offer can't be forwarded
      And the offer is not forwarded to the other distributor

    Scenario: Try to forward an offer that is "Rejected" to another distributor
      Given I am logged in as an distributor
      And I am on the offer page of my company
      When I try to forward an offer that is "Rejected"
      Then an error message is shown that the offer can't be forwarded
      And the offer is not forwarded to the other distributor