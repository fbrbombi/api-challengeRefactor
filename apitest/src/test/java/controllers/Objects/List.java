package controllers.Objects;

public class List {
    private String name;
    private String id;
    private String pos;

    public List() {
        this.name = "";
        this.id = "";
        this.pos = "";
    }

    public List(String name, String id, String pos) {
        this.name = name;
        this.id = id;
        this.pos = pos;
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

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
