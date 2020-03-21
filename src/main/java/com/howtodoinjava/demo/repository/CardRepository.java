package com.howtodoinjava.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.demo.model.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

}
