package helpers;

import com.google.gson.Gson;
import controllers.Objects.Board;
import controllers.Objects.Card;
import controllers.Objects.Member;
import controllers.Objects.TrelloList;

import java.util.HashMap;
import java.util.Map;

import static utils.SearcherAttributes.findIdByName;
import static utils.SearcherAttributes.findPositionOfTrelloElement;

public class APIManager {
    protected Gson gson;

    public APIManager() {
        gson = new Gson();
    }

    private String getBoardsInfo() {
        return HTTPRequest.getRequest(null, "members/me/boards").body().asString();
    }

    public Board[] getBoards() {
        return gson.fromJson(getBoardsInfo(), Board[].class);
    }

    public String getBoardId(String boardName) {
        return findIdByName(getBoards(), boardName);
    }

    public String getListId(String listName, String idBoard) {
        return findIdByName(getTrelloList(idBoard), listName);
    }

    private String getListInfo(String idBoard) {
        return HTTPRequest.getRequest(null, "boards/" + idBoard + "/lists").body().asString();
    }

    public TrelloList[] getTrelloList(String idBoard) {
        return gson.fromJson(getListInfo(idBoard), TrelloList[].class);
    }


    public String getListPosition(TrelloList[] trelloLists, String listName) {
        return findPositionOfTrelloElement(trelloLists, listName);
    }


    private String getCardsFromListInfo(String idList) {
        return HTTPRequest.getRequest(null, "lists/" + idList + "/cards").body().asString();

    }

    public Card[] getCardsFromList(String idList) {
        return gson.fromJson(getCardsFromListInfo(idList), Card[].class);
    }

    public String getCardId(String idList, String cardName) {
        return findIdByName(getCardsFromList(idList), cardName);
    }

    private String getListMemberinfo(String idBoard) {
        return HTTPRequest.getRequest(null, "boards/" + idBoard + "/members").body().asString();

    }

    public Member[] getListOfMembers(String idBoard) {
        return gson.fromJson(getListMemberinfo(idBoard), Member[].class);
    }

    public void postNewList(String name, String idBoard, String pos) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("idBoard", idBoard);
        params.put("pos", pos);
        HTTPRequest.postRequest(params, "lists/");
    }

    public void postNewCard(String name, String idList) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("idList", idList);
        HTTPRequest.postRequest(params, "cards/");
    }

    public void addNewMember() {

    }

}
