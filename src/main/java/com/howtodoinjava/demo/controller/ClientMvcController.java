package com.howtodoinjava.demo.controller;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.Client;
import com.howtodoinjava.demo.model.Operation;
import com.howtodoinjava.demo.service.ClientService;
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
@RequestMapping("/Client")
public class ClientMvcController
{
	@Autowired
	ClientService service;

	@Autowired
	OperationService operservice;

	@RequestMapping
	public String getAllClients(Model model)
	{
		List<Client> list = service.getAllClients();

		model.addAttribute("clients", list);
		return "Client/list-clients";
	}

	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editClientById(Model model, @PathVariable("id") Optional<Long> id)
			throws RecordNotFoundException
	{
		if (id.isPresent())
		{
			Client entity = service.getClientById(id.get());
			model.addAttribute("client", entity);
		}
		else
		{
			model.addAttribute("client", new Client());
		}
		return "Client/add-edit-client";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteClientById(Model model, @PathVariable("id") Long id)
			throws RecordNotFoundException
	{
		service.deleteClientById(id);
		return "redirect:/Client";
	}

	@RequestMapping(path = "/operations/{id}")
	public String ClientOpetaions(Model model, @PathVariable("id") Long id)
			throws RecordNotFoundException
	{
		List<Operation> list = operservice.getAllOperation();

		model.addAttribute("operations", list);
		return "Client/list-operation";
	}

	@RequestMapping(path = "/createClient", method = RequestMethod.POST)
	public String createOrUpdateClient(Client client)
	{
		service.createOrUpdateClient(client);

		return "redirect:/Client";
	}
}
