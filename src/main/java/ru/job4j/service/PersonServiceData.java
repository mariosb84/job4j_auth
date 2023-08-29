package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonMemStore;
import ru.job4j.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class PersonServiceData implements PersonService, UserDetailsService {

    private final PersonRepository personRepository;

    private final PersonMemStore persons;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> add(Person person) {
      Person result = personRepository.save(person);
        if (Optional.of(result).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    @Override
    public boolean update(Person person) {
        personRepository.save(person);
        return personRepository.findById(person.getId()).isPresent();

    }

    @Override
    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    @Override
    public boolean delete(Person person) {
        personRepository.delete(person);
        return personRepository.findById(person.getId()).isEmpty();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = persons.findByLogin(login);
        if (person == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(person.getLogin(), person.getPassword(), emptyList());
    }

    public void save(Person person) {
        persons.save(person);
    }

    public Person findByLogin(String login) {
        return persons.findByLogin(login);
    }

    public List<Person> findAllPersonsMem() {
        return persons.findAllPersons();
    }

}
