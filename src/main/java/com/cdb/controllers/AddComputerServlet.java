package com.cdb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdb.services.Impl.GestionEntreprise;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {

        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String action = request.getParameter("action");
	    request.getSession().setAttribute("numPage", 1);
	    
	    if (action != null) {
	        
	        if (action.equals("Add")) {

	            request.getRequestDispatcher("views/addComputer.jsp").forward(request,
	                    response);
	            return;
	            
	        }
	        
	    }
	    
	    request.setAttribute("companies", GestionEntreprise.getInstanceGestionEntreprise().findEntreprise());
        request.getRequestDispatcher("views/addComputer.jsp").forward(request,
                response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	}

}
