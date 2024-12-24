package com.spring.mvcproject.database.jdbc.repository;

import com.spring.mvcproject.database.jdbc.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void saveTest() {
        Person p = new Person(3L, "제갈량", 57);

        personRepository.save(p);
    }

    @Test
    void updateTest() {
        Person p = new Person(2L, "하츄핑", 5);

        personRepository.update(p);
    }

    @Test
    void deleteTest() {
        Long id = 1L;
        personRepository.delete(id);
    }

    @Test
    void bulkInsert() {
        // 데이터 생성
        List<String> nameList
                = List.of("돌돌", "미순", "미훙", "미도리");
        nameList.forEach((name) -> {
            Long randomId = (long) (Math.random() * 100 + 50);
            int randomAge = (int) (Math.random() * 30) + 10;
            Person p = new Person(randomId, name, randomAge);
            personRepository.save(p);
        });
    }

    @Test
    void findAllTest() {
        List<Person> personList
                = personRepository.findAll();

        System.out.println(personList);
        personList.forEach(System.out::println);
    }



}
