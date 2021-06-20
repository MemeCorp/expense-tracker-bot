package org.meme.corp.database.repository.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.meme.corp.database.entity.Person;
import org.meme.corp.database.entity.PersonClient;
import org.meme.corp.database.repository.AbstractRepositoryTest;

import java.util.ArrayList;
import java.util.List;

public class PersonClientRepositoryTest extends AbstractRepositoryTest {

    private static final PersonClientRepository personClientRepository = new PersonClientRepository();
    private static final PersonRepository personRepository = new PersonRepository();

    private static final String PERSON_CLIENT_TEST_NAME = "test_person_client";

    @AfterEach
    public void tearDown() {
        for (PersonClient personClient : personClientRepository.findAll()) {
            personClientRepository.delete(personClient);
        }
    }

    @Test
    public void FindByIdTest() {
        //prepare
        PersonClient personClient = PersonClient.builder()
                .clientName(PERSON_CLIENT_TEST_NAME)
                .person(personRepository.save(new Person()))
                .build();

        PersonClient created = personClientRepository.save(personClient);

        //execute
        PersonClient found = personClientRepository.findById(created.getId());

        //assert
        Assertions.assertEquals(created, found);
    }

    @Test
    public void FindAllTest() {
        //prepare
        List<PersonClient> createdPersonClients = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PersonClient personClient = PersonClient.builder()
                    .clientName(PERSON_CLIENT_TEST_NAME + i)
                    .person(personRepository.save(new Person()))
                    .build();

            createdPersonClients.add(personClientRepository.save(personClient));
        }

        //execute
        List<PersonClient> foundPersonClients = personClientRepository.findAll();

        //assert
        Assertions.assertEquals(foundPersonClients, createdPersonClients);
    }

    @Test
    public void SaveTest() {
        //prepare
        PersonClient personClient = PersonClient.builder()
                .clientName(PERSON_CLIENT_TEST_NAME)
                .person(personRepository.save(new Person()))
                .build();

        //execute
        PersonClient created = personClientRepository.save(personClient);

        //assert
        PersonClient found = personClientRepository.findById(created.getId());

        Assertions.assertEquals(found, created);
    }

    @Test
    public void UpdateTest() {
        //prepare
        PersonClient personClient = PersonClient.builder()
                .clientName(PERSON_CLIENT_TEST_NAME)
                .person(personRepository.save(new Person()))
                .build();

        PersonClient created = personClientRepository.save(personClient);

        created.setClientName(PERSON_CLIENT_TEST_NAME + "updated");
        //execute

        PersonClient updated = personClientRepository.update(created);

        //assert
        Assertions.assertEquals(created, updated);
    }

    @Test
    public void DeleteByIdTest() {
        //prepare
        PersonClient personClient = PersonClient.builder()
                .clientName(PERSON_CLIENT_TEST_NAME)
                .person(personRepository.save(new Person()))
                .build();

        PersonClient created = personClientRepository.save(personClient);

        //execute
        personClientRepository.deleteById(created.getId());

        //assert
        Assertions.assertNull(personClientRepository.findById(created.getId()));
    }

    @Test
    public void DeleteTest() {
        //prepare
        PersonClient personClient = PersonClient.builder()
                .clientName(PERSON_CLIENT_TEST_NAME)
                .person(personRepository.save(new Person()))
                .build();

        PersonClient created = personClientRepository.save(personClient);

        //execute
        personClientRepository.delete(created);

        //assert
        Assertions.assertNull(personClientRepository.findById(created.getId()));
    }
}
