package com.cdb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdb.dto.DashboardDto;
import com.cdb.dto.PageDto;
import com.cdb.mappers.DashboardDtoMapper;
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

        DashboardDto dashboard = DashboardDtoMapper.INSTANCE_DASHBOARD_DTO_MAPPER
                .recuperationDashboardRequestGet(request);
        PageDto page = GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                .findOrdinateurByPage(dashboard.getNumPage(),
                        dashboard.getNbParPage(), dashboard.getFiltre());
        request.setAttribute("page", page);
        request.getSession().setAttribute("page", page);
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

        String selection = request.getParameter("selection");
        String[] aSupprimer = selection.split(",");

        for (String supprimeStr : aSupprimer) {

            long supprime = 0;

            if (!supprimeStr.equals("")) {

                supprime = Long.parseLong(supprimeStr);

            }

            GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                    .suppressionOrdinateur(supprime);

        }

        doGet(request, response);

    }

}
