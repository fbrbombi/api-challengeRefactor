package controllers.getter;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import controllers.Objects.Card;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static controllers.Strings.GETCARD;

public class GetterCard {
    private String BoardName;
    Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    Map<String, String> fields = new HashMap<>();
    Card[] card;

    public GetterCard(String name, String idlist) {
        fields = Auth.getInstance().getAuthParams();
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(fields);
        link = GETCARD + idlist + "/cards?fields=name,idMembers";
        response = requestSpecification.when().get(link);
        card = gson.fromJson(response.body().asString(), Card[].class);
    }

    public void putParams(String key, String data) {
        fields.put(key, data);
    }

    public String getCardID(String name) {
        int i;
        for (i = 0; i < (card.length); i++) {
            if (card[i].getName().equals(name)) {
                return card[i].getId();
            }
        }
        return "Not found";
    }

    public String verifyCard(String name) {
        int i;
        for (i = 0; i < (card.length); i++) {
            if (card[i].getName().equals(name)) {
                return "Found";
            }
        }
        return null;
    }

}
