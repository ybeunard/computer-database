package com.cdb.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdb.controllers.validation.DateValidation;
import com.cdb.controllers.validation.Parse;
import com.cdb.dto.EntrepriseDto;
import com.cdb.dto.OrdinateurDto;
import com.cdb.entities.Entreprise;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.RequeteQueryException;
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
        OrdinateurDto ordinateur = GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                .findOrdinateurById(id);
        List<EntrepriseDto> entreprises = GestionEntreprise.INSTANCE_GESTION_ENTREPRISE
                .findEntreprise();
        request.setAttribute("computer", ordinateur);
        request.setAttribute("idCompany",
                GestionEntreprise.INSTANCE_GESTION_ENTREPRISE
                        .findIdEntrepriseByName(ordinateur.getFactory(),
                                entreprises));
        request.setAttribute("companies", entreprises);
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

        if (editPost(request)) {

            response.sendRedirect("DashboardServlet");

        } else {

            doGet(request, response);

        }

    }

    /**
     * Edits the post.
     *
     * @param request
     *            the request
     * @return true, if successful
     */
    public boolean editPost(HttpServletRequest request) {

        String name = request.getParameter("computerName");

        if (name == null || name.equals("")) {

            request.setAttribute("nameTest", 1);
            return false;

        }

        long id = Parse.parseLong(request.getParameter("ordinateur"), 0);
        Optional<LocalDate> introduced = Parse
                .parseDate(request.getParameter("introduced"), request);

        if (introduced == null) {

            request.setAttribute("introducedTest", 1);
            return false;

        }

        Optional<LocalDate> discontinued = Parse
                .parseDate(request.getParameter("discontinued"), request);

        if (discontinued == null) {

            request.setAttribute("discontinuedTest", 1);
            return false;

        }

        if (introduced.isPresent() && discontinued.isPresent()) {

            if (!DateValidation.isValid(introduced.get(), discontinued.get())) {

                request.setAttribute("incohérenceTest", 1);
                return false;

            }

        }

        Optional<Entreprise> factory = Parse
                .parseFactory(request.getParameter("company"));

        try {

            if (!factory.isPresent()) {

                GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                        .updateOrdinateur(new Ordinateur.OrdinateurBuilder(name)
                                .id(id).dateIntroduit(introduced.get())
                                .dateInterrompu(discontinued.get()).build());

            } else {

                GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                        .updateOrdinateur(new Ordinateur.OrdinateurBuilder(name)
                                .id(id).dateIntroduit(introduced.get())
                                .dateInterrompu(discontinued.get())
                                .fabricant(factory.get()).build());

            }

        } catch (RequeteQueryException e) {

            if (introduced != null) {

                request.setAttribute("introducedTest", 1);

            }

            if (discontinued != null) {

                request.setAttribute("discontinuedTest", 1);

            }

            return false;

        }

        return true;

    }

}
