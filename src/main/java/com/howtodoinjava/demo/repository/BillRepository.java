package com.howtodoinjava.demo.repository;

import com.howtodoinjava.demo.model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

}
