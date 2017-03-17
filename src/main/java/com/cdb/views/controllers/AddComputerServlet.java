package com.cdb.views.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdb.views.controllers.validation.Validation;
import com.cdb.model.dto.OrdinateurDto;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.model.mappers.OrdinateurDtoMapper;
import com.cdb.model.mappers.OrdinateurMapper;
import com.cdb.services.Impl.GestionEntreprise;
import com.cdb.services.Impl.GestionOrdinateur;

/**
 * Servlet implementation class AddComputerServlet.
 */
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new adds the computer servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {

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

        try {

            request.setAttribute("companies",
                    GestionEntreprise.INSTANCE_GESTION_ENTREPRISE
                            .findEntreprise());

        } catch (ConnexionDatabaseException | RequeteQueryException e) {

            request.setAttribute("error", 1);

        }

        request.getRequestDispatcher("views/addComputer.jsp").forward(request,
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

        OrdinateurDto ordinateur = OrdinateurDtoMapper
                .recuperationOrdinateurDto(request);

        if (Validation.validationOrdinateurDto(request, ordinateur)) {

            try {

                GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR.createOrdinateur(
                        OrdinateurMapper.recuperationOrdinateur(ordinateur));

            } catch (RequeteQueryException | ConnexionDatabaseException e) {

                request.setAttribute("error", 1);
                doGet(request, response);
                return;

            }

            response.sendRedirect("DashboardServlet");

        } else {

            doGet(request, response);

        }

    }

}
