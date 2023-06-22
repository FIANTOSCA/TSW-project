package ton.unisa;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductControl
 */

@WebServlet(name="AdminControl", urlPatterns={"/admin"}) 
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ProductModelDS usa il DataSource
	// ProductModelDM usa il DriverManager	
	static boolean isDataSource = false;
	
	static ProductModel model;
	
	static {
		if (isDataSource) {
			model = new ProductModelDS();
		} else {
			model = new ProductModelDM();
		}
	}
	
	public AdminControl() {
		super();
	}

	/*Funzione doGet con 2 eccezioni
	 * 1) specifica delle Servlet
	 * 2) legata all'input/output
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("read")) {
					int id = Integer.parseInt(request.getParameter("id"));
					request.removeAttribute("admin");
					request.setAttribute("admin", model.doRetrieveByKey(id));
					/*Rimozione di un prodotto*/
				} else if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					model.doDelete(id);
					/*Inserimento di un prodotto*/
				} else if (action.equalsIgnoreCase("insert")) {
					//int ID = Integer.parseInt(request.getParameter("id"));
					String NOME = request.getParameter("name");
					String CATEGORIA = request.getParameter("description");
					int QTA_DISP = Integer.parseInt(request.getParameter("quantity"));
					String MARCA_MOD = request.getParameter("mar_mod");
					float PREZZO = Float.parseFloat(request.getParameter("price"));

					ProductBean bean = new ProductBean();
					//bean.setID(ID);
					bean.setNOME(NOME);
					bean.setCATEGORIA(CATEGORIA);
					bean.setQTA_DISP(QTA_DISP);
					bean.setMARCA_MOD(MARCA_MOD);
					bean.setPREZZO(PREZZO);
					model.doSave(bean);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		/*Deleghiamo il resto a ProductView*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
		dispatcher.forward(request, response);
	}

	/*doPost realizzato chiamando la doGet*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
