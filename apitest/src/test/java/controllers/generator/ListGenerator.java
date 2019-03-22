package controllers.generator;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import controllers.Objects.List;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static controllers.Strings.CREATELIST;

public class ListGenerator {
    Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    List[] Lists;
    Map<String, String> fields = new HashMap<>();

    public ListGenerator(String name, String idBoard, String pos) {
        fields = Auth.getInstance().getAuthParams();
        putParams("name", name);
        putParams("idBoard", idBoard);
        putParams("pos", pos);
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(fields);
        response = requestSpecification.when().post(CREATELIST);
    }

    public void putParams(String key, String data) {
        fields.put(key, data);
    }

}
