package com.kargus.Customer.Website.controllers;

import com.kargus.Customer.Website.models.Scooter;
import com.kargus.Customer.Website.services.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/scooter")
public class ScooterController {

    @Autowired
    ScooterService scooterService;


    @GetMapping
    public String viewHomePage(Model model) {
        // Here you call the service to retrieve all the customers
        final List<Scooter> scooterList = scooterService.getAllScooters();
        // Once the customers are retrieved, you can store them in model and return it to the view
        model.addAttribute("scooterList", scooterList);
        return "index-scooter";
    }

    @GetMapping("/new")
    public String showNewScooterPage(Model model) {
        // Here a new (empty) Scooter is created and then sent to the view
        Scooter scooter = new Scooter();
        model.addAttribute("scooter", scooter);
        return "new-scooter";
    }

    @PostMapping(value = "/save")
    // As the Model is received back from the view, @ModelAttribute
    // creates a Scooter based on the object you collected from
    // the HTML page above
    public String saveScooter(@ModelAttribute("scooter") Scooter scooter) {
        scooterService.saveScooter(scooter);
        return "redirect:/scooter";
    }

    @GetMapping("/edit/{id}")
    // The path variable "id" is used to pull a customer from the database
    public ModelAndView showEditScooterPage(@PathVariable(name = "id") Long id) {
        // Since the previous methods use Model, this one uses ModelAndView
        ModelAndView mav = new ModelAndView("edit-scooter");
        if (scooterService.getScooter(id) == null) {
            ModelAndView errorModelAndView = new ModelAndView("error-page");           //TODO implemented check for null and handled error
            errorModelAndView.addObject("error", HttpStatus.NOT_FOUND);
            return errorModelAndView;
        }

        Scooter scooter = scooterService.getScooter(id);
        mav.addObject("scooter", scooter);
        return mav;
    }

    @PostMapping("/update/{id}")
    public String updateScooter(@PathVariable(name = "id") Long id, @ModelAttribute("scooter") Scooter scooter, Model model) {
        if (scooterService.getScooter(id) == null) {
            model.addAttribute("error",
                    "Can Not Update");
            return "error-page";
        }
        scooterService.saveScooter(scooter);
        return "redirect:/scooter";
    }

    @RequestMapping("/delete/{id}")
    public String deleteScooter(@PathVariable(name = "id") Long id) {
        if (scooterService.getScooter(id) == null) {
            return "error-page";
        }

        scooterService.deleteScooter(id);
        return "redirect:/scooter";
    }


}
