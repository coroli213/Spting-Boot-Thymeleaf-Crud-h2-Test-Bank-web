package com.howtodoinjava.demo.controller;

import com.howtodoinjava.demo.model.Client;
import com.howtodoinjava.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    ClientService service;

    @RequestMapping
    public String getAllClients(Model model)
    {
        List<Client> list = service.getAllClients();

        model.addAttribute("clients", list);
        return "home";
    }
}