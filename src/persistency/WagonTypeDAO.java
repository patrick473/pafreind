package persistency;

import Domain.Component;
import Domain.Wagon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WagonTypeDAO extends BaseDAO {

	public void addWagonType(Wagon WagonType) {
		try {
			Connection myConn = BaseDAO.getConnection();
	
			PreparedStatement pstmt = myConn.prepareStatement(
					"INSERT into wagontype(name, amountofseats, wagonid) VALUES(?,?,?)");
			pstmt.setString(1, WagonType.getName());
			pstmt.setInt(2, WagonType.getAmountOfSeats());
			pstmt.setInt(3, WagonType.getWagonID());
			pstmt.executeQuery();
			myConn.close();
		}
	
		catch (Exception exc) {
			exc.printStackTrace();
		}
	
	
	}
	
	public List<Wagon> findAlleWagonTypes() {
		List<Wagon> wagontypelist = new ArrayList<Wagon>();
		try {
			Connection myConn = BaseDAO.getConnection();

			Statement myStmt = myConn.createStatement();

			ResultSet myRs = myStmt.executeQuery("select * from wagontype");

			while (myRs.next()) {
				Wagon s = new Wagon(myRs.getString("name"), myRs.getInt("amountofseats"),
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
