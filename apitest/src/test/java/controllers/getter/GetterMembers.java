package controllers.getter;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import controllers.Objects.Member;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static controllers.Strings.GETMEMBERS;

public class GetterMembers {
    private String BoardName;
    Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    Map<String, String> fields = new HashMap<>();
    Member[] members;

    public GetterMembers(String idBoard) {

        fields = Auth.getInstance().getAuthParams();
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(fields);
        link = GETMEMBERS + idBoard + "/members?fields=fullname";
        response = requestSpecification.when().get(link);
        System.out.println(response.body().asString());
        members = gson.fromJson(response.body().asString(), Member[].class);
    }

    public void putParams(String key, String data) {
        fields.put(key, data);
    }

    public String getMemberID(int num) {
        return members[num].getId();
    }

    public Member[] getMember() {
        return members;
    }

}
