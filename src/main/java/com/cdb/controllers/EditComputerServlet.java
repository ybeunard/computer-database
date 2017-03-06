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
import com.cdb.dto.EntrepriseDto;
import com.cdb.dto.OrdinateurDto;
import com.cdb.entities.Entreprise;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.RequeteQueryException;
import com.cdb.services.Impl.GestionEntreprise;
import com.cdb.services.Impl.GestionOrdinateur;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet("/EditComputerServlet")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputerServlet() {
        
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String idStr = request.getParameter("ordinateur");
	    long id = 0;
	    
	    if(idStr != null && !idStr.equals("")) {
	        
	        id = Long.parseLong(idStr);
	        
	    }

	    OrdinateurDto ordinateur = GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR.findOrdinateurById(id);
	    List<EntrepriseDto> entreprises = GestionEntreprise.INSTANCE_GESTION_ENTREPRISE.findEntreprise();
	    request.setAttribute("computer", ordinateur);
	    request.setAttribute("idCompany", GestionEntreprise.INSTANCE_GESTION_ENTREPRISE.findIdEntrepriseByName(ordinateur.getFactory(), entreprises));
	    request.setAttribute("companies", entreprises);
        request.getRequestDispatcher("views/editComputer.jsp").forward(request,
                response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String name = request.getParameter("computerName");

        if (name == null || name.equals("")) {

            request.setAttribute("nameTest", 1);
            doGet(request, response);
            return;

        }
        
        String idStr = request.getParameter("ordinateur");
        long id = 0;
        
        if(idStr != null && !idStr.equals("")) {
            
            id = Long.parseLong(idStr);
            
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

        String idCompanyStr = request.getParameter("company");
        Optional<Entreprise> factory = Optional.empty();

        if (idCompanyStr != null && !idCompanyStr.equals("")) {

            long idCompany = Long.parseLong(idCompanyStr);
            factory = GestionEntreprise.INSTANCE_GESTION_ENTREPRISE
                    .findEntrepriseById(idCompany);

        }

        try {

            if (!factory.isPresent()) {

                GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                        .updateOrdinateur(new Ordinateur.OrdinateurBuilder(name).id(id)
                                .dateIntroduit(introduced)
                                .dateInterrompu(discontinued).build());

            } else {

                GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                        .updateOrdinateur(new Ordinateur.OrdinateurBuilder(name).id(id)
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
