package controllers.Objects;

public class Card {
    private String name;
    private String id;
    private String[] idMembers;

    public Card() {
        this.name = "";
        this.id = "";
    }

    public Card(String name, String id, String[] idMembers) {
        this.name = name;
        this.id = id;
        this.idMembers = idMembers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getIdMembers() {
        return idMembers;
    }

    public void setIdMembers(String[] idMembers) {
        this.idMembers = idMembers;
    }
}
