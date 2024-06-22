Feature: Overview my offers

  As an Provider
  I want to be able to get an overview of my offers
  So that I can see which offers are still open and which are closed

  # Persona
  # Jef - Provider


  Rule: The overview must contain my own offers

    Scenario: An overview of all my offers is shown when there are offers
      Given I am logged in as an Aanbieder
      And there are offers
      When I go to the overview of my offers
      Then I see all my offers

