package controllers;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static controllers.Strings.MOVECARD;

public class MoveCard {
    Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    Map<String, String> fields = new HashMap<>();

    public MoveCard(String newIdList, String idCard) {
        fields = Auth.getInstance().getAuthParams();
        putParams("idList", newIdList);
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(fields);
        //link = MOVECARD + idCard + "?idList=" + newIdList;
        link = MOVECARD + idCard;
        System.out.println(link);
        response = requestSpecification.when().put(link);

    }

    public void putParams(String key, String data) {
        fields.put(key, data);
    }

}
