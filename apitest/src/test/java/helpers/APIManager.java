package helpers;

import com.google.gson.Gson;
import trelloObjects.TrelloObject;

import java.util.HashMap;
import java.util.Map;

import static helpers.TrelloEndPoints.*;
import static utils.SearcherAttributes.*;

public class APIManager {
    private Gson gson;

    public APIManager() {
        gson = new Gson();
    }

    private String getTrelloObjectJSON(String endpoint, Map<String, String> otherParams) {
        return HTTPRequest.getRequest(otherParams, endpoint).body().asString();
    }

    private TrelloObject[] getTrelloObject(Map<String, String> otherParams, String endpoint) {
        return gson.fromJson(getTrelloObjectJSON(endpoint, otherParams), TrelloObject[].class);
    }

    public TrelloObject[] getBoards() {
        return getTrelloObject(null, pathIdBoard);
    }

    public String getBoardId(String boardName) {
        return findIdByName(getBoards(), boardName);
    }

    public String getListId(String listName, String idBoard) {
        return findIdByName(getTrelloList(idBoard), listName);
    }


    public TrelloObject[] getTrelloList(String idBoard) {
        String path = pathBoard + idBoard + endPointLists;
        return getTrelloObject(null, path);
    }

    public String getListPosition(TrelloObject[] trelloLists, String listName) {
        return findPositionOfTrelloElement(trelloLists, listName);
    }


    public TrelloObject[] getCardsFromList(String idList) {
        String path = pathLists + idList + endPointCards;
        return getTrelloObject(null, path);
    }

    public String getCardId(String idList, String cardName) {
        return findIdByName(getCardsFromList(idList), cardName);
    }


    private TrelloObject[] getListOfMembersBoard(String idBoard) {
        String path = pathBoard + idBoard + endPointMembers;
        return getTrelloObject(null, path);
    }

    public String getIdMember(String idBoard) {
        return getRandomMemberIdOfTheBoard(getListOfMembersBoard(idBoard));
    }

    public TrelloObject[] getListOfMembersCard(String idCard) {
        String path = pathCards + idCard + endPointMembers;
        return getTrelloObject(null, path);
    }

    public void postNewList(String name, String idBoard, String pos) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("idBoard", idBoard);
        params.put("pos", pos);
        HTTPRequest.postRequest(params, pathLists);
    }

    public void postNewCard(String name, String idList) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("idList", idList);
        HTTPRequest.postRequest(params, pathCards);
    }

    public void addNewMember(String value, String idCard) {
        Map<String, String> params = new HashMap<>();
        params.put("value", value);
        HTTPRequest.postRequest(params, pathCards + idCard + endPointIdMembers);
    }

    public void postNewComment(String text, String idCard) {
        Map<String, String> params = new HashMap<>();
        params.put("text", text);
        HTTPRequest.postRequest(params, pathCards + idCard + endPointComments);
    }


    public TrelloObject[] getComments(String idCard) {
        Map<String, String> params = new HashMap<>();
        params.put("fields", "badges");
        String path = pathCards + idCard;
        return getTrelloObject(params, path);
    }

    public void moveACard(String nextList, String idCard) {
        Map<String, String> params = new HashMap<>();
        params.put("idList", nextList);
        HTTPRequest.putRequest(params, pathCards + idCard);
    }

    public void deleteCard(String idCard) {
        HTTPRequest.deleteRequest(null, pathCards + idCard);
    }


}
