package com.cdb.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdb.controllers.validation.DateValidation;
import com.cdb.entities.Entreprise;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.RequeteQueryException;
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

        request.setAttribute("companies",
                GestionEntreprise.INSTANCE_GESTION_ENTREPRISE.findEntreprise());
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

        String name = request.getParameter("computerName");

        if (name == null || name.equals("")) {

            request.setAttribute("nameTest", 1);
            doGet(request, response);
            return;

        }

        String introducedStr = request.getParameter("introduced");
        LocalDate introduced = null;

        if (introducedStr != null && !introducedStr.equals("")) {

            Optional<LocalDate> introducedOptional = DateValidation
                    .parseDate(introducedStr);

            if (!introducedOptional.isPresent()) {

                request.setAttribute("introducedTest", 1);
                doGet(request, response);
                return;

            }

            introduced = introducedOptional.get();

        }

        String discontinuedStr = request.getParameter("discontinued");
        LocalDate discontinued = null;

        if (discontinuedStr != null && !discontinuedStr.equals("")) {

            Optional<LocalDate> discontinuedOptional = DateValidation
                    .parseDate(discontinuedStr);

            if (!discontinuedOptional.isPresent()) {

                request.setAttribute("discontinuedTest", 1);
                doGet(request, response);
                return;

            }

            discontinued = discontinuedOptional.get();

        }

        if (introduced != null && discontinued != null) {

            if (!DateValidation.isValid(introduced, discontinued)) {

                request.setAttribute("incohérenceTest", 1);
                doGet(request, response);
                return;

            }

        }

        String idStr = request.getParameter("company");
        Optional<Entreprise> factory = Optional.empty();

        if (idStr != null && !idStr.equals("")) {

            long id = Long.parseLong(idStr);
            factory = GestionEntreprise.INSTANCE_GESTION_ENTREPRISE
                    .findEntrepriseById(id);

        }

        try {

            if (!factory.isPresent()) {

                GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                        .createOrdinateur(new Ordinateur.OrdinateurBuilder(name)
                                .dateIntroduit(introduced)
                                .dateInterrompu(discontinued).build());

            } else {

                GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                        .createOrdinateur(new Ordinateur.OrdinateurBuilder(name)
                                .dateIntroduit(introduced)
                                .dateInterrompu(discontinued)
                                .fabricant(factory.get()).build());

            }

        } catch (RequeteQueryException e) {

            if (introduced != null) {

                request.setAttribute("introducedTest", 1);

            }

            if (discontinued != null) {

                request.setAttribute("discontinuedTest", 1);

            }

            doGet(request, response);
            return;

        }

        response.sendRedirect("DashboardServlet");

    }

}
