Feature: Add Element to Trello
  as a user of Trello, i want to add some elements to the board

  Background:
    Given the user is member of a board

  Scenario: Get id of the board
    When the user send a petition for get the id boards with his api_key and token
    Then The Trello API should responds only with the id boards and its names

  Scenario: Add a list to the board
    When the user creates a list
    Then A new list will displayed

  Scenario: Add card to the list
    Given the user wants to add the new card to the list  with data:
      | TODO        |
      | IN PROGRESS |
      | QA          |
      | DONE        |
    When the user send a petition for post the card
    Then The Trello API should responds only with the card of the specific list

  Scenario: Add member to the card
    Given the user added a card
    When the user wants to add a new member
    Then The card must have a new member

  Scenario: Add a comment to the card
    Given the user added a card
    When the user wants to comment a card
    Then A new comment is posted


  Scenario: Add member to the card when it stay IN PROGRESS list
    Given the user added a card
    When the user wants to add another member
    Then A new member is added

  Scenario: Add a comment to the card when it stay IN PROGRESS list
    Given the user added a card
    When the user wants to add another comment when the card is IN PROGRESS list
    Then A new comment is posted

  Scenario: Add member to the card when it stay QA list
    Given the user added a card
    When the user wants to add another member in QA
    Then A new member is added

  Scenario: Add a comment to the card when it stay QA list
    Given the user added a card
    When the user wants to add another comment when the card is QA list
    Then A new comment is posted

  Scenario: Add member to the card when it stay DONE list
    Given the user added a card
    When the user wants to add another member in DONE
    Then A new member is added

  Scenario: Add a comment to the card when it stay DONE list
    Given the user added a card
    When the user wants to add another comment when the card is DONE list
    Then A new comment is posted



