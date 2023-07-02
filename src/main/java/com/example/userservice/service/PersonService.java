package com.example.userservice.service;

import com.example.userservice.entity.Person;
import com.example.userservice.entity.Role;
import com.example.userservice.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    public Person getById(Long id) {
        Optional<Person> optionalPerson = repository.findById(id);
        return optionalPerson.orElse(null);
    }

    public Person createPerson(Person person) {
        return repository.save(person);
    }

    public Person updatePerson(Person person) {
        Optional<Person> optionalPerson = repository.findById(person.getId());
        if (optionalPerson.isPresent()) {
            return repository.save(person);
        }
        return null;
    }

    public boolean removePerson(Long id) {
        Optional<Person> optionalPerson = repository.findById(id);
        if (optionalPerson.isPresent()) {
            repository.delete(optionalPerson.get());
            return true;
        }
        return false;
    }

    public Person changeRole(String role, Long id) {
        Optional<Person> optionalPerson = repository.findById(id);
        if (optionalPerson.isPresent()) {
            optionalPerson.get().setRole(Role.valueOf(role));
            return repository.save(optionalPerson.get());
        }
        return null;
    }

    public Optional<Person> getByEmail(String email) {
        return repository.findByEmail(email);
    }
    @PostConstruct
    public void sd(){
        createPerson(new Person("dsd","fdf","123A","xxx@mail.com", LocalDate.now(),Role.ADMIN));
            }

}
