package controllers.getter;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import controllers.Objects.CardList;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static controllers.Strings.ADDCARD;

public class GetterCardPosition {
    private String BoardName;
    Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    Map<String, String> fields = new HashMap<>();
    CardList card;

    public GetterCardPosition(String idCard) {
        fields = Auth.getInstance().getAuthParams();
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(fields);
        link = ADDCARD + idCard + "/list?fields=name";
        response = requestSpecification.when().get(link);
        card = gson.fromJson(response.body().asString(), CardList.class);
    }

    public void putParams(String key, String data) {
        fields.put(key, data);
    }


    public String getCardList() {
        return card.getName();
    }

}
