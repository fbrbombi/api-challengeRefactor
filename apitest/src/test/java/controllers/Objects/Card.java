package controllers.Objects;

public class Card extends TrelloObject {

    public Card(String name, String id, String[] idMembers) {
        this.name = name;
        this.id = id;
        this.idMembers = idMembers;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

}
