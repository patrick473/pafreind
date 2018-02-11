package Domain;

public class Locomotive extends Component {

        Integer locomotiveID;





    public Integer getLocomotiveID() {
        return locomotiveID;
    }

    public void setLocomotiveID(Integer locomotiveID) {
        this.locomotiveID = locomotiveID;
    }

    @Override
    public String toString() {
        return "Locomotive{" +
                "locomotiveID=" + locomotiveID +
                ", name='" + name + '\'' +
                '}';
    }

    public static final class LocomotiveBuilder {
        protected String name;
        Integer locomotiveID;

        private LocomotiveBuilder() {
        }

        public static LocomotiveBuilder aLocomotive() {
            return new LocomotiveBuilder();
        }

        public LocomotiveBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public LocomotiveBuilder setLocomotiveID(Integer locomotiveID) {
            this.locomotiveID = locomotiveID;
            return this;
        }

        public Locomotive build() {
            Locomotive locomotive = new Locomotive();
            locomotive.setName(name);
            locomotive.setLocomotiveID(locomotiveID);
            return locomotive;
        }
    }
}
