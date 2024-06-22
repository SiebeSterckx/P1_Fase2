Feature: Login
  As a person
  I want to be able to login
  In order that I can be identified

#Personas
#Lars – User


  Rule: The email of the user must be provided

    Scenario: The user is able to login when his email is provided.
      Given Lars has filled in his email in the email field
      When Lars chooses to login
      Then Lars should be able to login


    Scenario: An error message is given when the user’s email isn’t provided
      Given Lars has not filled in his email in the email field
      When Lars chooses to login
      Then Lars should be given an error message that his email must be filled in


  Rule: The password must be provided


    Scenario: A user is logged in when he provides his password
      Given Lars has provided the his password
      When Lars chooses to login
      Then Lars should be able to login


    Scenario: An error message is given when Lars logs in with no password
      Given Lars has provided the no password
      When Lars chooses to login
      Then Lars should be given an error message that a password must be provided


  Rule: The password must be correct


    Scenario: A user is logged in when he provides the right password
      Given Lars has provided the right password
      When Lars chooses to login
      Then Lars should be able to login


    Scenario: An error message is given when Lars logs in with the wrong password
      Given Lars has provided the wrong password
      When Lars chooses to login
      Then Lars should be given an error message that his credentials are wrong


  Rule: The user must be registered


    Scenario: A user is logged in when he is registered
      Given Lars is registered
      When Lars chooses to login
      Then Lars should be able to login


    Scenario: An error message is given when Lars logs in but isn’t registered
      Given Lars is not registered
      When Lars chooses to login
      Then Lars should be given an error message that he isn’t registered