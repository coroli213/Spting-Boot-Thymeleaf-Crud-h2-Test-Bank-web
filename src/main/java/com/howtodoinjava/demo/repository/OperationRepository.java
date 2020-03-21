package com.howtodoinjava.demo.repository;

import com.howtodoinjava.demo.model.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {

}
