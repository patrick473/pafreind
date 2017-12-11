package persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Domain.Locomotive;


public class LocomotiveDAO extends BaseDAO {

	public void addLocomitive(Locomotive locomotive) {
		try {
			Connection myConn = BaseDAO.getConnection();

			PreparedStatement pstmt = myConn.prepareStatement(
					"INSERT into locomotive(trainid, name, locomotiveid) VALUES(?,?,?)");
			pstmt.setInt(1, locomotive.getTrainID());
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
