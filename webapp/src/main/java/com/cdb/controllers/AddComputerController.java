package com.cdb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cdb.model.dto.ComputerDto;
import com.cdb.services.Impl.CompanyService;
import com.cdb.services.Impl.ComputerService;
import com.cdb.utils.mappers.ComputerMapper;
import com.cdb.controllers.validation.ComputerDtoValidation;

/**
 * The Class AddComputerController.
 */
@Controller
@RequestMapping("/addComputer.htm")
public class AddComputerController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(AddComputerController.class);

    /** The gestion ordinateur. */
    @Autowired
    ComputerService computerService;

    /**
     * Gets the gestion ordinateur.
     *
     * @return the gestion ordinateur
     */
    public ComputerService getComputerService() {

        return computerService;

    }

    /** The gestion entreprise. */
    @Autowired
    CompanyService companyService;

    /**
     * Gets the gestion entreprise.
     *
     * @return the gestion entreprise
     */
    public CompanyService getCompanyService() {

        return companyService;

    }

    /** The ordinateur dto validation. */
    @Autowired
    ComputerDtoValidation computerDtoValidation;

    /**
     * Inits the binder.
     *
     * @param binder
     *            the binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        binder.setValidator(computerDtoValidation);

    }

    /**
     * Instantiates a new adds the computer controller.
     */
    public AddComputerController() {

        LOGGER.info("AddComputerController instancié");

    }

    /**
     * Adds the computer get.
     *
     * @param model
     *            the model
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView addComputerGet(Model model) {

        LOGGER.info("AddComputerController: GET");
        recuperationModelAffichageAddComputer(model);
        model.addAttribute("computerDto", new ComputerDto());
        return new ModelAndView("addComputer");

    }

    /**
     * Adds the computer post.
     *
     * @param ordinateurDto
     *            the ordinateur dto
     * @param result
     *            the result
     * @param model
     *            the model
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addComputerPost(
            @ModelAttribute("computerDto") @Validated ComputerDto computerDto,
            BindingResult result, Model model) {

        LOGGER.info("AddComputerController: POST");

        if (!result.hasErrors()) {

            try {

                computerService.createComputer(
                        ComputerMapper.recoveryComputer(computerDto));

            } catch (DataAccessException e) {

                model.addAttribute("error",
                        "Erreur: l'ordinateur n'a pas été créer");

            }

            return new ModelAndView("redirect:/dashboard.htm");

        } else {

            recuperationModelAffichageAddComputer(model);
            return new ModelAndView("addComputer");

        }

    }

    /**
     * Recuperation model affichage add computer.
     *
     * @param model
     *            the model
     */
    private void recuperationModelAffichageAddComputer(Model model) {

        try {

            model.addAttribute("companies", companyService.findCompanies());

        } catch (DataAccessException e) {

            model.addAttribute("error",
                    "Erreur lors du chargement des entreprises");

        }

    }

}
