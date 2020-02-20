package com.example.api.controller;

import com.example.api.entity.Person;
import com.example.api.service.PersonService;
import com.example.api.util.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = Path.PERSON)
public class PersonController {

    private final PersonService service;

    @GetMapping
    public List<Person> get() {
        return service.findAll();
    }

    @PostMapping
    public Person post(@Valid @RequestBody Person person){
        return service.post(person);
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable("id") Long id) {
        return service.find(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

}
