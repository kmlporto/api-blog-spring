package com.example.api.controller;

import com.example.api.dto.response.PersonResponse;
import com.example.api.dto.update.PersonPartialUpdate;
import com.example.api.dto.update.PersonUpdate;
import com.example.api.entity.Person;
import com.example.api.service.PersonService;
import com.example.api.util.Converter;
import com.example.api.util.Path;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name="People")
@AllArgsConstructor
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = Path.PERSON)
public class PersonController {

    private final PersonService service;

    private final Converter converter;

    @GetMapping
    public ResponseEntity<List<PersonResponse>> get() {
        return ResponseEntity.ok(converter.mapAll(service.findAll(), PersonResponse.class));
    }

    @PostMapping
    public Person post(@Valid @RequestBody Person person){
        return service.save(person);
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable("id") Long id) {
        return service.find(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> put(@PathVariable("id") Long id, @Valid @RequestBody PersonUpdate personUpdate){
        Person person = converter.map(personUpdate, Person.class);
        person.setId(id);
        return ResponseEntity.ok(converter.map(service.update(person), PersonResponse.class));
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> patch(@PathVariable("id") Long id, @Valid @RequestBody PersonPartialUpdate personUpdate){
        Person person = converter.map(personUpdate, Person.class);
        person.setId(id);
        return ResponseEntity.ok(converter.map(service.partialUpdate(person), PersonResponse.class));
    }
}
