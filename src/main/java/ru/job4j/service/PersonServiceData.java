package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceData implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person add(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void update(Person person) {
        personRepository.save(person);
    }

    @Override
    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

}
