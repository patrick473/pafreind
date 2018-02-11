package persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Domain.Component;
import Domain.Locomotive;
import Domain.Train;

import static Domain.Locomotive.LocomotiveBuilder.aLocomotive;


public class LocomotiveDAO extends BaseDAO {

	public void addLocomitive(int id, String loconame) {
		try {
			Connection myConn = BaseDAO.getConnection();

			PreparedStatement pstmt = myConn.prepareStatement(
					"INSERT into locomotive(\"trainID\", \"Name\", \"locomotiveID\") VALUES(?,?,nextval('locoseq'))");
			pstmt.setInt(1, id);
			pstmt.setString(2, loconame);

			pstmt.executeUpdate();
			myConn.close();

		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

	}

    private ArrayList<Locomotive> getLocomotives(String query) {
        ArrayList<Locomotive> results = new ArrayList<Locomotive>();
        try (Connection con = super.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet dbresultSet = stmt.executeQuery(query);
            while (dbresultSet.next()) {
                ArrayList<Component> components = new ArrayList<Component>();
                Integer locoID = dbresultSet.getInt("locomotiveid");
                String name = dbresultSet.getString("name");



                Locomotive locomotive = aLocomotive()
                        .setLocomotiveID(locoID)
                        .setName(name)
                        .build();
                results.add(locomotive);

            }
        } catch ( Exception exc) {
            exc.printStackTrace();
        }
        return results;
    }
	public List<Locomotive> findAlleLocomotives() {

		return getLocomotives("select * from locomotive");
	}
	
	public Locomotive findLocomotive(int trainid) {

		return getLocomotives("SELECT * FROM locomotive WHERE \"trainID\" = "+trainid).get(0);
	}
    public Locomotive findLatestLocomotive() {


        return getLocomotives("SELECT * FROM locomitve ORDER BY \"trainid\" DESC limit 1 ").get(0);
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
