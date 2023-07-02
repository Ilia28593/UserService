package com.example.userservice.controller;

import com.example.userservice.entity.Person;
import com.example.userservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class Controller {
    private final PersonService service;

    /**
     * Контроллер отвечающий за получения пользователя по id.
     * @param id - передается по http в заголовке запроса.
     * @return возвращает пользователя или если обьек не найден BadRequest.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        if (Objects.isNull(service.getById(id))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    /**
     * Контроллер отвечающий за получения пользователя по Email.
     * @param email - передается по http в заголовке запроса.
     * @return возвращает пользователя или если обьек не найден BadRequest.
     */
    @GetMapping("email/{email}")
    public ResponseEntity<Person> getPersonByEmail(@PathVariable(name = "email") String email) {
        if (service.getByEmail(email).isPresent()) {
            return new ResponseEntity<>(service.getByEmail(email).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Контроллер отвечающий за получения списка всех пользователей.
     * @return возвращает псписок всех пользователей.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<>(service.getAllPersons(), HttpStatus.OK);
    }

    /**
     * Контроллер отвечающий за создание пользователя.
     * @param person - передается по http в теле запроса.
     * @return возвращает пользователя или если обьек не найден BadRequest.
     */
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return new ResponseEntity<>(service.createPerson(person), HttpStatus.OK);
    }

    /**
     * Контроллер отвечающий за обнавление пользователя.
     * @param person - передается по http в теле запроса.
     * @return возвращает пользователя или если обьек не найден BadRequest.
     */
    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        return new ResponseEntity<>(service.updatePerson(person), HttpStatus.OK);
    }

    /**
     * Контроллер отвечающий за удаление пользователя.
     * @param id - передается по http в теле запроса.
     * @return возвращает сообщение об удаление или если обьек не найден BadRequest.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePerson(@PathVariable Long id) {
        return new ResponseEntity<>(service.removePerson(id) ?
                "Пользователь удален" : "Удаление не удачно", HttpStatus.OK);
    }

    /**
     * Контроллер отвечающий за создание HTTP запроса к микросервису на обнавление роли пользователя и получение его.
     *
     * @param id   - передается по http в заголовке запроса.
     * @param role - передается по http в заголовке запроса.
     * @return возвращает пользователя или если обьек не найден BadRequest.
     */
    @PutMapping("{id}/change/{role}")
    public ResponseEntity<Person> updatePerson(@PathVariable String role, @PathVariable Long id) {
        return new ResponseEntity<>(service.changeRole(role, id), HttpStatus.OK);
    }
}


