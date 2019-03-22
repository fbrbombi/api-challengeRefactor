package steps;


import controllers.DeleteCard;
import controllers.MoveCard;
import controllers.Objects.Card;
import controllers.Objects.Comment;
import controllers.Objects.Members;
import controllers.generator.CardGeneratorFromJSON;
import controllers.generator.CommentGenerator;
import controllers.generator.ListGenerator;
import controllers.generator.MemberGenerator;
import controllers.getter.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;


public class APIGetTest {
    @Steps
    private static GetterBoard getBoard;
    private static GetterMembers members;
    private static MemberGenerator member;
    private static GetterList y;
    private static String idList;
    private static String idCard;
    private static CardGeneratorFromJSON card;
    private static GetterCard card2;
    public static Card testCard;
    public static String id;
    private static CommentGenerator comment;
    private static MoveCard move;
    private static ListGenerator newlist;
    private static DeleteCard delete;
    private static String newname = "Q/AFABIO";
    private static Response response;
    // String board="Prueba";
    private String board = "API Challenge 19";

    @Given("^the user is member of a board$")
    public void theUserIsMemberOfABoard() {

        getBoard = new GetterBoard();
    }

    @When("^the user send a petition for get the id boards with his api_key and token$")
    public void theUserSendAPetitionForGetTheIdBoardWithHisApi_keyAndToken() {
        id = getBoard.validate(board);
    }

    @Then("^The Trello API should responds only with the id boards and its names$")
    public void theTrelloAPIShouldRespondsOnlyWithTheIdBoardAndItsNames() {
        response = getBoard.getResponse();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(200, response.getStatusCode());
        JsonPath jsonresponse = response.jsonPath();
        String id = jsonresponse.get("id").toString();
        System.out.println(id);
        Assert.assertNotNull(id);
    }

    @Given("^the user wants to know the list on the board using a ID board$")
    public void theUserWantsToKnowTheListOnTheBoardUsingAIDBoard() {
        theUserIsMemberOfABoard();
        theUserSendAPetitionForGetTheIdBoardWithHisApi_keyAndToken();

    }

    @When("^the user send a petition for get the list with his api_key and token$")
    public void theUserSendAPetitionForGetTheListWithHisApi_keyAndToken() {
        y = new GetterList(id);
    }

    @Then("^The Trello API should responds only with the list of the specific board$")
    public void theTrelloAPIShouldRespondsOnlyWithTheListOfTheSpecificBoard() {
        System.out.println("The list of " + board + " is");
        y.validate();
        response = y.getResponse();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(200, response.getStatusCode());
        JsonPath jsonresponse = response.jsonPath();
        String id = jsonresponse.get("id").toString();
        System.out.println(id);
        Assert.assertNotNull(id);
    }

    @Given("^the user wants to add the new card to the TODO list  with data$")
    public void theUserWantsToAddTheNewCardToTheTODOListWithData() {

        idList = y.getID("TODO");
        System.out.println(idList);
    }

    @When("^the user send a petition for post the card$")
    public void theUserSendAPetitionForPostTheCard() {
        card = new CardGeneratorFromJSON("ajiaco", idList);
    }

    @Then("^The Trello API should responds only with the card of the specific list$")
    public void theTrelloAPIShouldRespondsOnlyWithTheCardOfTheSpecificList() {
        System.out.println("Card Added");
        GetterCard postedcard=new GetterCard("ajiaco2",idList);
        String valid=postedcard.getCardID("ajiaco");
        Assert.assertNotNull(valid);
    }

    //
    @Given("^the user added a card$")
    public void theUserAddedACard() {
        card2 = new GetterCard("ajiaco", idList);
    }

    @When("^the user wants to verify if the card was created$")
    public void theUserWantsToVerifyIfTheCardWasCreated() {
        System.out.println(card2.verifyCard("ajiaco"));
    }

    @Then("^The Trello API should responds only with the name of the card with its ID$")
    public void theTrelloAPIShouldRespondsOnlyWithTheNameOfTheCardWithItsID() {
    }

    @When("^the user wants to add a new member$")
    public void theUserWantsToAddANewMember() {
        idCard = card2.getCardID("ajiaco");
        members = new GetterMembers(id);
       // System.out.println("ajiaco" + members.getMemberID(1));
        addMember(2);
    }

    @Then("^The Trello API should responds only with succesful message$")
    public void theTrelloAPIShouldRespondsOnlyWithSuccesfulMessage() {

    }


    @When("^the user wants to comment a card$")
    public void theUserWantsToCommentACard() {
        addComment("superajiaco");
    }

    @When("^the user wants to move a card to IN PROGRESS list$")
    public void theUserWantsToMoveACardToINPROGRESSList() {
        move("IN PROGRESS");
    }

    ///
    @When("^the user wants to add another member$")
    public void theUserWantsToAddAnotherMember() {
        addMember(4);
    }

    @When("^the user wants to add another comment when the card is IN PROGRESS list$")
    public void theUserWantsToAddAnotherCommentWhenTheCardIsINPROGRESSList() {
        addComment("HIPERAJIACO");
    }

    private void addComment(String text) {
        comment = new CommentGenerator(text, idCard);
    }

    private void addMember(int id) {
        member = new MemberGenerator(members.getMemberID(id), idCard);

    }

    private void move(String list) {
        String inprogress = y.getID(list);
        move = new MoveCard(inprogress, idCard);
    }

    @When("^the user wants to move a card to QA list$")
    public void theUserWantsToMoveACardToQAList() {
        y = new GetterList(id);
        move("Q/AFABIO");
    }

    @When("^the user wants to add another member in QA$")
    public void theUserWantsToAddAnotherMemberInQA() {
        addMember(1);
    }

    @When("^the user wants to add another comment when the card is QA list$")
    public void theUserWantsToAddAnotherCommentWhenTheCardIsQAList() {
        addComment("ESTOY EN QA");
    }


    @When("^the user creates a list$")
    public void theUserCreatesAList() {
        String original = y.getPos("DONE");
        int pos = Integer.parseInt(original) - 1;
        System.out.println(pos);
        newlist = new ListGenerator(newname, id, String.valueOf(pos));
    }

    @When("^the user wants to move a card to DONE list$")
    public void theUserWantsToMoveACardToDONEList() {
        move("DONE");
    }

    @When("^the user wants to add another member in DONE$")
    public void theUserWantsToAddAnotherMemberInDONE() {
        addMember(7);
    }

    @When("^the user wants to add another comment when the card is DONE list$")
    public void theUserWantsToAddAnotherCommentWhenTheCardIsDONEList() {
        addComment("I am in DONE");
    }

    @When("^the user wants to delete the  card$")
    public void theUserWantsToDeleteTheCard() {
        delete = new DeleteCard(idCard);
    }

    @Then("^A new list will displayed$")
    public void aNewListWillDisplayed() {
      /*  y = new GetterList(id);
        response = y.getResponse();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(200, response.getStatusCode());
        id = y.getID("Q/AFABIO");
        Assert.assertNotEquals("Not found", id);*/
        /*GetterList getList = new GetterList(id);
        response = getList.getResponse();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(200, response.getStatusCode());
        id = getList.getID("Q/AFABIO");
        Assert.assertNotEquals("Not found", id);*/
    }

    @Then("^The Trello API should responds only with a succesful message$")
    public void theTrelloAPIShouldRespondsOnlyWithASuccesfulMessage() {
    }

    @Then("^The card must have a new member$")
    public void theCardMustHaveANewMember() {
     Members[] valid=members.getMember();
        System.out.println("tamaño 1 "+valid.length);
        Assert.assertNotNull(valid);

    }

    @Then("^A new comment is posted$")
    public void aNewCommentIsPosted() {
        GetterComments getcomment=new GetterComments(idCard);
        String valid=getcomment.getComment();
        Assert.assertNotNull(valid);

    }

    @Then("^the card is IN PROGRESS list$")
    public void theCardIsINPROGRESSList() {
        GetterCardPosition position=new GetterCardPosition(idCard);
        Assert.assertEquals("IN PROGRESS",position.getCardList());
    }


    @Then("^A new member is added$")
    public void aNewMemberIsAdded() {
        GetterMembers members2=new GetterMembers(id);
        Members[] valid=members2.getMember();
        Assert.assertNotNull(valid);
    }

    @Then("^the card is QA list$")
    public void theCardIsQAList() {
        GetterCardPosition position=new GetterCardPosition(idCard);
        Assert.assertEquals("Q/AFABIO",position.getCardList());
    }

    @Then("^the card is DONE list$")
    public void theCardIsDONEList() {
        GetterCardPosition position=new GetterCardPosition(idCard);
        Assert.assertEquals("DONE",position.getCardList());
    }
}
