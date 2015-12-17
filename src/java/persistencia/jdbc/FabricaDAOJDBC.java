package persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FabricaDAOJDBC {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private final String url = "jdbc:mysql://localhost/restaurante";
	private final String user = "root";
	private final String pass = "oxente";
        
        private static FabricaDAOJDBC instance = null;
	
	private FabricaDAOJDBC() throws SQLException{
		try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(url,user,pass);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FabricaDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
	}
        
        public Connection getConnection(){
            return this.con;
        }
	
	public static FabricaDAOJDBC getInstance() throws SQLException{
		if(instance == null){
			instance = new FabricaDAOJDBC();
		}
		return instance;
	}
	private void destroyFactory() throws SQLException{
		con.close();		
	}
	
	public static void destroyInstance() throws SQLException{
		if(instance != null){
			instance.destroyFactory();
			instance = null;
		}
	}
}


