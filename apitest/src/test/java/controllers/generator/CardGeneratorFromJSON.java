package controllers.generator;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import controllers.Objects.List;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static controllers.Strings.ADDCARD;


public class CardGeneratorFromJSON {
    Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    List[] Lists;
    Map<String, String> fields = new HashMap<>();

    public CardGeneratorFromJSON(String name, String idlist) {
        fields = Auth.getInstance().getAuthParams();
        putParams("idList", idlist);
        putParams("name", name);
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(fields);
        response = requestSpecification.when().post(ADDCARD);
    }

    public void putParams(String key, String data) {
        fields.put(key, data);
    }
}
