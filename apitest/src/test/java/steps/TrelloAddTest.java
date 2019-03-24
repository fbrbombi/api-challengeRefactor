package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.APIManager;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;

import static utils.SearcherAttributes.*;

public class TrelloAddTest {

    private String board;
    private APIManager apiManager;

    @Given("^the user is member of a board$")
    public void theUserIsMemberOfABoard() {
        //board = "API Challenge 19";
        board = "Prueba";
        apiManager = new APIManager();
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
        int pos = Integer.parseInt(Serenity.sessionVariableCalled("listPosition")) - 1;
        String idBoard = Serenity.sessionVariableCalled("idBoard");
        apiManager.postNewList(name, idBoard, String.valueOf(pos));
    }


    @Then("^A new list called \"([^\"]*)\" will displayed$")
    public void aNewListCalledWillDisplayed(String name) {
        String idBoard = Serenity.sessionVariableCalled("idBoard");
        boolean validation = isThereAListWithName(apiManager.getTrelloList(idBoard), name);
        Assert.assertTrue(validation);
    }

    @And("^the user has created \"([^\"]*)\" list$")
    public void theUserHasCreatedList(String listName) {
        String idBoard = apiManager.getBoardId(board);
        Serenity.setSessionVariable("idBoard").to(idBoard);

        if (!isThereAListWithName(apiManager.getTrelloList(idBoard), listName)) {
            String listPosition = apiManager.getListPosition(apiManager.getTrelloList(idBoard), "DONE");
            int pos = Integer.parseInt(listPosition) - 1;
            apiManager.postNewList(listName, idBoard, String.valueOf(pos));
        }
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
        boolean validation = isThisMemberPartOfTheCard(idMember, apiManager.getListOfMembersCard(idCard));
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
        String idCard = Serenity.sessionVariableCalled("idCard");
        System.out.println(apiManager.getComments(idCard)[0].getComments());


    }
}
