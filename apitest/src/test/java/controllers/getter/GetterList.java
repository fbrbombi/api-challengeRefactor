package controllers.getter;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import controllers.Objects.List;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static controllers.Strings.LIST;

public class GetterList {
    private Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    List[] Lists;

    public GetterList(String id) {
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(Auth.getInstance().getAuthParams());
        System.out.println(Auth.getInstance().getAuthParams());

        link = LIST + id + "/lists?fields=name,pos";
        response = requestSpecification.when().get(link);
        Lists = gson.fromJson(response.body().asString(), List[].class);
    }

    public void validate() {

        for (int i = 0; i < (Lists.length); i++) {
            System.out.println(Lists[i].getName());
            System.out.println(Lists[i].getPos());
        }

    }

    public String getPos(String name) {
        int i;
        for (i = 0; i < (Lists.length); i++) {
            if (Lists[i].getName().equals(name)) {
                return Lists[i].getPos();
            }
        }
        return "Not found";
    }

    public String getID(String id) {


        for (int i = 0; i < (Lists.length); i++) {
            //System.out.println(Lists[i].getName());
            if (Lists[i].getName().equals(id)) {
                return Lists[i].getId();
            }
        }
        return "Not found";
    }
    public Response getResponse() {
        return response;
    }
}
