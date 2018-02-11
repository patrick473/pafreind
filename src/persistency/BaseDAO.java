package persistency;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BaseDAO {
    private DataSource connectionPool;

    public BaseDAO() {

    }
    public static Connection getConnection(){
        final String url = "jdbc:postgresql://localhost/postgres";
        final String user = "root";
        final String password = "password";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}