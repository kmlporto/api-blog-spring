package com.example.api.service;


import com.example.api.dto.response.PersonResponse;
import com.example.api.entity.Person;
import com.example.api.repository.PersonRepository;
import com.example.api.util.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

@AllArgsConstructor
@Service
public class PersonService {

    private final PersonRepository repository;

    private final Converter converter;

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Person find(Long id){
        Optional.ofNullable(id).orElseThrow(InvalidParameterException::new);
        return repository.findById(id).orElseThrow(NoResultException::new);
    }

    public Person save(Person person){
        Optional.ofNullable(person).orElseThrow(InvalidParameterException::new);
        return repository.save(person);
    }

    public void delete(Long id){
        Optional.ofNullable(id).orElseThrow(InvalidParameterException::new);
        repository.deleteById(id);
    }

    public Person update(Person update){
        return update(update, converter::map);
    }

    public Person partialUpdate(Person update){
        return update(update, converter::mapWithOutNull);
    }

    @Transactional
    private Person update(Person update, BiConsumer<Person,Person> consumer){
        Optional.ofNullable(update).orElseThrow(InvalidParameterException::new);
        Person person = find(update.getId());
        consumer.accept(update, person);

        return save(person);
    }
}
