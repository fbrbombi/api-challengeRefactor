package controllers;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import controllers.Objects.TrelloList;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static controllers.Strings.ADDCARD;


public class DeleteCard {
    Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    TrelloList[] trelloLists;
    Map<String, String> fields = new HashMap<>();

    public DeleteCard(String idCard) {
        fields = Auth.getInstance().getAuthParams();

        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(fields);
        link = ADDCARD + idCard;
        response = requestSpecification.when().delete(link);
    }

    public void putParams(String key, String data) {
        fields.put(key, data);
    }
}
