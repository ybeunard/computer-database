package com.cdb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dto.PageDto;
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
    
    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(DashboardServlet.class);

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

        PageDto pageCourante = (PageDto) request.getSession()
                .getAttribute("page");
        int numPage;
        int nbParPage;
        String filtre;

        if (pageCourante == null) {

            numPage = 1;
            nbParPage = 10;
            filtre = "";

        } else {

            numPage = pageCourante.getNumPage();
            nbParPage = pageCourante.getNbParPage();
            filtre = pageCourante.getFiltre();
            LOGGER.info("recuperation des informations de page courante : " + numPage + " , " + nbParPage + " , " + filtre);

        }

        String numPageStr = request.getParameter("numPage");

        if (numPageStr != null && !numPageStr.equals("")) {

            numPage = Integer.parseInt(numPageStr);

        }

        String nbParPageStr = request.getParameter("nbParPage");

        if (nbParPageStr != null && !nbParPageStr.equals("")) {

            nbParPage = Integer.parseInt(nbParPageStr);

        }
        
        String filtreStr = request.getParameter("search");
        
        if (filtreStr != null && !filtreStr.equals("")) {
            
            filtre = filtreStr;
            
        }
        
        String resetFiltre = request.getParameter("resetFiltre");
        
        if(resetFiltre != null && resetFiltre.equals("OK")) {
            
            filtre = "";
            numPage = 1;
            
        }
        
        PageDto page = GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                .findOrdinateurByPage(numPage, nbParPage, filtre);
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

    }

}
