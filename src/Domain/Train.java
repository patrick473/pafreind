package Domain;

import java.util.ArrayList;

public class Train  {

    Integer trainID;
    ArrayList<Component> components;
    String name;




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


    public static final class TrainBuilder {
        Integer trainID;
        ArrayList<Component> components;
        String name;

        private TrainBuilder() {
        }

        public static TrainBuilder aTrain() {
            return new TrainBuilder();
        }

        public TrainBuilder setTrainID(Integer trainID) {
            this.trainID = trainID;
            return this;
        }

        public TrainBuilder setComponents(ArrayList<Component> components) {
            this.components = components;
            return this;
        }

        public TrainBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Train build() {
            Train train = new Train();
            train.setTrainID(trainID);
            train.setComponents(components);
            train.setName(name);
            return train;
        }
    }
}
