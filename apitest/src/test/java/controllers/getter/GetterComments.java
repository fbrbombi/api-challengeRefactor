package controllers.getter;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import controllers.Objects.Comment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static controllers.Strings.ADDCARD;

public class GetterComments {
    private Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    Comment Comment;

    public GetterComments(String id) {
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(Auth.getInstance().getAuthParams());
        link = ADDCARD + id + "?fields=comments";
        response = requestSpecification.when().get(link);
        Comment = gson.fromJson(response.body().asString(), Comment.class);
    }
    public String getComment() {
        return Comment.getId();
    }
    public Response getResponse() {
        return response;
    }
}
