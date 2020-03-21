package com.howtodoinjava.demo.controller;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.Bill;
import com.howtodoinjava.demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Bill")
public class BillMvcController
{
    @Autowired
    BillService service;

    @RequestMapping
    public String getAllBill(Model model)
    {
        List<Bill> list = service.getAllBill();

        model.addAttribute("bills", list);
        return "Bill/list-bills";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editBillById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException
    {
        if (id.isPresent())
        {
            Bill entity = service.getBillById(id.get());
            model.addAttribute("bill", entity);
        }
        else
        {
            model.addAttribute("bill", new Bill());
        }
        return "Bill/add-edit-bill";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteBillById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException
    {
        service.deleteBillById(id);
        return "redirect:/Bill";
    }

    @RequestMapping(path = "/createBill", method = RequestMethod.POST)
    public String createOrUpdateBill(Bill Bill)
    {
        service.createOrUpdateBill(Bill);
        return "redirect:/Bill";
    }
}
