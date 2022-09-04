package com.thiagoti.messageapi.contacts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;
    
    @GetMapping
    public ModelAndView getAll() throws Exception{
        ModelAndView model = new ModelAndView("contacts");
        model.addObject("contacts", service.getAll());
        return model;
    }
    
}
