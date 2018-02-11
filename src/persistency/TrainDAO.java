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
		ArrayList<Train> results = new ArrayList<Train>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbresultSet = stmt.executeQuery(query);
			while (dbresultSet.next()) {
				ArrayList<Component> components = new ArrayList<Component>();
				Integer trainID = dbresultSet.getInt("trainID");
				String name = dbresultSet.getString("name");
				Locomotive locomotive = ldao.findLocomotive(trainID);
				components.add(locomotive);

				Train train = aTrain()
                        .setTrainID(trainID)
                        .setName(name)
                        .setComponents(components)
                        .build();
				results.add(train);

			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return results;
	}

	// select statements
	public Train findTrain(Integer trainID) {
		ArrayList<Train> trains = getTrains("select * from train where trainid = " + trainID);
		Train train = trains.get(0);
		return train;
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
		ArrayList<Train> trains = getTrains("SELECT * FROM train ORDER BY \"trainid\" DESC limit 1 ");
		Train train = trains.get(0);
		return train;
	}

	public void createTrain(String trainname, String loconame) {
		try (Connection con = super.getConnection()) {

			PreparedStatement pstmt = con
					.prepareStatement("INSERT into train (name,trainID) VALUES(?,nextval('trainseq'))");

			pstmt.setString(1, trainname);

			pstmt.executeUpdate();
			Train t = findLatestTrain();

			Locomotive tloco = ldao.addLocomitive(t.getTrainID(), loconame);
			t.addComponent(tloco);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

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
