package controllers.generator;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static controllers.Strings.CARDUPDATE;

public class CommentGenerator {
    Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    Map<String, String> fields = new HashMap<>();

    public CommentGenerator(String text, String idCard) {
        fields = Auth.getInstance().getAuthParams();
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(fields);
        link = CARDUPDATE + idCard + "/actions/comments?text=" + text;
        response = requestSpecification.when().post(link);
    }


}
