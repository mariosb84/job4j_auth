package ru.job4j.repository;

import org.springframework.stereotype.Component;
import ru.job4j.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PersonMemStore {

    private final ConcurrentHashMap<String, Person> persons = new ConcurrentHashMap<>();

    public void save(Person person) {
        persons.put(person.getLogin(), person);
    }


    public Person findByLogin(String login) {
        return persons.get(login);
    }

    public List<Person> findAllPersons() {
        return new ArrayList<>(persons.values());
    }

}