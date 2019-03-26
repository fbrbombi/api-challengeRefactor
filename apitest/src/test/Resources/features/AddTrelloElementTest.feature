Feature: Add Element to Trello
  as a user of Trello, i want to add some elements to the board

  Background:
    Given the user is member of a board

  Scenario: Get id of the board
    When the user send a request for get the id boards with his api_key and token
    Then The Trello API should responds only with the id boards and its names

  Scenario: Add a list to the board
    And  knows the position where the list will be created
    When the user creates a list called "QA Fabio"
    Then A new list called "QA Fabio" will displayed

  Scenario Outline: Add card to the list
    And the user wants to add the new card to the "<list>"
    When the user send a request for post the card with name "TestCard"
    Then The Trello API should responds only with the "TestCard" of the specific list
    Examples:
      | list        |
      | TODO        |
      | IN PROGRESS |
      | DONE        |

  Scenario Outline: Add member to the card
    And the user has created a "TestCard" card on the "<list>"
    And the user wants to add a new member to the card
    When the user send a request for add a new member
    Then The Trello API should responds adding a new member to the card
    Examples:
      | list        |
      | TODO        |
      | IN PROGRESS |
      | DONE        |

  Scenario Outline: Add a comment to the card
    And the user has created a "TestCard" card on the "<list>"
    And the user wants to add a new comment to the card
    When the user send a request for add a comment
    Then The Trello API should responds adding a new comment to the card
    Examples:
      | list        |
      | TODO        |
      | IN PROGRESS |
      | DONE        |

