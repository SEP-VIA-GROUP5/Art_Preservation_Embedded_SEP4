package dk.via.sep4.dbConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.apache.logging.log4j.Logger;

/**
 * @author $(Alina Chelmus)
 */
public class DatabaseConnection
{

  private static Logger logger;
  private static Connection getRemoteConnection() {
    if (System.getenv("RDS_HOSTNAME") != null) {
      try {
        Class.forName("org.postgresql.Driver");
        String dbName = System.getenv("Sep4data");
        String userName = System.getenv("postgres");
        String password = System.getenv("gadbjerg42");
        String hostname = System.getenv("aabwkxvvpmrhsh.cuvvqbrgiokt.us-east-1.rds.amazonaws.com");
        String port = System.getenv("1433");
        String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
       // logger.trace("Getting remote connection with connection string from environment variables.");
        Connection con = DriverManager.getConnection(jdbcUrl);
        logger.info("Remote connection successful.");
        return con;
      }
      catch (ClassNotFoundException e) {
        logger.warn(e.toString());}
      catch (SQLException e) {
        logger.warn(e.toString());}
    }
    return null;
  }

}
