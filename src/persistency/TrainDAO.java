package persistency;

import Domain.Train;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TrainDAO extends BaseDAO {


    public ArrayList<Train> getTrains(String query){
        ArrayList<Train> results = new ArrayList<Train>();
        try (Connection con = super.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet dbresultSet = stmt.executeQuery(query);
            while (dbresultSet.next()){
                Integer trainID = dbresultSet.getInt("trainID");
                String name = dbresultSet.getString("name");
              //  Train train = new Train()
              //  results.add();

            }
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return results;
    }
}
