import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// ▬ "Making" the "Connection"
//      → with the "Database" ▬
public class DB {

    // ▬ The "mycon()" Method ▬
    public static Connection mycon(){
        // ▼ "Objects" ▼
        Connection con = null;


        try{
            // ▼ Creating the"Java DataBase Connection" ("JDBC") ▼
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ▼ "Connecting" to the "pointofsales_db" Database ▼
            con = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost/pointofsales_db",
                            "root",
                            ""
                    );

            return con;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
