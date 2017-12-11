package persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class WagonTypeDAO extends BaseDAO {

	public void addWagonType(WagonType WagonType) {
		try {
			Connection myConn = BaseDAO.getConnection();
	
			PreparedStatement pstmt = myConn.prepareStatement(
					"INSERT into wagontype(name, amountofseats, wagonid) VALUES(?,?,?)");
			pstmt.setString(1, //.getName());//
			pstmt.setString(2, //.getAmountOfSeats());//
			pstmt.setString(3, //gerecht.getWagonId());//
			pstmt.executeQuery();
			myConn.close();
		}
	
		catch (Exception exc) {
			exc.printStackTrace();
		}
	
	
	}
}
