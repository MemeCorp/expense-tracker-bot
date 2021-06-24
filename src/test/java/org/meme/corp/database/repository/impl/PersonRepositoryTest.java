package org.meme.corp.database.repository.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.meme.corp.database.entity.Person;
import org.meme.corp.database.repository.AbstractRepositoryTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonRepositoryTest extends AbstractRepositoryTest {

    private static final PersonRepository personRepository = new PersonRepository();

    private static final String PERSON_TEST_NAME = "test_person";

    @AfterEach
    public void tearDown() {
        for (Person person : personRepository.findAll()) {
            personRepository.delete(person);
        }
    }

    @Test
    public void FindByIdTest() {
        //prepare
        Person person = Person.builder()
                .name(PERSON_TEST_NAME)
                .transactions(Collections.emptySet())
                .build();

        Person created = personRepository.save(person);

        //execute
        Person found = personRepository.findById(created.getId());

        //assert
        Assertions.assertEquals(created, found);
    }

    @Test
    public void FindAllTest() {
        //prepare
        List<Person> createdPersons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Person person = Person.builder()
                    .name(PERSON_TEST_NAME + i)
                    .transactions(Collections.emptySet())
                    .build();

            createdPersons.add(personRepository.save(person));
        }

        //execute
        List<Person> foundPersons = personRepository.findAll();

        //assert
        Assertions.assertEquals(foundPersons, createdPersons);
    }

    @Test
    public void SaveTest() {
        //prepare
        Person person = Person.builder()
                .name(PERSON_TEST_NAME)
                .transactions(Collections.emptySet())
                .build();

        //execute
        Person created = personRepository.save(person);

        //assert
        Person found = personRepository.findById(created.getId());

        Assertions.assertEquals(found, created);
    }

    @Test
    public void UpdateTest() {
        //prepare
        Person person = Person.builder()
                .name(PERSON_TEST_NAME)
                .transactions(Collections.emptySet())
                .build();

        Person created = personRepository.save(person);

        created.setName(PERSON_TEST_NAME + "updated");
        //execute

        Person updated = personRepository.update(created);

        //assert
        Assertions.assertEquals(created, updated);
    }

    @Test
    public void DeleteByIdTest() {
        //prepare
        Person person = Person.builder()
                .name(PERSON_TEST_NAME)
                .transactions(Collections.emptySet())
                .build();

        Person created = personRepository.save(person);

        //execute
        personRepository.deleteById(created.getId());

        //assert
        Assertions.assertNull(personRepository.findById(created.getId()));
    }

    @Test
    public void DeleteTest() {
        //prepare
        Person person = Person.builder()
                .name(PERSON_TEST_NAME)
                .transactions(Collections.emptySet())
                .build();

        Person created = personRepository.save(person);

        //execute
        personRepository.delete(created);

        //assert
        Assertions.assertNull(personRepository.findById(created.getId()));
    }
}
