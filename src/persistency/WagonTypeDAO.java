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
					"INSERT into wagontype(name, amountofseats, \"wagonID\") VALUES(?,?,nextval('wagonseq'))");
			pstmt.setString(1, WagonType.getName());
			pstmt.setInt(2, WagonType.getAmountOfSeats());

			pstmt.executeUpdate();
			myConn.close();
		}
	
		catch (Exception exc) {
			exc.printStackTrace();
		}
	
	
	}
	
	private List<Wagon> findAllWagonTypes(String query) {
		List<Wagon> wagontypelist = new ArrayList<Wagon>();
		try {
			Connection myConn = BaseDAO.getConnection();

			Statement myStmt = myConn.createStatement();

			ResultSet myRs = myStmt.executeQuery(query);

			while (myRs.next()) {
				Wagon s = new Wagon(myRs.getString("name"), myRs.getInt("amountofseats"),
						myRs.getInt("wagonID"));
				wagontypelist.add(s);
				
				
			}
			myConn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return wagontypelist;
	}
    public Wagon findWagon(Integer trainID){
        List<Wagon> wagons =findAllWagonTypes("select * from wagon where \"wagonid\" = "+ trainID);
        Wagon wagon = wagons.get(0);
        return wagon;
    }
    
    public int getWagonSeats(Integer wagonid) {
    	int result = 0;
    	try {
			Connection myConn = BaseDAO.getConnection();

			PreparedStatement pstmt = myConn.prepareStatement("SELECT amountofseats FROM wagon WHERE wagonid = ?");
			pstmt.setInt(1, wagonid);
			pstmt.executeQuery();

			ResultSet rs = pstmt.getResultSet();
			while (rs.next()) {
				String seats = rs.getString("amountofseats");
				result = Integer.parseInt(seats);

			}

			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
    	return result;
    }
    
    public Wagon findWagonByName(String name ){
        List<Wagon> wagons =findAllWagonTypes("select * from wagon where name = "+ name);
        Wagon wagon = wagons.get(0);
        return wagon;
    }


    public List<Wagon> selectAllWagonTypes(){
	    return findAllWagonTypes("select * from wagontype");
    }
    public Wagon selectWagon(Integer wagonID){
	    List<Wagon> wagontypes = findAllWagonTypes("select * from wagontype where \"wagonID\" ="+wagonID);
	    return wagontypes.get(0);
    }
}
