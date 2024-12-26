package com.spring.mvcproject.database.mybatis.repository;

import com.spring.mvcproject.database.mybatis.entity.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    void saveTest() {
        Pet pet = Pet.builder()
                .petName("둘기둘기")
                .petAge(1)
                .injection(true)
                .build();

        petRepository.save(pet);
    }

    @Test
    void findByIdTest() {
        Pet foundPet = petRepository.findById(1L);
        System.out.println(foundPet);
    }

    @Test
    void findAllTest() {
        List<Pet> foundPets = petRepository.findAll();
        System.out.println("List of Pets");
        foundPets.forEach(System.out::println);
    }

    @Test
    void deleteByIdTest() {
        boolean b = petRepository.deleteById(1L);
        System.out.println(b);
    }

    @Test
    void countTest() {
        int count = petRepository.petCount();
        System.out.println(count);
    }
}