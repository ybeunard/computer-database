package com.cdb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/addComputer.html")
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

        LOGGER.info("AddComputerController Instantiated");

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
        recoveryDisplayAddComputer(model);
        model.addAttribute("computerDto", new ComputerDto());
        return new ModelAndView("addComputer");

    }

    /**
     * Adds the computer post.
     *
     * @param computerDto
     *            the computer dto
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

            computerService.createComputer(
                    ComputerMapper.recoveryComputer(computerDto));
            return new ModelAndView("redirect:/dashboard.html");

        } else {

            recoveryDisplayAddComputer(model);
            return new ModelAndView("addComputer");

        }

    }

    /**
     * Recuperation model affichage add computer.
     *
     * @param model
     *            the model
     */
    private void recoveryDisplayAddComputer(Model model) {

        model.addAttribute("companies", companyService.findCompanies());

    }

}
