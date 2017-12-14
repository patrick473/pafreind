package persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Domain.Component;
import Domain.Train;
import Domain.Wagon;



public class WagonTrainDAO extends BaseDAO {
	
	public void addWagonTrain(Train train, Wagon wagon) {
		try {
			Connection myConn = BaseDAO.getConnection();

			PreparedStatement pstmt = myConn.prepareStatement(
					"INSERT into wagontrain(trainid, wagonid) VALUES(?,?)");
			pstmt.setInt(1, train.getTrainID());
			pstmt.setInt(2, wagon.getWagonID());
			pstmt.executeQuery();
			myConn.close();
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

	}
	
	public void deleteWagonTrain(Train train, Wagon wagon) {
		try {
			Connection myConn = BaseDAO.getConnection();

			PreparedStatement pstmt = myConn.prepareStatement("DELETE FROM wagontrain WHERE trainid = ? and wagonid = ?");
			pstmt.setInt(1, train.getTrainID());
			pstmt.setInt(2,wagon.getWagonID());
			pstmt.execute();
			myConn.close();
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
