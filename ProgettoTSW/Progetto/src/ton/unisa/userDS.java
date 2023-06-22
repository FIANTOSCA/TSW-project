package ton.unisa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class userDS extends userDAO 
{
	private Connection sqlConn = null;
	private PreparedStatement statement = null;
	
	public userDS() {
		try {
		sqlConn = DriverManagerConnectionPool.getConnection();
		}
		catch (SQLException e) {
			
		}
	}
	
	/*Funzione per la verifica del login dell'utente*/
	public userBean doCheckLogin(String user, String pass) throws SQLException {
		userBean uB = new userBean();
		String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
		
        try {
	        statement = sqlConn.prepareStatement(sql);
	        
	        statement.setString(1, user);
	        statement.setString(2, pass);
	        
	        ResultSet rs = statement.executeQuery();
	        
	        if (rs.next())
	        {
	        	uB = new userBean();
	        	uB.setId(rs.getInt("Id"));
	        	uB.setUsername(rs.getString("Username"));
	        	uB.setSurname(rs.getString("Surname"));
	        	uB.setPassword(rs.getString("Password"));
	        	uB.setIsadmin(rs.getInt("isadmin"));
	        }
        } catch (SQLException e) {
        	throw e;
        }
		return uB;
	}

}
