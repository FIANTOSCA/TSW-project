package ton.unisa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ProductModelDM implements ProductModel {

	private static final String TABLE_NAME = "prodotti";

	/*Metodo per la gestione utilizzato = forza bruta
	 * specificando le operazioni CRUD
	 *doSave(),doRetreiveByKey(),doDelete(),
	 *doRetreiveAll()*/
	@Override
	public synchronized void doSave(ProductBean prodotti) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductModelDM.TABLE_NAME
				+ " (NOME, CATEGORIA, QTA_DISP, MARCA_MOD, PREZZO) VALUES (?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			//preparedStatement.setInt(1, prodotti.getID());
			preparedStatement.setString(1, prodotti.getNOME());
			preparedStatement.setString(2, prodotti.getCATEGORIA());
			preparedStatement.setInt(3, prodotti.getQTA_DISP());
			preparedStatement.setString(4, prodotti.getMARCA_MOD());
			preparedStatement.setFloat(5, prodotti.getPREZZO());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public synchronized ProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setID(rs.getInt("ID"));
				bean.setNOME(rs.getString("NOME"));
				bean.setCATEGORIA(rs.getString("CATEGORIA"));
				bean.setQTA_DISP(rs.getInt("QTA_DISP"));
				bean.setMARCA_MOD(rs.getString("MARCA_MOD"));
				bean.setPREZZO(rs.getFloat("PREZZO"));
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

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductModelDM.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setID(rs.getInt("ID"));
				bean.setNOME(rs.getString("NOME"));
				bean.setCATEGORIA(rs.getString("CATEGORIA"));
				bean.setQTA_DISP(rs.getInt("QTA_DISP"));
				bean.setMARCA_MOD(rs.getString("MARCA_MOD"));
				bean.setPREZZO(rs.getFloat("PREZZO"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}

}