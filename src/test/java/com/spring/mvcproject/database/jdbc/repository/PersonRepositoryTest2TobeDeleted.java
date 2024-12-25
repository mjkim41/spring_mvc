package com.spring.mvcproject.database.jdbc.repository;

import com.spring.mvcproject.database.jdbc.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest2TobeDeleted {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void save() {
        personRepository.getAll();
    }
}