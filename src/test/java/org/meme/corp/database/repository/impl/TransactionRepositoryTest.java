package org.meme.corp.database.repository.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.meme.corp.database.entity.Person;
import org.meme.corp.database.entity.Transaction;
import org.meme.corp.database.repository.AbstractRepositoryTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepositoryTest extends AbstractRepositoryTest {

    private static final TransactionRepository transactionRepository = new TransactionRepository();
    private static final PersonRepository personRepository = new PersonRepository();

    private static final String EVENT_TEST_NAME = "test_transaction";

    @AfterEach
    public void tearDown() {
        for (Transaction transaction : transactionRepository.findAll()) {
            transactionRepository.delete(transaction);
        }
    }

    @Test
    public void FindByIdTest() {
        //prepare
        Transaction transaction = Transaction.builder()
                .name(EVENT_TEST_NAME)
                .owner(personRepository.save(new Person()))
                .persons(Collections.emptySet())
                .build();

        Transaction created = transactionRepository.save(transaction);

        //execute
        Transaction found = transactionRepository.findById(created.getId());

        //assert
        Assertions.assertEquals(created, found);
    }

    @Test
    public void FindAllTest() {
        //prepare
        List<Transaction> createdTransactions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Transaction transaction = Transaction.builder()
                    .name(EVENT_TEST_NAME + i)
                    .persons(Collections.emptySet())
                    .build();

            createdTransactions.add(transactionRepository.save(transaction));
        }

        //execute
        List<Transaction> foundTransactions = transactionRepository.findAll();

        //assert
        Assertions.assertEquals(foundTransactions, createdTransactions);
    }

    @Test
    public void SaveTest() {
        //prepare
        Transaction transaction = Transaction.builder()
                .name(EVENT_TEST_NAME)
                .owner(personRepository.save(new Person()))
                .persons(Collections.emptySet())
                .build();

        //execute
        Transaction created = transactionRepository.save(transaction);

        //assert
        Transaction found = transactionRepository.findById(created.getId());

        Assertions.assertEquals(found, created);
    }

    @Test
    public void UpdateTest() {
        //prepare
        Transaction transaction = Transaction.builder()
                .name(EVENT_TEST_NAME)
                .owner(personRepository.save(new Person()))
                .persons(Collections.emptySet())
                .build();

        Transaction created = transactionRepository.save(transaction);

        created.setName(EVENT_TEST_NAME + "updated");
        //execute

        Transaction updated = transactionRepository.update(created);

        //assert
        Assertions.assertEquals(created, updated);
    }

    @Test
    public void DeleteByIdTest() {
        //prepare
        Transaction transaction = Transaction.builder()
                .name(EVENT_TEST_NAME)
                .owner(personRepository.save(new Person()))
                .persons(Collections.emptySet())
                .build();

        Transaction created = transactionRepository.save(transaction);

        //execute
        transactionRepository.deleteById(created.getId());

        //assert
        Assertions.assertNull(transactionRepository.findById(created.getId()));
    }

    @Test
    public void DeleteTest() {
        //prepare
        Transaction transaction = Transaction.builder()
                .name(EVENT_TEST_NAME)
                .owner(personRepository.save(new Person()))
                .persons(Collections.emptySet())
                .build();

        Transaction created = transactionRepository.save(transaction);

        //execute
        transactionRepository.delete(created);

        //assert
        Assertions.assertNull(transactionRepository.findById(created.getId()));
    }
}
