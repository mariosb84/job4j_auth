package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Person;
import ru.job4j.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService persons;
    private final BCryptPasswordEncoder encoder;

    public PersonController(final PersonService persons,
                            BCryptPasswordEncoder encoder) {
        this.persons = persons;
        this.encoder = encoder;
    }

    @GetMapping("/")
    public List<Person> findAll() {
        return this.persons.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        var person = this.persons.findById(id);
        return new ResponseEntity<Person>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        var result = this.persons.add(person);
        return new ResponseEntity<Person>(
                result.orElse(new Person()),
                result.isPresent() ? HttpStatus.CREATED : HttpStatus.CONFLICT
        );
    }

    @PutMapping("/")
    public ResponseEntity<Boolean> update(@RequestBody Person person) {
        if ((this.persons.update(person))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        Person person = new Person();
        person.setId(id);
        if ((this.persons.delete(person))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
       persons.save(person);
    }

    @GetMapping("/all")
    public List<Person> findAllPersons() {
        return persons.findAllPersonsMem();
    }

}
