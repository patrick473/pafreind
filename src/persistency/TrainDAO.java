package persistency;

import Domain.Component;
import Domain.Locomotive;
import Domain.Train;

import java.sql.*;
import java.util.ArrayList;

public class TrainDAO extends BaseDAO {

    LocomotiveDAO ldao = new LocomotiveDAO();

    //master select
    private ArrayList<Train> getTrains(String query){
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
    //select statements
    public Train findTrain(Integer trainID){
        ArrayList<Train> trains =getTrains("select * from train where trainid = "+ trainID);
        Train train = trains.get(0);
        return train;
    }
    public Train findTrainByName(String name){
        ArrayList<Train> trains =getTrains("select * from train where name = "+ name);
        Train train = trains.get(0);
        return train;
    }
    public ArrayList<Train> findAllTrains(){
        ArrayList<Train> trains =getTrains("select * from train ");

        return trains;
    }
    public Train findLatestTrain(){
        ArrayList<Train> trains =getTrains("SELECT * FROM train ORDER BY \"trainID\" DESC limit 1 ");
        Train train = trains.get(0);
        return train;
    }
    public Train createTrain(Train t,String loconame){
        try(Connection con = super.getConnection()){

            PreparedStatement pstmt = con.prepareStatement(
                    "INSERT into train (name,trainID) VALUES(?,nextval('trainseq'))");

            pstmt.setString(1, t.getName());

            pstmt.executeUpdate();
        t = findLatestTrain();

        Component tloco = new Locomotive(loconame);
        ldao.addLocomitive(t,tloco);
        t.addComponent(tloco);
        }
        catch ( SQLException sqle){
            sqle.printStackTrace();
        }
        return t;
    }
    
    public void getTrainSeats(Integer trainid) {
    	try {
			Connection myConn = BaseDAO.getConnection();

			PreparedStatement pstmt = myConn.prepareStatement("SELECT amountofseats FROM wagontype WHERE wagontrain.trainid = ? "
					+ "AND wagontype.wagonid = wagontrain.wagonid ");
			pstmt.setInt(1, trainid);
			pstmt.executeQuery();

			ResultSet rs = pstmt.getResultSet();

			System.out.println(rs);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
    }
    
    public void deleteTrain(Train t){
        try( Connection con = super.getConnection()){

        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }


}
