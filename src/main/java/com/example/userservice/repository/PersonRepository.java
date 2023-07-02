package com.example.userservice.repository;

import com.example.userservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Modifying
    @Transactional
    @Override
    <S extends Person> S save(S entity);

    Optional<Person> findByEmail(String email);
}
