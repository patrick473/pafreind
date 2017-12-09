package Domain;

public abstract class Component {
    protected String name;



    public Component() {
    }

    public Component(String name) {
        this.name = name;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Component{" +
                "name='" + name + '\''+
                '}';
    }
}


