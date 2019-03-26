Feature: Delete Element on Trello
  as a user of Trello, i want to delete some elements on the board

  Background:
    Given the user is member of a board

  Scenario Outline: Delete a card on the list
    And the user has created a "TestCard" card on the "<list>"
    And the user wants to delete the card
    When the user send a request to delete the card
    Then The Trello API  responds erasing the card
    Examples:
      | list        |
      | TODO        |
      | IN PROGRESS |
      | DONE        |