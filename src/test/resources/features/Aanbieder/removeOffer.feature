Feature: Remove offer

  As an Provider
  I want to be able to remove an offer when it is no longer relevant
  So that I can keep my offer overview up to date

  # Persona
  # Jef - Provider

  Rule: Overview must contain at least 1 offer

    Scenario: An offer is removed from the overview when there is 1 offer left
        Given there is 1 offer in the overview
        And the offer is await
        When Jef removes the offer
        Then the offer is removed from the overview

    Scenario: An error is shown when an offer is removed from the overview when there are no offers left
        Given there are no offers in the overview
        And the offer is await
        When Jef removes the offer
        Then an error is shown that there must be at least 1 item in the overview

    Rule: The offer must be unaccepted by the Distributor

      Scenario: An offer is removed from the overview when it is await by the Distributor
        Given the offer is await by the Distributor
        When Jef removes the offer
        Then the offer is removed from the overview

      Scenario: An error is shown when an offer is removed from the overview when it is accepted by the Distributor
        Given the offer is accepted by the Distributor
        When Jef removes the offer
        Then an error is shown that the offer is already accepted by the Distributor