package steps;

import com.google.gson.Gson;
import controllers.Objects.Board;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import static helpers.TrelloActions.getBoardsInfo;
import static utils.SeacherAttributes.findIdByName;

public class TrelloAddTest {
    @Steps

    private Gson gson = new Gson();
    private String board;

    @Given("^the user is member of a board$")
    public void theUserIsMemberOfABoard() {
        board = "API Challenge 19";
    }

    @When("^the user send a petition for get the id boards with his api_key and token$")
    public void theUserSendAPetitionForGetTheIdBoardWithHisApi_keyAndToken() {
        Board[] boards = gson.fromJson(getBoardsInfo(), Board[].class);
        Serenity.setSessionVariable("Boards").to(boards);

    }

    @Then("^The Trello API should responds only with the id boards and its names$")
    public void theTrelloAPIShouldRespondsOnlyWithTheIdBoardAndItsNames() {
        String id = findIdByName(Serenity.sessionVariableCalled("Boards"), board);
        Assert.assertNotEquals("Not found", id);
    }

    @When("^the user creates a list$")
    public void theUserCreatesAList() {

    }
}
