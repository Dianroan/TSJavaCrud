package DB;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {
    private Connection con;
    public DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taylorswiftdb","root","");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public final Connection getCon() {
        return con;
    }
}
