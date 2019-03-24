package controllers.getter;

import com.google.gson.Gson;
import controllers.Objects.Auth;
import controllers.Objects.TrelloList;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static controllers.Strings.LIST;

public class GetterList {
    private Gson gson = new Gson();
    RequestSpecification requestSpecification;
    Response response;
    String link;
    TrelloList[] trelloLists;

    public GetterList(String id) {
        requestSpecification = RestAssured.given().contentType("application/json")
                .and().queryParams(Auth.getInstance().getAuthParams());
        System.out.println(Auth.getInstance().getAuthParams());

        link = LIST + id + "/lists?fields=name,pos";
        response = requestSpecification.when().get(link);
        trelloLists = gson.fromJson(response.body().asString(), TrelloList[].class);
    }

    public void validate() {

        for (int i = 0; i < (trelloLists.length); i++) {
            System.out.println(trelloLists[i].getName());
            System.out.println(trelloLists[i].getPos());
        }

    }

    public String getPos(String name) {
        int i;
        for (i = 0; i < (trelloLists.length); i++) {
            if (trelloLists[i].getName().equals(name)) {
                return trelloLists[i].getPos();
            }
        }
        return "Not found";
    }

    public String getID(String id) {


        for (int i = 0; i < (trelloLists.length); i++) {
            //System.out.println(trelloLists[i].getName());
            if (trelloLists[i].getName().equals(id)) {
                return trelloLists[i].getId();
            }
        }
        return "Not found";
    }
    public Response getResponse() {
        return response;
    }
}
