package com.cdb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdb.controllers.validation.Parse;
import com.cdb.controllers.validation.Validation;
import com.cdb.dto.OrdinateurDto;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.mappers.OrdinateurDtoMapper;
import com.cdb.mappers.OrdinateurMapper;
import com.cdb.services.Impl.GestionEntreprise;
import com.cdb.services.Impl.GestionOrdinateur;

/**
 * Servlet implementation class EditComputerServlet.
 */
@WebServlet("/EditComputerServlet")
public class EditComputerServlet extends HttpServlet {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new edits the computer servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public EditComputerServlet() {

        super();

    }

    /**
     * Do get.
     *
     * @param request
     *            the request
     * @param response
     *            the response
     * @throws ServletException
     *             the servlet exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        long id = Parse.parseLong(request.getParameter("ordinateur"), 0);
        
        try {
            
            OrdinateurDto ordinateur = GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                    .findOrdinateurById(id);
            
            if(ordinateur == null) {
                
                response.sendRedirect("DashboardServlet");
                return;
                
            }
            
            request.setAttribute("computer", ordinateur);
            request.setAttribute("companies", GestionEntreprise.INSTANCE_GESTION_ENTREPRISE
                    .findEntreprise());
            
        } catch (ConnexionDatabaseException | RequeteQueryException e) {
            
            request.setAttribute("error", 1);
            
        }
        
        request.getRequestDispatcher("views/editComputer.jsp").forward(request,
                response);

    }

    /**
     * Do post.
     *
     * @param request
     *            the request
     * @param response
     *            the response
     * @throws ServletException
     *             the servlet exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        OrdinateurDto ordinateur = OrdinateurDtoMapper.recuperationOrdinateurDto(request);

        if (Validation.validationOrdinateurDto(request, ordinateur)) {

            try {
                
                GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                .updateOrdinateur(OrdinateurMapper.recuperationOrdinateur(ordinateur));
                
            } catch (RequeteQueryException | ConnexionDatabaseException e) {

                request.setAttribute("error", 1);
                doGet(request,response);
                return;

            }

            response.sendRedirect("DashboardServlet");

        } else {

            doGet(request, response);

        }

    }

}
