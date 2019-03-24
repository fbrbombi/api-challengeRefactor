package helpers;

import com.google.gson.Gson;
import controllers.Objects.*;

import java.util.HashMap;
import java.util.Map;

import static utils.SearcherAttributes.*;

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

    private String getListMemberOfBoardInfo(String idBoard) {
        return HTTPRequest.getRequest(null, "boards/" + idBoard + "/members").body().asString();
    }

    private Member[] getListOfMembersBoard(String idBoard) {
        return gson.fromJson(getListMemberOfBoardInfo(idBoard), Member[].class);
    }

    public String getIdMember(String idBoard) {
        return getRandomMemberIdOfTheBoard(getListOfMembersBoard(idBoard));
    }

    private String getListMemberOfCardInfo(String idCard) {
        return HTTPRequest.getRequest(null, "cards/" + idCard + "/members").body().asString();
    }

    public Member[] getListOfMembersCard(String idCard) {
        return gson.fromJson(getListMemberOfCardInfo(idCard), Member[].class);
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

    public void addNewMember(String value, String idCard) {
        Map<String, String> params = new HashMap<>();
        params.put("value", value);
        HTTPRequest.postRequest(params, "cards/" + idCard + "/idMembers");
    }

    public void postNewComment(String text, String idCard) {
        Map<String, String> params = new HashMap<>();
        params.put("text", text);
        HTTPRequest.postRequest(params, "cards/" + idCard + "/actions/comments");
    }

    private String getCommentsInfo(String idCard) {
        Map<String, String> params = new HashMap<>();
        params.put("fields", "badges");
        return HTTPRequest.getRequest(params, "cards/" + idCard).body().asString();
    }

    public Comment[] getComments(String idCard) {
        System.out.println(getCommentsInfo(idCard));
        return gson.fromJson(getCommentsInfo(idCard), Comment[].class);
    }

    public void moveACard(String nextList, String idCard) {
        Map<String, String> params = new HashMap<>();
        params.put("idList", nextList);
        HTTPRequest.putRequest(params, "cards/" + idCard);
    }

}
