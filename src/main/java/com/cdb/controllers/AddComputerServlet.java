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
import com.cdb.controllers.validation.Parse;
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

        if (addPost(request)) {

            response.sendRedirect("DashboardServlet");

        } else {

            doGet(request, response);

        }

    }

    /**
     * Adds the post.
     *
     * @param request
     *            the request
     * @return true, if successful
     */
    public boolean addPost(HttpServletRequest request) {

        String name = request.getParameter("computerName");

        if (name == null || name.equals("")) {

            request.setAttribute("nameTest", 1);
            return false;

        }

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
                        .createOrdinateur(new Ordinateur.OrdinateurBuilder(name)
                                .dateIntroduit(introduced.get())
                                .dateInterrompu(discontinued.get()).build());

            } else {

                GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                        .createOrdinateur(new Ordinateur.OrdinateurBuilder(name)
                                .dateIntroduit(introduced.get())
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
