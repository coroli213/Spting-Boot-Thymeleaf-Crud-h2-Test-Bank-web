package com.howtodoinjava.demo.repository;

import com.howtodoinjava.demo.model.Terminal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends CrudRepository<Terminal, Long> {

}
