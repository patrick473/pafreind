package persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Domain.Locomotive;
import Domain.Train;


public class LocomotiveDAO extends BaseDAO {

	public void addLocomitive(Train train, Locomotive locomotive) {
		try {
			Connection myConn = BaseDAO.getConnection();

			PreparedStatement pstmt = myConn.prepareStatement(
					"INSERT into locomotive(trainid, name, locomotiveid) VALUES(?,?,?)");
		//	pstmt.setInt(1, train.getTrainID());
			pstmt.setString(2, locomotive.getName());
			pstmt.setInt(3, locomotive.getLocomotiveID());
			pstmt.executeQuery();
			myConn.close();
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

	}
}
