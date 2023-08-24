package ru.job4j.service;

import ru.job4j.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

     List<Person> findAll();

     Person add(Person person);

   void update(Person person);

     Optional<Person> findById(int id);

    void delete(Person person);

}