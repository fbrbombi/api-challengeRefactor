package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.APIManager;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;

import static utils.SearcherAttributes.*;

public class TrelloSteps {

    private String board;
    private APIManager apiManager;

    @Given("^the user is member of a board$")
    public void theUserIsMemberOfABoard() {
        //board = "API Challenge 19";
        board = "Prueba";
        apiManager = new APIManager();
        Serenity.setSessionVariable("idBoard").to(apiManager.getBoardId(board));


    }

    @When("^the user send a petition for get the id boards with his api_key and token$")
    public void theUserSendAPetitionForGetTheIdBoardWithHisApi_keyAndToken() {
        Serenity.setSessionVariable("boards").to(apiManager.getBoards());
    }

    @Then("^The Trello API should responds only with the id boards and its names$")
    public void theTrelloAPIShouldRespondsOnlyWithTheIdBoardAndItsNames() {
        String id = findIdByName(Serenity.sessionVariableCalled("boards"), board);
        Serenity.setSessionVariable("idBoard").to(id);
        Assert.assertNotEquals("Not found", id);
    }

    @And("^knows the position where the list will be created$")
    public void knowsThePositionWhereTheListWillBeCreated() {
        String id = apiManager.getBoardId(board);
        String listPosition = apiManager.getListPosition(apiManager.getTrelloList(id), "DONE");
        Serenity.setSessionVariable("listPosition").to(listPosition);
        Serenity.setSessionVariable("idBoard").to(id);

    }

    @When("^the user creates a list called \"([^\"]*)\"$")
    public void theUserCreatesAListCalled(String name) {
        int pos = (int) Float.parseFloat(Serenity.sessionVariableCalled("listPosition")) - 1;
        String idBoard = Serenity.sessionVariableCalled("idBoard");
        apiManager.postNewList(name, idBoard, String.valueOf(pos));
    }


    @Then("^A new list called \"([^\"]*)\" will displayed$")
    public void aNewListCalledWillDisplayed(String name) {
        String idBoard = Serenity.sessionVariableCalled("idBoard");
        boolean validation = isThereAListWithName(apiManager.getTrelloList(idBoard), name);
        Assert.assertTrue(validation);
    }


    @And("^the user wants to add the new card to the \"([^\"]*)\"$")
    public void theUserWantsToAddTheNewCardToThe(String listName) {
        String idBoard = Serenity.sessionVariableCalled("idBoard");
        String idList = apiManager.getListId(listName, idBoard);
        Serenity.setSessionVariable("idList").to(idList);
    }

    @When("^the user send a petition for post the card with name \"([^\"]*)\"$")
    public void theUserSendAPetitionForPostTheCardWithName(String cardName) {
        String idList = Serenity.sessionVariableCalled("idList");
        apiManager.postNewCard(cardName, idList);
    }


    @Then("^The Trello API should responds only with the \"([^\"]*)\" of the specific list$")
    public void theTrelloAPIShouldRespondsOnlyWithTheOfTheSpecificList(String cardName) {
        String idList = Serenity.sessionVariableCalled("idList");
        String validation = findByName(apiManager.getCardsFromList(idList), cardName);
        Assert.assertEquals("Found", validation);
    }


    @And("^the user has created a \"([^\"]*)\" card on the \"([^\"]*)\"$")
    public void theUserHasCreatedACardOnThe(String cardName, String listName) {
        String idBoard = Serenity.sessionVariableCalled("idBoard");
        String idList = apiManager.getListId(listName, idBoard);
        Serenity.setSessionVariable("idList").to(idList);
        Serenity.setSessionVariable("idBoard").to(idBoard);
        Serenity.setSessionVariable("cardName").to(cardName);
        apiManager.postNewCard(cardName, idList);
    }

    @And("^the user wants to add a new member to the \"([^\"]*)\"$")
    public void theUserWantsToAddANewMemberToThe(String cardName) {
        String idList = Serenity.sessionVariableCalled("idList");
        String idCard = apiManager.getCardId(idList, cardName);
        Serenity.setSessionVariable("idCard").to(idCard);

    }

    @When("^the user send a petition for add a new member$")
    public void theUserSendAPetitionForAddANewMember() {
        String idCard = Serenity.sessionVariableCalled("idCard");
        String idBoard = Serenity.sessionVariableCalled("idBoard");
        String idMember = apiManager.getIdMember(idBoard);
        apiManager.addNewMember(idMember, idCard);
        Serenity.setSessionVariable("idMember").to(idMember);
    }

    @Then("^The Trello API should responds adding a new member to the card$")
    public void theTrelloAPIShouldRespondsAddingANewMemberToTheCard() {
        String idCard = Serenity.sessionVariableCalled("idCard");
        String idMember = Serenity.sessionVariableCalled("idMember");
        boolean validation = isThisIdPartOfThisList(idMember, apiManager.getListOfMembersCard(idCard));
        Assert.assertTrue(validation);

    }

    @And("^the user wants to add a new comment to the \"([^\"]*)\"$")
    public void theUserWantsToAddANewCommentToThe(String cardName) {
        String idList = Serenity.sessionVariableCalled("idList");
        String idCard = apiManager.getCardId(idList, cardName);
        Serenity.setSessionVariable("idCard").to(idCard);
    }

    @When("^the user send a petition for add a comment$")
    public void theUserSendAPetitionForAddAComment() {
        String idCard = Serenity.sessionVariableCalled("idCard");
        String text = "ajiaco";
        apiManager.postNewComment(text, idCard);
    }

    @Then("^The Trello API should responds adding a new comment to the card$")
    public void theTrelloAPIShouldRespondsAddingANewCommentToTheCard() {
        //      String idCard = Serenity.sessionVariableCalled("idCard");
//        System.out.println(apiManager.getComments(idCard)[0].getComment());


    }

    @And("^the user wants to move the card to the \"([^\"]*)\"$")
    public void theUserWantsToMoveTheCardToThe(String nextList) {
        String idBoard = Serenity.sessionVariableCalled("idBoard");
        String idList = Serenity.sessionVariableCalled("idList");
        String cardName = Serenity.sessionVariableCalled("cardName");
        String idCard = apiManager.getCardId(idList, cardName);
        String idNextList = apiManager.getListId(nextList, idBoard);
        Serenity.setSessionVariable("idCard").to(idCard);
        Serenity.setSessionVariable("idNextList").to(idNextList);

    }

    @When("^the user send a petition for move the card$")
    public void theUserSendAPetitionForMoveTheCard() {
        String idCard = Serenity.sessionVariableCalled("idCard");
        String idNextList = Serenity.sessionVariableCalled("idNextList");
        apiManager.moveACard(idNextList, idCard);
    }

    @Then("^The Trello API should responds moving the card$")
    public void theTrelloAPIShouldRespondsMovingTheCard() {
        String idCard = Serenity.sessionVariableCalled("idCard");
        String idNextList = Serenity.sessionVariableCalled("idNextList");
        boolean validation = isThisIdPartOfThisList(idCard, apiManager.getCardsFromList(idNextList));
        Assert.assertTrue(validation);

    }

    @And("^the user wants to delete the \"([^\"]*)\"$")
    public void theUserWantsToDeleteThe(String cardName) {
        String idList = Serenity.sessionVariableCalled("idList");
        String idCard = apiManager.getCardId(idList, cardName);
        Serenity.setSessionVariable("idCard").to(idCard);
    }

    @When("^the user send a petition for delete the card$")
    public void theUserSendAPetitionForDeleteTheCard() {
        String idCard = Serenity.sessionVariableCalled("idCard");
        apiManager.deleteCard(idCard);
    }

    @Then("^The Trello API should responds erasing the card$")
    public void theTrelloAPIShouldRespondsErasingTheCard() {
        String idCard = Serenity.sessionVariableCalled("idCard");
        String idList = Serenity.sessionVariableCalled("idList");
        boolean validation = isThisIdPartOfThisList(idCard, apiManager.getCardsFromList(idList));
        Assert.assertFalse(validation);
    }
}
