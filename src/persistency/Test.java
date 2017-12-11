package persistency;




/**
 * Created by patrick on 12/7/2017.
 */
public class Test {

    public static void main(String[] args) {

        BaseDAO bdao = new BaseDAO();
        TrainDAO tdao = new TrainDAO();
        bdao.getConnection();

        System.out.println(tdao.getTrains("select * from train"));

        }
    }

