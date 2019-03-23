Feature: Basic CRUD board
  As a user in trello, i want to execute CRUD task in a specific bar


  Scenario: Get list of a board
    Given the user wants to know the list on the board using a ID board
    When the user send a petition for get the list with his api_key and token
    Then The Trello API should responds only with the list of the specific board


