package controllers.Objects;

public class CardList {
    public String id;
    public String name;

    public CardList(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
