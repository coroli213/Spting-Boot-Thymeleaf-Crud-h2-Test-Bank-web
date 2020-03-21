package com.howtodoinjava.demo.controller;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.Terminal;
import com.howtodoinjava.demo.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Terminal")
public class TerminalMvcController
{
    @Autowired
    TerminalService service;

    @RequestMapping
    public String getAllTerminals(Model model)
    {
        List<Terminal> list = service.getAllTerminals();

        model.addAttribute("terminals", list);
        return "Terminal/list-terminals";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editTerminalById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException
    {
        if (id.isPresent())
        {
            Terminal entity = service.getTerminalById(id.get());
            model.addAttribute("terminal", entity);
        }
        else
        {
            model.addAttribute("terminal", new Terminal());
        }
        return "Terminal/add-edit-terminal";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteTerminalById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException
    {
        service.deleteTerminalById(id);
        return "redirect:/Terminal";
    }

    @RequestMapping(path = "/createTerminal", method = RequestMethod.POST)
    public String createOrUpdateTerminal(Terminal Terminal)
    {
        service.createOrUpdateTerminal(Terminal);
        return "redirect:/Terminal";
    }
}
