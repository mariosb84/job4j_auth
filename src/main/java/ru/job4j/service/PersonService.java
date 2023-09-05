package ru.job4j.service;

import ru.job4j.domain.Person;
import ru.job4j.domain.PersonDto;

import java.util.List;
import java.util.Optional;

public interface PersonService {

     List<Person> findAll();

    Optional<Person> add(Person person);

   boolean update(Person person);

     Optional<Person> findById(int id);

    boolean delete(Person person);

    boolean updatePatch(PersonDto personDto);

}
