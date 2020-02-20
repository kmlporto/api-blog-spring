package com.example.api.service;


import com.example.api.entity.Person;
import com.example.api.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonService {

    private final PersonRepository repository;

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Person find(Long id){
        Optional.ofNullable(id).orElseThrow(InvalidParameterException::new);
        return repository.findById(id).orElseThrow(NoResultException::new);
    }

    public Person post(Person person){
        Optional.ofNullable(person).orElseThrow(InvalidParameterException::new);
        return repository.save(person);
    }

    public void delete(Long id){
        Optional.ofNullable(id).orElseThrow(InvalidParameterException::new);
        repository.deleteById(id);
    }

}
