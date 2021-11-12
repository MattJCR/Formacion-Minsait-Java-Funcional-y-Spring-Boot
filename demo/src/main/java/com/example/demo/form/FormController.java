package com.example.demo.form;

import java.util.List;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class FormController implements WebMvcConfigurer {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/form")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @GetMapping("/customer")
    public String showForm(CustomerForm customerForm) {
        return "customer";
    }

    @PostMapping("/form")
    public String checkPersonInfo(@Validated PersonForm personForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "redirect:/results";
    }

    @PostMapping("/customer")
    public String checkCustomerInfo(@Validated CustomerForm customerForm, BindingResult bindingResult, Model model) {
        System.out.println(customerForm.toString());
        List<Customer> customers = customerRepository.findByEmail(customerForm.getEmail());
        if (bindingResult.hasErrors() || customers.size() > 0) {
            System.out.println("Error: " + customerForm.toString());
            model.addAttribute("emailExist", "Email already exist.");
            return "customer";
        }
        customerRepository
                .save(new Customer(customerForm.getFirstName(), customerForm.getLastName(), customerForm.getEmail()));
        return "redirect:/customers";
    }
}