package controllers.Objects;

public class List extends TrelloObject {

    public List(String name, String id, String pos) {
        this.name = name;
        this.id = id;
        this.pos = pos;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setId(String id) {
        this.id = id;
    }


    public void setPos(String pos) {
        this.pos = pos;
    }
}
