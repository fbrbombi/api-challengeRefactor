Feature: Add Element to Trello
  as a user of Trello, i want to add some elements to the board

  Background:
    Given the user is member of a board

  Scenario: Get id of the board
    When the user send a petition for get the id boards with his api_key and token
    Then The Trello API should responds only with the id boards and its names

  Scenario: Add a list to the board
    And  knows the position where the list will be created
    When the user creates a list called "QA Fabio"
    Then A new list called "QA Fabio" will displayed

  Scenario Outline: Add card to the list
    And the user has created "QA Fabio" list
    And the user wants to add the new card to the "<list>"
    When the user send a petition for post the card with name "TestCard"
    Then The Trello API should responds only with the "TestCard" of the specific list
    Examples:
      | list        |
      | TODO        |
      | IN PROGRESS |
      | QA Fabio    |
      | DONE        |

  Scenario Outline: Add member to the list
    And the user has created "QA Fabio" list
    And the user has created a "TestCard" card on the "<list>"
    And the user wants to add a new member to the "TestCard"
    When the user send a petition for add a new member
    Then The Trello API should responds adding a new member to the card
    Examples:
      | list        |
      | TODO        |
      | IN PROGRESS |
      | QA Fabio    |
      | DONE        |

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



