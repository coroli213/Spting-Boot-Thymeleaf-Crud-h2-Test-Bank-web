package com.howtodoinjava.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.demo.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}
