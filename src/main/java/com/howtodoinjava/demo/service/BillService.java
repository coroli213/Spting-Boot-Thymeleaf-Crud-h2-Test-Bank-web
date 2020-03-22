package com.howtodoinjava.demo.service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.Bill;
import com.howtodoinjava.demo.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

	@Autowired
	BillRepository repository;

	public List<Bill> getAllBill()
	{
		List<Bill> result = (List<Bill>) repository.findAll();

		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Bill>();
		}
	}

	public Bill getBillById(Long id) throws RecordNotFoundException
	{
		Optional<Bill> Bill = repository.findById(id);

		if(Bill.isPresent()) {
			return Bill.get();
		} else {
			throw new RecordNotFoundException("No Bill record exist for given id");
		}
	}

	public Bill createOrUpdateBill(Bill entity)
	{
		if(entity.getId()  == null)
		{
			entity = repository.save(entity);
			return entity;
		}
		else
		{
			Optional<Bill> Bill = repository.findById(entity.getId());

			if(Bill.isPresent())
			{
				Bill newEntity = Bill.get();
				newEntity.setBalance(entity.getBalance());
				newEntity.setBank_owner(entity.getBank_owner());
				//newEntity.setPin(entity.getPin());
				//newEntity.setCsv(entity.getCsv());

				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);
				return entity;
			}
		}
	}

	public void deleteBillById(Long id) throws RecordNotFoundException
	{
		Optional<Bill> Bill = repository.findById(id);

		if(Bill.isPresent())
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Bill record exist for given id");
		}
	}
}