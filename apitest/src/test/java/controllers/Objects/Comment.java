package controllers.Objects;

public class Comment extends TrelloObject {

    String[] badges;

    public Comment(String id, String[] badges) {
        this.id = id;
        this.badges = badges;
    }
}
