package persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Domain.Locomotive;
import Domain.Train;



public class LocomotiveDAO extends BaseDAO {

	public void addLocomitive(Train train, Locomotive locomotive) {
		try {
			Connection myConn = BaseDAO.getConnection();

			PreparedStatement pstmt = myConn.prepareStatement(
					"INSERT into locomotive(trainid, name, locomotiveid) VALUES(?,?,?)");
			pstmt.setInt(1, train.getTrainID());
			pstmt.setString(2, locomotive.getName());
			pstmt.setInt(3, locomotive.getLocomotiveID());
			pstmt.executeQuery();
			myConn.close();
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

	}
	
	public List<Locomotive> findAlleLocomotives() {
		List<Locomotive> locomotivelist = new ArrayList<Locomotive>();
		try {
			Connection myConn = BaseDAO.getConnection();

			Statement myStmt = myConn.createStatement();

			ResultSet myRs = myStmt.executeQuery("select * from locomotive");

			while (myRs.next()) {
				Locomotive s = new Locomotive(myRs.getString("name"),
						myRs.getInt("locomotiveid"));
				locomotivelist.add(s);
				
				
			}
			myConn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return locomotivelist;
	}
	
	public Locomotive findLocomotive(int trainid) {
		Locomotive p = null;
		try {
			Connection myConn = BaseDAO.getConnection();

			PreparedStatement pstmt = myConn.prepareStatement("SELECT * FROM locomotive WHERE trainid = ?");
			pstmt.setInt(1, trainid);
			pstmt.executeQuery();

			ResultSet rs = pstmt.getResultSet();

			while (rs.next()) {

				String name1 = rs.getString("name");
				int locomotiveid1 = rs.getInt("locomotiveid");
				Locomotive locomotive = new Locomotive( name1, locomotiveid1);

				p = locomotive;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return p;
	}
	
	public void deleteLocomotive(Train train) {
		try {
			Connection myConn = BaseDAO.getConnection();

			PreparedStatement pstmt = myConn.prepareStatement("DELETE FROM locomotive WHERE trainid = ?");
			pstmt.setInt(1, train.getTrainID());
			pstmt.execute();
			myConn.close();
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

	}
}
