package com.howtodoinjava.demo.service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.Card;
import com.howtodoinjava.demo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

	@Autowired
	CardRepository repository;

	public List<Card> getAllCard()
	{
		List<Card> result = (List<Card>) repository.findAll();

		if(result.size() > 0) {
			return result;
		} else
			{
			return new ArrayList<Card>();
		}
	}

	public Card getCardById(Long id) throws RecordNotFoundException
	{
		Optional<Card> Card = repository.findById(id);

		if(Card.isPresent()) {
			return Card.get();
		} else {
			throw new RecordNotFoundException("No Card record exist for given id");
		}
	}

	public Card createOrUpdateCard(Card entity)
	{
		if(entity.getId()  == null)
		{
			entity = repository.save(entity);

			return entity;
		}
		else
		{
			Optional<Card> Card = repository.findById(entity.getId());

			if(Card.isPresent())
			{
				Card newEntity = Card.get();
				newEntity.setNumber(entity.getNumber());
				newEntity.setDate_of(entity.getDate_of());
				newEntity.setPin(entity.getPin());
				newEntity.setCsv(entity.getCsv());

				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}

	public void deleteCardById(Long id) throws RecordNotFoundException
	{
		Optional<Card> Card = repository.findById(id);

		if(Card.isPresent())
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Card record exist for given id");
		}
	}
}