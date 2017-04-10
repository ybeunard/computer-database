package com.cdb.controllers;

import javax.servlet.http.HttpServletRequest;
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

import com.cdb.controllers.validation.ComputerDtoValidation;
import com.cdb.model.dto.ComputerDto;
import com.cdb.services.Impl.CompanyService;
import com.cdb.services.Impl.ComputerService;
import com.cdb.utils.Parse;
import com.cdb.utils.mappers.ComputerMapper;

/**
 * The Class EditComputerController.
 */
@Controller
@RequestMapping("/editComputer.htm")
public class EditComputerController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(EditComputerController.class);

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
     * Instantiates a new edits the computer controller.
     */
    public EditComputerController() {

        LOGGER.info("EditComputerController Instantiated");

    }

    /**
     * Edits the computer get.
     *
     * @param request
     *            the request
     * @param model
     *            the model
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView editComputerGet(HttpServletRequest request,
            Model model) {

        LOGGER.info("EditComputerController: GET");
        recoveryDisplayEditComputer(request, model);
        model.addAttribute("computerDto", new ComputerDto());
        return new ModelAndView("editComputer");

    }

    /**
     * Edits the computer post.
     *
     * @param ordinateurDto
     *            the ordinateur dto
     * @param result
     *            the result
     * @param request
     *            the request
     * @param model
     *            the model
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView editComputerPost(
            @ModelAttribute("computerDto") @Validated ComputerDto computerDto,
            BindingResult result, HttpServletRequest request, Model model) {

        LOGGER.info("EditComputerController: POST");

        if (!result.hasErrors()) {

            computerService.updateComputer(
                        ComputerMapper.recoveryComputer(computerDto));
            return new ModelAndView("redirect:/dashboard.htm");

        } else {

            recoveryDisplayEditComputer(request, model);
            return new ModelAndView("editComputer");

        }

    }

    /**
     * Recuperation model affichage edit computer.
     *
     * @param request
     *            the request
     * @param model
     *            the model
     */
    private void recoveryDisplayEditComputer(
            HttpServletRequest request, Model model) {

        long id = Parse.parseLong(request.getParameter("id"), 0);
        model.addAttribute("computer", computerService.findComputerById(id));
        model.addAttribute("companies", companyService.findCompanies());

    }

}
