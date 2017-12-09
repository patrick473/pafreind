package Domain;

public abstract class Component {
    protected String name;

    protected Integer position;

    public Component() {
    }

    public Component(String name,  Integer position) {
        this.name = name;

        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}


