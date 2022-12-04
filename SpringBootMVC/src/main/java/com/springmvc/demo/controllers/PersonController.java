package com.springmvc.demo.controllers;

import com.springmvc.demo.classes.DataForDisplayedLists;
import com.springmvc.demo.classes.Person;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/newUserForm")
public class PersonController {

    @InitBinder //called in every web request
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @RequestMapping("/showForm")
    public String showForm(Model personModel, Model dataForDisplayedListsModel){
        Person person = new Person();
        DataForDisplayedLists dataForDisplayedLists = new DataForDisplayedLists();
        personModel.addAttribute("person",person);
        dataForDisplayedListsModel.addAttribute("dataForDisplayedLists", dataForDisplayedLists);
        return "personUserForm";
    }

    @RequestMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("person") Person person,
                              BindingResult bindingResult,@ModelAttribute("dataForDisplayedLists") DataForDisplayedLists dataForDisplayedLists){
        System.out.println(person.toString());
        if (bindingResult.hasErrors())
            return "personUserForm";
        return "personConfirmation";
    }

}
