package Domain;

import java.util.ArrayList;

public class Train  {

    Integer trainID;
    ArrayList<Component> components;
    String name;

    public Train(String name) {
        this.name = name;
    }

    public Train(Integer trainID, ArrayList<Component> components, String name) {
        this.trainID = trainID;
        this.components = components;
        this.name = name;
    }

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }
    public void addComponent(Component component){
        components.add(component);
    }
    public Integer calculateSeats(){
            Integer amountOfSeats = 0;
        for (Component component: components){
            if (component instanceof Wagon){
                 amountOfSeats += ((Wagon) component).getAmountOfSeats();
            }
        }
        return amountOfSeats;
    }
    @Override

    public String toString() {
        return "Train{" +
                "trainID=" + trainID +
                ", components=" + components +
                ", name='" + name + '\'' +
                '}';
    }
}
