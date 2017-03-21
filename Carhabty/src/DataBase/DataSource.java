package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by GARCII on 3/12/2017.
 */

public class DataSource {

    private String url = "jdbc:mysql://localhost:3306/pidev";
    private String login = "root";
    private String password = "";
    private Connection connection;
    private static DataSource dataSource;

    private DataSource() {
        try {
            connection = DriverManager.getConnection(url,login,password);
            System.out.println("connection établie");
        } catch (Exception e) {
            System.out.println("connection non établie");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }
}

