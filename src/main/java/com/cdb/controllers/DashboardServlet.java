package com.cdb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdb.services.Impl.GestionOrdinateur;

/**
 * Servlet implementation class DashboardServlet.
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new dashboard servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {

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

        String action = request.getParameter("action");
        String numPageStr = request.getParameter("numPage");
        int numPage = 1;

        if (numPageStr != null && !numPageStr.equals("")) {

            numPage = Integer.parseInt(numPageStr);

        }

        String nbParPageStr = request.getParameter("nbParPage");
        int nbParPage = 10;

        if (nbParPageStr != null && !nbParPageStr.equals("")) {

            nbParPage = Integer.parseInt(nbParPageStr);

        }

        if (action != null) {

        }

        request.setAttribute("pagination", GestionOrdinateur
                .getInstanceGestionOrdinateur().count(numPage, nbParPage));
        request.setAttribute("allComputer",
                GestionOrdinateur.getInstanceGestionOrdinateur()
                        .findOrdinateurByPage(numPage, nbParPage));
        request.getRequestDispatcher("views/dashboard.jsp").forward(request,
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

        doGet(request, response);

    }

}
