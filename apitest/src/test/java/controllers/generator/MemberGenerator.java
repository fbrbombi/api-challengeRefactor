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

public class MemberGenerator {
    Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    List[] Lists;
    Map<String, String> fields = new HashMap<>();

    public MemberGenerator(String idMembers, String idCard) {
        fields = Auth.getInstance().getAuthParams();
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(fields);
        link = ADDCARD + idCard + "/idMembers?value=" + idMembers;
        response = requestSpecification.when().post(link);
    }

    public void putParams(String key, String data) {
        fields.put(key, data);
    }
}
