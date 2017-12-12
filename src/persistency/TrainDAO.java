package persistency;

import Domain.Component;
import Domain.Locomotive;
import Domain.Train;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TrainDAO extends BaseDAO {

    LocomotiveDAO ldao = new LocomotiveDAO();
    public ArrayList<Train> getTrains(String query){
        ArrayList<Train> results = new ArrayList<Train>();
        try (Connection con = super.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet dbresultSet = stmt.executeQuery(query);
            while (dbresultSet.next()){
                ArrayList<Component> components = new ArrayList<Component>();
                Integer trainID = dbresultSet.getInt("trainID");
                String name = dbresultSet.getString("name");
                Locomotive locomotive = ldao.findLocomotive(trainID);
                components.add(locomotive);

                Train train = new Train(trainID,components,name);
                results.add(train);

            }
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return results;
    }
    public Train findTrain(Integer trainID){
        getTrains("select * from train where")
    }
}
