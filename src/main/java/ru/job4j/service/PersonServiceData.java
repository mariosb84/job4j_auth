package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.domain.PersonDto;
import ru.job4j.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class PersonServiceData implements PersonService, UserDetailsService {

    private final PersonRepository personRepository;

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
    public boolean updatePatch(PersonDto personDto) {
        var person = personRepository.findById(personDto.getId());
        if (person.isPresent()) {
            Person result = person.get();
            result.setPassword(personDto.getPassword());
             this.add(person.get());
             return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person user = personRepository.findPersonByLogin(login).orElseThrow();
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(user.getLogin(), user.getPassword(), emptyList());
    }

}
