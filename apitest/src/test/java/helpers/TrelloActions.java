package helpers;

import io.restassured.response.Response;

public class TrelloActions {

    public static String getBoardsInfo() {
        return HTTPRequest.getRequest(null, "members/me/boards").body().asString();
    }


}
