package com.howtodoinjava.demo.controller;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.Operation;
import com.howtodoinjava.demo.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Operation")
public class OperationMvcController
{
    @Autowired
    OperationService service;

    @RequestMapping
    public String getAllOperations(Model model)
    {
        List<Operation> list = service.getAllOperation();

        model.addAttribute("operations", list);
        return "Operation/list-operations";
    }

    @RequestMapping(path = "/getid/{id}")
    public String GetOperationsById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException
    {
        List<Operation> list = service.getAllOperationById(id);

        model.addAttribute("operations", list);

        return "Operation/list-operations";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editOperationById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException
    {
        if (id.isPresent())
        {
            Operation entity = service.getOperationById(id.get());
            model.addAttribute("operation", entity);
        }
        else
        {
            model.addAttribute("operation", new Operation());
        }
        return "Operation/add-edit-operation";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteOperationById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException
    {
        service.deleteOperationById(id);
        return "redirect:/Operation";
    }

    @RequestMapping(path = "/createOperation", method = RequestMethod.POST)
    public String createOrUpdateOperation(Operation Operation)
    {
        service.createOrUpdateOperation(Operation);

        return "redirect:/Operation";
    }
}
