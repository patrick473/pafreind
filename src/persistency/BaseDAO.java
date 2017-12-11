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
    public Connection getConnection(){
        final String url = "jdbc:postgresql://localhost/postgres";
        final String user = "postgres";
        final String password = "password";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}