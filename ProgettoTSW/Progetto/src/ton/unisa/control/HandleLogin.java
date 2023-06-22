package ton.unisa.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ton.unisa.DriverManagerConnectionPool;
import ton.unisa.userBean;
import ton.unisa.userDS;

@WebServlet(name="HandleLogin", urlPatterns={"/connection"})

public class HandleLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection sqlConn = null;
    private PreparedStatement statement = null;
  
    public HandleLogin() {
        super();
    }


    /*doGet realizzato chiamando la doPost*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
        {
    		doPost(request, response);
        }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
        {
            String forward = "/ProductView.jsp";
            String forward2 = "/userLogged.jsp";
            String loginError = "/invalidLogin.html";
            String user = request.getParameter("username");
            String password = request.getParameter("password");
            String result = null;
            
            RequestDispatcher dispatcher = null;
            try
            {
                userDS uDS = new userDS();
                
                userBean uB = uDS.doCheckLogin(user, password);
                
                if (uB.getId() != -1) {
                	result = uB.getUsername() + " " + uB.getSurname();
               
                	
                	request.setAttribute("result", result);
                	
                	if (uB.getIsadmin()!=1) {
                		dispatcher = getServletContext().getRequestDispatcher(forward2);
                	}
                	else {
                		dispatcher = getServletContext().getRequestDispatcher(forward);
                	}
                }
                else {
                	dispatcher = getServletContext().getRequestDispatcher(loginError);
                }
                	
            }
            catch(Exception e)
            {
                request.setAttribute("error", e.getLocalizedMessage());
            }
            
            request.setAttribute("username", user);
            request.setAttribute("password", password);
            dispatcher.forward(request, response);
        }
}
