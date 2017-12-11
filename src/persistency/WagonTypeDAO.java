package persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.DAO.BaseDAO;
import nl.hu.v1ipass.POJO.Gerecht;

public class WagonTypeDAO extends BaseDAO {

	public void addWagonType(WagonType WagonType) {
		try {
			Connection myConn = BaseDAO.getConnection();
	
			PreparedStatement pstmt = myConn.prepareStatement(
					"INSERT into wagontype(name, amountofseats, wagonid) VALUES(?,?,?)");
			pstmt.setString(1, //.getName());//
			pstmt.setInt(2, //.getAmountOfSeats());//
			pstmt.setInt(3, //gerecht.getWagonId());//
			pstmt.executeQuery();
			myConn.close();
		}
	
		catch (Exception exc) {
			exc.printStackTrace();
		}
	
	
	}
	
	public List<WagonType> findAlleWagonTypes() {
		List<WagonType> wagontypelist = new ArrayList<WagonType>();
		try {
			Connection myConn = BaseDAO.getConnection();

			Statement myStmt = myConn.createStatement();

			ResultSet myRs = myStmt.executeQuery("select * from wagontype");

			while (myRs.next()) {
				WagonType s = new WagonType(myRs.getString("name"), myRs.getInt("amountofseats"),
						myRs.getInt("wagonid"));
				wagontypelist.add(s);
				
				
			}
			myConn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return wagontypelist;
	}
}
