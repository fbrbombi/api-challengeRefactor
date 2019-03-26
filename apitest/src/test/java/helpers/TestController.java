package helpers;

import static utils.SearcherAttributes.*;

public class TestController {
    private APIManager apiManager;
    private String board;
    private String idBoard;
    private String listPosition;
    private String idList;
    private String cardName;
    private String idCard;
    private String idMember;
    private String idNextList;

    public TestController() {
        apiManager = new APIManager();
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getBoardId() {
        return idBoard;
    }

    public void findBoardId() {
        idBoard = apiManager.getBoardId(board);
    }

    public String getPositionOfList(String list) {
        listPosition = apiManager.getListPosition(apiManager.getTrelloList(idBoard), list);
        return listPosition;
    }

    public void postNewList(String name) {
        int pos = (int) Float.parseFloat(listPosition) - 1;
        apiManager.postNewList(name, idBoard, String.valueOf(pos));
    }

    public boolean isTheListCreated(String name) {
        return isThereAListWithName(apiManager.getTrelloList(idBoard), name);
    }

    public void findIDList(String name) {
        idList = apiManager.getListId(name, idBoard);
    }

    public void postNewCard(String cardName) {
        apiManager.postNewCard(cardName, idList);
    }

    public String postCardValidation(String cardName) {
        return findByName(apiManager.getCardsFromList(idList), cardName);
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void findCardID() {
        idCard = apiManager.getCardId(idList, cardName);
    }

    public void findIDMember() {
        idMember = apiManager.getIdMember(idBoard);
    }

    public void addNewMember() {
        apiManager.addNewMember(idMember, idCard);
    }

    public boolean isTheMemberAdded() {
        return isThisIdPartOfThisList(idMember, apiManager.getListOfMembersCard(idCard));
    }

    public void postAComment(String text) {
        apiManager.postNewComment(text, idCard);
    }

    public void getNextIdList(String nextList) {
        idNextList = apiManager.getListId(nextList, idBoard);
    }

    public void moveCard() {
        apiManager.moveACard(idNextList, idCard);
    }

    public boolean isTheCardMoved() {
        return isThisIdPartOfThisList(idCard, apiManager.getCardsFromList(idNextList));
    }

    public void deleteCard() {
        apiManager.deleteCard(idCard);
    }

    public boolean isTheCardDeleted() {
        return isThisIdPartOfThisList(idCard, apiManager.getCardsFromList(idList));
    }
}
