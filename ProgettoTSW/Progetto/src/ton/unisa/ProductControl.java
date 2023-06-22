package ton.unisa;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ton.unisa.Cart;

//Questa servlet verra utilizzata per il lato utente

/**
 * Servlet implementation class ProductControl
 */
public class ProductControl extends HttpServlet {
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
	
	public ProductControl() {
		super();
	}

	/*Funzione doGet con 2 eccezioni
	 * 1) specifica delle Servlet
	 * 2) legata all'input/output
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		

		String action = request.getParameter("action");

		try {
			if (action != null) {
				/*In caso di azione per aggiungere un prodotto*/
				if (action.equalsIgnoreCase("addC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.addProduct(model.doRetrieveByKey(id));
					/*In caso di azione per eliminare un prodotto*/
				} else if (action.equalsIgnoreCase("deleteC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.deleteProduct(model.doRetrieveByKey(id));
					/*In caso di azione per ricerca con Id*/
				} else if (action.equalsIgnoreCase("read")) {
					int id = Integer.parseInt(request.getParameter("id"));
					request.removeAttribute("prodotti");
					request.setAttribute("prodotti", model.doRetrieveByKey(id));
					/*In caso di azione per cancellare tramite Id*/
				} else if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					model.doDelete(id);
					/*In caso di azione di inserimento*/
				} else if (action.equalsIgnoreCase("insert")) {
					//int ID = Integer.parseInt(request.getParameter("id"));
					String NOME = request.getParameter("NOME");
					String CATEGORIA = request.getParameter("CATEGORIA");
					int QTA_DISP = Integer.parseInt(request.getParameter("QTA_DISP"));
					String MARCA_MOD = request.getParameter("MARCA_MOD");
					float PREZZO = Float.parseFloat(request.getParameter("PREZZO"));

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
		
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		/*Deleghiamo il resto a userLogged*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userLogged.jsp");
		dispatcher.forward(request, response);
	}

	/*doPost realizzato chiamando la doGet*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
