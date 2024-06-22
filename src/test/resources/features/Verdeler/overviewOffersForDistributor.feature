Feature: Overview offers for distributor

  As a Distributor
  I want to be able to see an overview of all offers for my company
  So that I can see which offers are still open and which are already closedµ

  # Persona
  # Bob - Distributor

  Rule: The overview must contain offers of the logged in distributor

    Scenario: An overview of all the distributor's offers is shown when there are offers
      Given I am logged in as an Verdeler
      And there are offers
      When I go to the overview of my company
      Then I should see all the offers of my company