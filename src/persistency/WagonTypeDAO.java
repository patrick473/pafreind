package persistency;

import Domain.Component;
import Domain.Wagon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static Domain.Wagon.WagonBuilder.aWagon;

public class WagonTypeDAO extends BaseDAO {

	public void addWagonType(String name, int amountofseats) {
		try {
			Connection myConn = BaseDAO.getConnection();
	
			PreparedStatement pstmt = myConn.prepareStatement(
					"INSERT into wagontype(name, amountofseats, \"wagonID\") VALUES(?,?,nextval('wagonseq'))");
			pstmt.setString(1, name);

			pstmt.setInt(2, amountofseats);

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
                String name = myRs.getString("name");
                int aos = myRs.getInt("amountofseats");
                int id = myRs.getInt("wagonID");
				Wagon s = aWagon()
						.setAmountOfSeats(aos)
						.setName(name)
						.setWagonID(id)
						.build();
				wagontypelist.add(s);
				
				
			}
			myConn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return wagontypelist;
	}


    public Wagon findWagon(Integer wagonID){

        return findAllWagonTypes("select * from wagontype where \"wagonID\" = "+ wagonID).get(0);
    }
    
    public Wagon findWagonByName(String name ){

        return findAllWagonTypes("select * from wagontype where name = "+ name).get(0);
    }

    public Wagon findLatestWagon() {


        return findAllWagonTypes("SELECT * FROM wagontype ORDER BY \"wagonID\" DESC limit 1 ").get(0);
    }
    public List<Wagon> selectAllWagonTypes(){

        return findAllWagonTypes("select * from wagontype");
    }


}
