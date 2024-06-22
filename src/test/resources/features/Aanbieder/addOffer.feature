Feature: add offer

  As an Provider
  I want to be able to add materials
  So that I can offer them to the Distributors

  # Persona
  # Jef - Provider


  Rule: a photo is required

    Scenario: The offer is added when a photo is provided
      Given Jef has provided 25/12/2023 as the latest pickup date
      And Jef has Materialenbank selected as distributor
      When Jef provides a photo of the material
      Then the offer is added to the system


    Scenario: An error message is shown when no photo is provided
      Given Jef has provided 25/12/2023 as the latest pickup date
      And Jef has Materialenbank selected as distributor
      When Jef does not provide a photo of the material
      Then an error message should be shown that a photo must be provided

    Rule: a latest pickup date is required

      Scenario: The offer is added when a latest pickup date is provided
        Given Jef provides a photo of the material
        And Jef has Materialenbank selected as distributor
        When Jef provides 25/12/2023 as the latest pickup date
        Then the offer is added to the system

      Scenario: An error message is shown when no latest pickup date is provided
        Given Jef provides a photo of the material
        And Jef has Materialenbank selected as distributor
        When Jef does not provide a latest pickup date
        Then an error message should be shown that a latest pickup date must be provided

    Rule: a latest pickup date must be in the future

      Scenario: The offer is added when a latest pickup date is in the future
        Given Jef provides a photo of the material
        And Jef has Materialenbank selected as distributor
        When Jef provides 25/12/2023 as the latest pickup date
        Then the offer is added to the system

      Scenario: An error message is shown when a latest pickup date is in the past
        Given Jef provides a photo of the material
        And Jef has Materialenbank selected as distributor
        When Jef provides 25/12/2019 as the latest pickup date
        Then an error message should be shown that the latest pickup date must be in the future

    Rule: one of 3 distributors has to be selected

      Scenario: The offer is added when one of the 3 distributors is selected
        Given Jef provides a photo of the material
        And Jef provides 25/12/2023 as the latest pickup date
        When Jef selects Materialenbank as the distributor
        Then the offer is added to the system