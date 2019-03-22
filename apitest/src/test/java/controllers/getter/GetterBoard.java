package controllers.getter;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import controllers.Objects.Board;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static controllers.Strings.BOARD;

public class GetterBoard {
    private String BoardName;
    Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    Board[] Boards;

    public GetterBoard() {
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(Auth.getInstance().getAuthParams());
        link = BOARD + "key=" + Auth.getInstance().getKey() + "&token=" + Auth.getInstance().getToken();
        response = requestSpecification.when().get(link);
        System.out.println(response.body().asString());
        Boards = gson.fromJson(response.body().asString(), Board[].class);

    }

    public String getBoards() {
        return Boards[1].getName();
    }

    public String validate(String name) {
        int i;
        for (i = 0; i < (Boards.length); i++) {
            if (Boards[i].getName().equals(name)) {
                return Boards[i].getId();
            }
        }
        return "Not found";
    }
    public Response getResponse() {
           return response;
    }
}
