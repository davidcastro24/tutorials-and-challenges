package com.springmvc.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller //subclass of @Component
@RequestMapping("/userForm")
public class UserFormController {

    @RequestMapping("/showForm")
    public String showForm(){
        return "userForm";
    }

    @RequestMapping("/processForm")
    public String processForm(){
        return "printResult";
    }

    /*@RequestMapping("/processFormModel")
    public String reamFormData(HttpServletRequest request, Model model){
        String personName = request.getParameter("personName");
        int personAge = Integer.parseInt(request.getParameter("personAge"));
        String personLastName = request.getParameter("personLastName");
        String personCountry = request.getParameter("personCountry");
        String personSubject = request.getParameter("personSubject");
        model.addAttribute("personName",personName);
        model.addAttribute("personAge",personAge);
        model.addAttribute("personLastName", personLastName);
        model.addAttribute("personCountry",personCountry);
        model.addAttribute("personSubject",personSubject);
        return "printResult";
    }*/

    //bind params automatically*
    @RequestMapping("/processFormModel")
    public String reamFormData( @RequestParam("personName")String personName,
                                @RequestParam("personAge")String personAge,
                                @RequestParam("personLastName") String personLastName,
                                @RequestParam("personCountry") String personCountry,
                                @RequestParam("personSubject") String personSubject,
                                Model model){
        model.addAttribute("personName",personName);
        model.addAttribute("personAge",personAge);
        model.addAttribute("personLastName", personLastName);
        model.addAttribute("personCountry",personCountry);
        model.addAttribute("personSubject",personSubject);
        return "printResult";
    }

}
