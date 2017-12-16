package persistency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Domain.Component;
import Domain.Train;
import Domain.Wagon;



public class WagonTrainDAO extends BaseDAO {
	WagonTypeDAO wdao = new WagonTypeDAO();

	public void addWagonTrain(Train train, Wagon wagon) {
		try {
			Connection myConn = BaseDAO.getConnection();

			Statement pstmt = myConn.createStatement();
			String query = "INSERT into wagontrain(\"trainID\", \"wagonID\" ,\"wagontrainID\") VALUES("+ train.getTrainID()+","+ wagon.getWagonID()+",nextval('wagontrainseq'))";
			pstmt.executeUpdate(query);
			myConn.close();
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

	}
    private ArrayList<Integer> getWagontrainInts(Train train,Wagon wagon){
        ArrayList<Integer> results = new ArrayList<Integer>();
        try (Connection con = super.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet dbresultSet = stmt.executeQuery("select * from wagontrain where \"trainID\" ="+train.getTrainID()+"and \"wagonID\"="+ wagon.getWagonID());
            while (dbresultSet.next()){
               Integer wagontrainid = dbresultSet.getInt("wagontrainID");




                results.add(wagontrainid);

            }
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return results;
    }
	public void deleteWagonTrain(Train train, Wagon wagon) {
		try (Connection con = super.getConnection()){

            ArrayList<Integer> wagons= getWagontrainInts(train, wagon);
            Integer deletablewagon = wagons.get(0);
			Statement pstmt = con.createStatement();
			String query = "DELETE FROM wagontrain WHERE \"wagontrainID\" = "+ deletablewagon ;

			pstmt.executeUpdate(query);

		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
