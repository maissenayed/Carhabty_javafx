package BackBone.Connexion;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;


public class BackBoneConnexion {

    private String url;
    private String login;
    private String password;
    private Connection connection;
    private static BackBoneConnexion dataSource;
    private static String config;

    private BackBoneConnexion() {
        try {
            url=getProperty("url");
            login=getProperty("login");
            password=getProperty("password");
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

    public static BackBoneConnexion getInstance(String conf) {

        if (dataSource == null) {
            config =conf;
            dataSource = new BackBoneConnexion();
        }
        return dataSource;
    }
    
    
 
    
    
    private String getProperty(String prop) throws FileNotFoundException, IOException
    {
        FileInputStream file=new FileInputStream(config);
        Properties p=new Properties();
        p.load(file);
        return p.getProperty(prop);
    }
    
    
    
    }


