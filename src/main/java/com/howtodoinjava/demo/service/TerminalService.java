package com.howtodoinjava.demo.service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.Terminal;
import com.howtodoinjava.demo.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TerminalService {

	@Autowired
	TerminalRepository repository;

	public List<Terminal> getAllTerminals()
	{
		List<Terminal> result = (List<Terminal>) repository.findAll();

		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Terminal>();
		}
	}

	public Terminal getTerminalById(Long id) throws RecordNotFoundException
	{
		Optional<Terminal> Terminal = repository.findById(id);

		if(Terminal.isPresent()) {
			return Terminal.get();
		} else {
			throw new RecordNotFoundException("No Terminal record exist for given id");
		}
	}

	public Terminal createOrUpdateTerminal(Terminal entity)
	{
		if(entity.getId()  == null)
		{
			entity = repository.save(entity);

			return entity;
		}
		else
		{
			Optional<Terminal> Terminal = repository.findById(entity.getId());

			if(Terminal.isPresent())
			{
				Terminal newEntity = Terminal.get();
				newEntity.setBalance(entity.getBalance());
				newEntity.setFilial_owner(entity.getFilial_owner());

				newEntity = repository.save(newEntity);

				return newEntity;
			}
			else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}

	public void deleteTerminalById(Long id) throws RecordNotFoundException
	{
		Optional<Terminal> Terminal = repository.findById(id);

		if(Terminal.isPresent())
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Terminal record exist for given id");
		}
	}
}