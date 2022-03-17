package com.kargus.Customer.Website.controllers;

import com.kargus.Customer.Website.models.Customer;
import com.kargus.Customer.Website.models.Scooter;
import com.kargus.Customer.Website.models.ScooterSelection;
import com.kargus.Customer.Website.services.CustomerService;
import com.kargus.Customer.Website.services.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ScooterService scooterService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        // Here you call the service to retrieve all the customers
        final List<Customer> customerList = customerService.getAllCustomers();
        // Once the customers are retrieved, you can store them in model and return it to the view
        model.addAttribute("customerList", customerList);
        return "index";
    }

    @GetMapping("/new")
    public String showNewCustomerPage(Model model) {
        // Here a new (empty) Customer is created and then sent to the view
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new-customer";
    }

    @PostMapping(value = "/save")
    // As the Model is received back from the view, @ModelAttribute
    // creates a Customer based on the object you collected from
    // the HTML page above
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    // The path variable "id" is used to pull a customer from the database
    public ModelAndView showEditCustomerPage(@PathVariable(name = "id") Long id) {
        // Since the previous methods use Model, this one uses ModelAndView
        // to get some experience using both. Model is more common these days,
        // but ModelAndView accomplishes the same thing and can be useful in
        // certain circumstances. The view name is passed to the constructor.
        ModelAndView mav = new ModelAndView("edit-customer");
        if (customerService.getCustomer(id) == null) {
            ModelAndView errorModelAndView = new ModelAndView("error-page");
            errorModelAndView.addObject("error", HttpStatus.NOT_FOUND);
            return errorModelAndView;
        }

        Customer customer = customerService.getCustomer(id);
        mav.addObject("customer", customer);
        return mav;
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable(name = "id") Long id, @ModelAttribute("customer") Customer customer, Model model) {
        if (customerService.getCustomer(id) == null) {
            model.addAttribute("error",HttpStatus.NOT_FOUND);
            return "error-page";
        }
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") Long id, Model model) {
        if (customerService.getCustomer(id) == null) {
            model.addAttribute("error", HttpStatus.NOT_FOUND);

            return "error-page";
        }

        customerService.deleteCustomer(id);
        return "redirect:/";
    }

    @GetMapping("/assign/{id}")
    public String getScooterAssignment(@PathVariable(name = "id") Long id, Model model) {
        if (customerService.getCustomer(id) == null) {
            model.addAttribute("error", HttpStatus.NOT_FOUND);

            return "error-page";
        }
        model.addAttribute("customerId", id);
        final List<Scooter> availableScooterList = scooterService.getAllScooters();
        final List<Scooter> unavailableScooterList = scooterService.getAllScooters();

        availableScooterList.removeIf(scooter -> scooter.getCustomer() != null);
        unavailableScooterList.removeIf(scooter -> scooter.getCustomer() == null);


        model.addAttribute("availableScooterList", availableScooterList);
        model.addAttribute("unavailableScooterList", unavailableScooterList);
        model.addAttribute("scooterSelection", new ScooterSelection());

        return "assign-scooter";
    }

    @PostMapping("/assign/{customerId}")
    public String assignScooterToCustomer(@PathVariable(name = "customerId") Long id, @ModelAttribute(name = "scooterSelection") ScooterSelection scooterSelection, Model model) {
        if (scooterSelection.getId() == null) {
            model.addAttribute("error",
                    "No Scooter Selected.");

            return "error-page";
        }
        customerService.assignScooterToCustomer(id, scooterSelection.getId());


        return "redirect:/";
    }

    @RequestMapping("/remove/{customerId}")
    public String removeScooterFromCustomer(@PathVariable(name = "customerId") Long id, @ModelAttribute(name = "scooterSelection") ScooterSelection scooterSelection, Model model) {
        if (customerService.getCustomer(id) == null) {
            model.addAttribute("error",
                    "Action not available.");

            return "error-page";
        }

        customerService.removeScooterFromCustomer(id);


        return "redirect:/";
    }

}
