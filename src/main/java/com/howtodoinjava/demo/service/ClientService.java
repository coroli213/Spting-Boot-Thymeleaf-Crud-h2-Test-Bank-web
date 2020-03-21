package com.howtodoinjava.demo.service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.Card;
import com.howtodoinjava.demo.model.Client;
import com.howtodoinjava.demo.repository.CardRepository;
import com.howtodoinjava.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository repository;

	@Autowired
	CardRepository cardrepository;
	
	public List<Client> getAllClients()
	{
		List<Client> result = (List<Client>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Client>();
		}
	}
	
	public Client getClientById(Long id) throws RecordNotFoundException
	{
		Optional<Client> client = repository.findById(id);
		
		if(client.isPresent()) {
			return client.get();
		} else {
			throw new RecordNotFoundException("No client record exist for given id");
		}
	}
	
	public Client createOrUpdateClient(Client entity)
	{
		if(entity.getId()  == null) 
		{
			entity = repository.save(entity);
			
			return entity;
		} 
		else 
		{
			Optional<Client> client = repository.findById(entity.getId());
			
			if(client.isPresent())
			{
				Client newEntity = client.get();

				newEntity.setFirstName(entity.getFirstName());
				newEntity.setSecondName(entity.getSecondName());
				newEntity.setLastName(entity.getLastName());
				newEntity.setSerial(entity.getSerial());
				newEntity.setNumber(entity.getNumber());
				// newEntity.setCard(entity.getCard());

				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				entity = repository.save(entity);
				
				return entity;
			}
		}
	} 
	
	public void deleteClientById(Long id) throws RecordNotFoundException
	{
		Optional<Client> client = repository.findById(id);

		Collection<Card> cards= client.get().getCards();
		cards.forEach((e)->{

			Optional<Card> Card = cardrepository.findById(e.getId());

			if(Card.isPresent())
			{
				cardrepository.deleteById(id);
			}
		});

		if(client.isPresent())
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No client record exist for given id");
		}
	} 
}