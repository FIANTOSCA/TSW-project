package ton.unisa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/*Implementazione del DAO = DataAccessObject*/
public class userDAO {
	static String TABLE_NAME = "USERS";
	
	public synchronized userBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		userBean bean = new userBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE CODE = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("Id"));
				bean.setUsername(rs.getString("Username"));
				bean.setSurname(rs.getString("Surname"));
				bean.setPassword(rs.getString("Password"));
				bean.setIsadmin(rs.getInt("isadmin"));
				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

}
