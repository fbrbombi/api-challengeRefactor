Feature: Move Element on Trello
  as a user of Trello, i want to move some elements on the board

  Background:
    Given the user is member of a board

  Scenario Outline: Move a card to another list
    And the user has created a "TestCard" card on the "<list>"
    And the user wants to move the card to the "<nextList>"
    When the user send a petition for move the card
    Then The Trello API should responds moving the card
    Examples:
      | list        | nextList    |
      | TODO        | IN PROGRESS |
      | IN PROGRESS | QA Fabio    |
      | QA Fabio    | DONE        |
      | DONE        | TODO        |