package com.howtodoinjava.demo.service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.Operation;
import com.howtodoinjava.demo.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

	@Autowired
	OperationRepository repository;

	public List<Operation> getAllOperation()
	{
		List<Operation> result = (List<Operation>) repository.findAll();

		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Operation>();
		}
	}

	public List<Operation> getAllOperationById(Long id)throws RecordNotFoundException
	{
		List<Operation> result = (List<Operation>) repository.findAll();

		List<Operation> outpt = new ArrayList<>();

		for(Operation item : result){
			if(item.getTerminal().getId()==id)
				outpt.add(item);
		}

		return  outpt;
	}

	public Operation getOperationById(Long id) throws RecordNotFoundException
	{
		Optional<Operation> Operation = repository.findById(id);

		if(Operation.isPresent()) {
			return Operation.get();
		} else {
			throw new RecordNotFoundException("No Card record exist for given id");
		}
	}

	public Operation createOrUpdateOperation(Operation entity)
	{
		if(entity.getId()  == null)
		{
			entity = repository.save(entity);

			return entity;
		}
		else
		{
			Optional<Operation> Operation = repository.findById(entity.getId());

			if(Operation.isPresent())
			{
				Operation newEntity = Operation.get();
				newEntity.setType(entity.getType());
				newEntity.setAmount(entity.getAmount());
				newEntity.setTerminal(entity.getTerminal());

				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}

	public void deleteOperationById(Long id) throws RecordNotFoundException
	{
		Optional<Operation> Operation = repository.findById(id);

		if(Operation.isPresent())
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Operation record exist for given id");
		}
	}
}