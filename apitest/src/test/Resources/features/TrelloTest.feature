Feature: Basic CRUD board
  As a user in trello, i want to execute CRUD task in a specific bar

  Scenario: Get id of the board
    Given the user is member of a board
    When the user send a petition for get the id boards with his api_key and token
    Then The Trello API should responds only with the id boards and its names

  Scenario: Get list of a board
    Given the user wants to know the list on the board using a ID board
    When the user send a petition for get the list with his api_key and token
    Then The Trello API should responds only with the list of the specific board

  Scenario: Add a list to the board
    Given the user is member of a board
    When the user creates a list
    Then A new list will displayed

  Scenario: Add card to a list
    Given the user wants to add the new card to the TODO list  with data
    When the user send a petition for post the card
    Then The Trello API should responds only with the card of the specific list


  Scenario: Verify if the card was created on TODO list
    Given the user added a card
    When the user wants to verify if the card was created
    Then The Trello API should responds only with the name of the card with its ID

  Scenario: Add member to the card
    Given the user added a card
    When the user wants to add a new member
    Then The card must have a new member

  Scenario: Add a comment to the card
    Given the user added a card
    When the user wants to comment a card
    Then A new comment is posted

  Scenario: Move the card to IN PROGRESS
    Given the user added a card
    When the user wants to move a card to IN PROGRESS list
    Then the card is IN PROGRESS list

  Scenario: Add member to the card when it stay IN PROGRESS list
    Given the user added a card
    When the user wants to add another member
    Then A new member is added

  Scenario: Add a comment to the card when it stay IN PROGRESS list
    Given the user added a card
    When the user wants to add another comment when the card is IN PROGRESS list
    Then A new comment is posted

  Scenario: Move the card to QA
    Given the user added a card
    When the user wants to move a card to QA list
    Then the card is QA list

  Scenario: Add member to the card when it stay QA list
    Given the user added a card
    When the user wants to add another member in QA
    Then A new member is added

  Scenario: Add a comment to the card when it stay QA list
    Given the user added a card
    When the user wants to add another comment when the card is QA list
    Then A new comment is posted

  Scenario: Move the card to DONE
    Given the user added a card
    When the user wants to move a card to DONE list
    Then the card is DONE list

  Scenario: Add member to the card when it stay DONE list
    Given the user added a card
    When the user wants to add another member in DONE
    Then A new member is added

  Scenario: Add a comment to the card when it stay DONE list
    Given the user added a card
    When the user wants to add another comment when the card is DONE list
    Then A new comment is posted

  Scenario: Delete a card
    Given the user added a card
    When the user wants to delete the  card
    Then The Trello API should responds only with succesful message