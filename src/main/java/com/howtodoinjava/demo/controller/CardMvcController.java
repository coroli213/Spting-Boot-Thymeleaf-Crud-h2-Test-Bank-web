package com.howtodoinjava.demo.controller;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.Card;
import com.howtodoinjava.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Card")
public class CardMvcController
{

    @Autowired
    CardService service;

    @RequestMapping
    public String getAllCard(Model model)
    {
        List<Card> list = service.getAllCard();

        model.addAttribute("cards", list);
        return "Card/list-cards";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editCardById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException
    {
        if (id.isPresent())
        {
            Card entity = service.getCardById(id.get());
            model.addAttribute("card", entity);
        }
        else
        {
            model.addAttribute("card", new Card());
        }
        return "Card/add-edit-card";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteCardById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException
    {
        service.deleteCardById(id);
        return "redirect:/Card";
    }

    @RequestMapping(path = "/createCard", method = RequestMethod.POST)
    public String createOrUpdateCard(Card Card)
    {
        service.createOrUpdateCard(Card);
        return "redirect:/Card";
    }
}
