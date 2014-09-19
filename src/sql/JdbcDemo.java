import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.DB;

public class JdbcDemo {

    Statement statement = null;
    Connection connection = null;
    public static final String userName = "cphmp259";
    public static final String pw = "cphmp259";
    public static final String dbms = "oracle";
    static final String driverDerby = "org.apache.derby.jdbc.EmbeddedDriver";
    static final String driverOracle = "oracle.jdbc.driver.OracleDriver";
    static final String connectionOracle = "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat";
    static final String connectionDerby = "jdbc:derby://localhost:1527/myLocalDerbyDatabase";

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", pw);
        if (JdbcDemo.dbms.equals("oracle")) {
            conn = DriverManager.getConnection(connectionOracle, connectionProps);
        } else if (JdbcDemo.dbms.equals("derby")) {
            conn = DriverManager.getConnection(connectionDerby, connectionProps);
        }
        System.out.println("Connected to database");
        return conn;
    }

    public void personQuery() throws SQLException {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeQuery("SELECT * FROM PERSON");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                System.out.println("Id:" + rs.getInt(1) + ", " + rs.getString("FIRST_NAME") + ", "
                        + rs.getString("LAST_NAME"));
            }
        }
    }

    public void insertCol() throws SQLException {
        try {
            Class.forName(DB.driver);

            connection = DriverManager.getConnection(DB.URL, DB.ID, DB.PW);

            statement = connection.createStatement();

            String insertSQL = "ALTER TABLE Person ADD mobile char(8)";
            //=== Execute the statement and retrieve 
            //	a count of how many rows was inserted
            
            int rows = statement.executeUpdate(insertSQL);

            //=== Validate the result
            if (rows == 1) {
                System.out.println("One row inserted!");
            } else {
                System.out.println("No row inserted (fail)");
            }
        } catch (Exception ee) {
            System.out.println("fail");
            System.err.println(ee);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public static void main(String[] args) {
        JdbcDemo test = new JdbcDemo();
        try {
            test.insertCol();
            test.personQuery();

        } catch (SQLException ex) {
            Logger.getLogger(JdbcDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
