/*
 * 
 */
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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        request.getSession().setAttribute("numPage", 1);

        if (action != null) {

            if (action.equals("Add")) {

                String name = request.getParameter("computerName");

                if (name == null || name.equals("")) {

                    request.setAttribute("nameTest", 1);
                    request.setAttribute("companies", GestionEntreprise
                            .getInstanceGestionEntreprise().findEntreprise());
                    request.getRequestDispatcher("views/addComputer.jsp")
                            .forward(request, response);
                    return;

                }

                String introducedStr = request.getParameter("introduced");
                LocalDate introduced = null;

                if (introducedStr != null && !introducedStr.equals("")) {

                    Optional<LocalDate> introducedOptional = DateValidation
                            .parseDate(introducedStr);

                    if (!introducedOptional.isPresent()) {

                        request.setAttribute("introducedTest", 1);
                        request.setAttribute("companies",
                                GestionEntreprise.getInstanceGestionEntreprise()
                                        .findEntreprise());
                        request.getRequestDispatcher("views/addComputer.jsp")
                                .forward(request, response);
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
                        request.setAttribute("companies",
                                GestionEntreprise.getInstanceGestionEntreprise()
                                        .findEntreprise());
                        request.getRequestDispatcher("views/addComputer.jsp")
                                .forward(request, response);
                        return;

                    }

                    discontinued = discontinuedOptional.get();

                }

                if (introduced != null && discontinued != null) {

                    if (!DateValidation.isValid(introduced, discontinued)) {

                        request.setAttribute("incohérenceTest", 1);
                        request.setAttribute("companies",
                                GestionEntreprise.getInstanceGestionEntreprise()
                                        .findEntreprise());
                        request.getRequestDispatcher("views/addComputer.jsp")
                                .forward(request, response);
                        return;

                    }

                }

                String company = request.getParameter("company");
                Optional<Entreprise> factory = Optional.empty();

                if (company != null && !company.equals("")) {

                    factory = GestionEntreprise.getInstanceGestionEntreprise()
                            .findEntrepriseByName(company);

                }

                try {

                    if (!factory.isPresent()) {

                        GestionOrdinateur.getInstanceGestionOrdinateur()
                                .createOrdinateur(
                                        new Ordinateur.OrdinateurBuilder(name)
                                                .dateIntroduit(introduced)
                                                .dateInterrompu(discontinued)
                                                .build());

                    } else {

                        GestionOrdinateur.getInstanceGestionOrdinateur()
                                .createOrdinateur(
                                        new Ordinateur.OrdinateurBuilder(name)
                                                .dateIntroduit(introduced)
                                                .dateInterrompu(discontinued)
                                                .fabricant(factory.get())
                                                .build());

                    }

                } catch (RequeteQueryException e) {

                    if (introduced != null) {

                        request.setAttribute("introducedTest", 1);

                    }

                    if (discontinued != null) {

                        request.setAttribute("discontinuedTest", 1);

                    }

                    request.setAttribute("companies", GestionEntreprise
                            .getInstanceGestionEntreprise().findEntreprise());
                    request.getRequestDispatcher("views/addComputer.jsp")
                            .forward(request, response);
                    return;


                }

                request.setAttribute("creationOk", 1);

            }

        }

        request.setAttribute("companies",GestionEntreprise.getInstanceGestionEntreprise().findEntreprise());request.getRequestDispatcher("views/addComputer.jsp").forward(request,response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

}
