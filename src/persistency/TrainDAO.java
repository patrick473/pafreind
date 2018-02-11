package persistency;

import Domain.Component;
import Domain.Locomotive;
import Domain.Train;

import java.sql.*;
import java.util.ArrayList;

import static Domain.Train.TrainBuilder.aTrain;

public class TrainDAO extends BaseDAO {

	LocomotiveDAO ldao = new LocomotiveDAO();

	// master select
	private ArrayList<Train> getTrains(String query) {
	    System.out.println(query);
		ArrayList<Train> results = new ArrayList<Train>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbresultSet = stmt.executeQuery(query);

			while (dbresultSet.next()) {

				ArrayList<Component> components = new ArrayList<Component>();
				Integer trainID = dbresultSet.getInt("trainid");
				String name = dbresultSet.getString("name");
				try {
                    Locomotive locomotive = ldao.findLocomotive(trainID);
                    components.add(locomotive);
                }
                catch(Exception exc){
				    exc.printStackTrace();
                }

				Train train = aTrain()
                        .setTrainID(trainID)
                        .setName(name)
                        .setComponents(components)
                        .build();
				System.out.println(train);
				results.add(train);

			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return results;
	}

	// select statements
	public Train findTrain(Integer trainID) {
		return getTrains("select * from train where trainid = " + trainID).get(0);
	}

	public Train findTrainByName(String name) {
		ArrayList<Train> trains = getTrains("select * from train where name = " + name);
		Train train = trains.get(0);
		return train;
	}

	public ArrayList<Train> findAllTrains() {
		ArrayList<Train> trains = getTrains("select * from train ");

		return trains;
	}

	public Train findLatestTrain() {

		return getTrains("SELECT * FROM train ORDER BY trainid  DESC  ").get(0);
	}

	public void createTrain(String trainname, String loconame) {
		try (Connection con = super.getConnection()) {


            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT into train (name,trainID) VALUES('"+trainname+"',nextval('trainseq'))");





		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
        ldao.addLocomitive(findLatestTrain().getTrainID(), loconame);

	}

	public int getTrainSeats(Integer trainid) {
		int result = 0;
		try {
			Connection myConn = BaseDAO.getConnection();

			PreparedStatement pstmt = myConn
					.prepareStatement("SELECT amountofseats FROM wagontype, wagontrain WHERE wagontrain.\"trainID\" = ? "
							+ "AND wagontype.\"wagonID\" = wagontrain.\"wagonID\" ");
			pstmt.setInt(1, trainid);
			pstmt.executeQuery();

			ResultSet rs = pstmt.getResultSet();
			while (rs.next()) {
				Integer seats = rs.getInt("amountofseats");
				result  += seats;

			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return result;
	}

	public void deleteTrain(Integer trainID) {
		try (Connection con = super.getConnection()) {
            Statement stmt = con.createStatement();
            String query = "delete from train where \"trainid\" = "+ trainID;
            stmt.executeUpdate(query);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
