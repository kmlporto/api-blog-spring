package com.example.api.controller;

import com.example.api.entity.Person;
import com.example.api.service.PersonService;
import com.example.api.util.Path;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = Path.PERSON)
public class PersonController {

    private final PersonService service;

    @GetMapping
    public List<Person> get() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable("id") Long id) {
        return service.find(id);
    }

}
