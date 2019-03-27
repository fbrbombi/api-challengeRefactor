package steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.TestController;
import org.junit.Assert;

public class TrelloSteps {

    private TestController testController = new TestController();

    @After
    public void after() {
        testController.deleteCard();
    }

    @Given("^the user is member of a board$")
    public void theUserIsMemberOfABoard() {
        testController.setBoard("Prueba");
        testController.findBoardId();
    }

    @When("^the user send a request for get the id boards with his api_key and token$")
    public void theUserSendARequestForGetTheIdBoardWithHisApi_keyAndToken() {
        testController.findBoardId();
    }

    @Then("^The Trello API should responds only with the id boards and its names$")
    public void theTrelloAPIShouldRespondsOnlyWithTheIdBoardAndItsNames() {
        Assert.assertNotEquals("the ID of the Board is found", "Not found", testController.getBoardId());
    }

    @And("^knows the position where the list will be created$")
    public void knowsThePositionWhereTheListWillBeCreated() {
        testController.getPositionOfList("DONE");
    }

    @When("^the user creates a list called \"([^\"]*)\"$")
    public void theUserCreatesAListCalled(String name) {
        testController.postNewList(name);
    }


    @Then("^A new list called \"([^\"]*)\" will displayed$")
    public void aNewListCalledWillDisplayed(String name) {

        Assert.assertTrue("Is true when the list is on the board", testController.isTheListCreated(name));
    }

    @And("^the user wants to add the new card to the \"([^\"]*)\"$")
    public void theUserWantsToAddTheNewCardToThe(String listName) {
        testController.findIDList(listName);
    }

    @When("^the user send a request for post the card with name \"([^\"]*)\"$")
    public void theUserSendARequestForPostTheCardWithName(String cardName) {
        testController.postNewCard(cardName);
        testController.setCardName(cardName);
        testController.findCardID();
    }


    @Then("^The Trello API should responds only with the \"([^\"]*)\" of the specific list$")
    public void theTrelloAPIShouldRespondsOnlyWithTheOfTheSpecificList(String cardName) {
        String validation = testController.postCardValidation(cardName);
        Assert.assertEquals("Expected to found a new card on the list", "Found", validation);
    }


    @And("^the user has created a \"([^\"]*)\" card on the \"([^\"]*)\"$")
    public void theUserHasCreatedACardOnThe(String cardName, String listName) {
        testController.findIDList(listName);
        testController.setCardName(cardName);
        testController.postNewCard(cardName);
        testController.findCardID();
    }

    @And("^the user wants to add a new member to the card$")
    public void theUserWantsToAddANewMemberToTheCard() {
        testController.findIDMember();
    }

    @When("^the user send a request for add a new member$")
    public void theUserSendARequestForAddANewMember() {
        testController.addNewMember();
    }

    @Then("^The Trello API should responds adding a new member to the card$")
    public void theTrelloAPIShouldRespondsAddingANewMemberToTheCard() {
        boolean validation = testController.isTheMemberAdded();
        Assert.assertTrue("Expects the member is added to the card", validation);

    }


    @And("^the user wants to add a new comment to the card")
    public void theUserWantsToAddANewCommentToTheCard() {
        testController.findCardID();
    }

    @When("^the user send a request for add a comment$")
    public void theUserSendARequestForAddAComment() {
        String text = "ajiaco";
        testController.postAComment(text);
    }

    @Then("^The Trello API should responds adding a new comment to the card$")
    public void theTrelloAPIShouldRespondsAddingANewCommentToTheCard() {
        testController.handleJson();

        //String idCard = Serenity.sessionVariableCalled("idCard");
        //System.out.println(apiManager.getComments(idCard)[0].getComment());
    }

    @And("^the user wants to move the card to the \"([^\"]*)\"$")
    public void theUserWantsToMoveTheCardToThe(String nextList) {
        testController.getNextIdList(nextList);
    }

    @When("^the user send a request for move the card$")
    public void theUserSendARequestForMoveTheCard() {
        testController.moveCard();
    }

    @Then("^The Trello API should responds moving the card$")
    public void theTrelloAPIShouldRespondsMovingTheCard() {
        boolean validation = testController.isTheCardMoved();
        Assert.assertTrue("Expected to find the card in the list", validation);
    }

    @And("^the user wants to delete the card")
    public void theUserWantsToDeleteTheCard() {
        testController.findCardID();
    }

    @When("^the user send a request to delete the card$")
    public void theUserSendARequestToDeleteTheCard() {
        testController.deleteCard();
    }

    @Then("^The Trello API  responds erasing the card$")
    public void theTrelloAPIRespondsErasingTheCard() {
        boolean validation = testController.isTheCardDeleted();
        Assert.assertFalse("Expected that is not here a card", validation);
    }
}
