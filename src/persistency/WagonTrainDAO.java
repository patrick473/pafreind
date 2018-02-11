package persistency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Domain.Component;
import Domain.Train;
import Domain.Wagon;



public class WagonTrainDAO extends BaseDAO {
	WagonTypeDAO wdao = new WagonTypeDAO();

	public void addWagonTrain(int train, int wagon) {
		try {
			Connection myConn = BaseDAO.getConnection();

			Statement pstmt = myConn.createStatement();
			String query = "INSERT into wagontrain(\"trainID\", \"wagonID\" ,\"wagontrainID\") VALUES("+ train+","+ wagon+",nextval('wagontrainseq'))";
			pstmt.executeUpdate(query);
			myConn.close();
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

	}
    private ArrayList<Integer> getWagontrainInts(int train,int wagon){
        ArrayList<Integer> results = new ArrayList<Integer>();
        try (Connection con = super.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet dbresultSet = stmt.executeQuery("select * from wagontrain where \"trainID\" ="+train+"and \"wagonID\"="+ wagon);
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
	public void deleteWagonTrain(int trainid, int wagonid) {
		try (Connection con = super.getConnection()){

            ArrayList<Integer> wagons= getWagontrainInts(trainid, wagonid);
            Integer deletablewagon = wagons.get(0);
			Statement pstmt = con.createStatement();
			String query = "DELETE FROM wagontrain WHERE \"wagontrainID\" = "+ deletablewagon ;

			pstmt.executeUpdate(query);

		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

	}
	public ArrayList<Wagon> getWagonFromTrain(Train train){
        ArrayList<Wagon> results = new ArrayList<Wagon>();
        try (Connection con = super.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet dbresultSet = stmt.executeQuery("select \"wagonID\" from wagontrain where \"trainID\" ="+train.getTrainID());
            while (dbresultSet.next()){
                Integer wagon = dbresultSet.getInt("wagonID");
               Wagon SelectedWagon = wdao.findWagon(wagon);




                results.add(SelectedWagon);

            }
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return results;
    }

}
