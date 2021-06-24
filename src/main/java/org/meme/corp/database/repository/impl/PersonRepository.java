package org.meme.corp.database.repository.impl;

import org.meme.corp.database.entity.Person;
import org.meme.corp.database.repository.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class PersonRepository extends AbstractRepository<Person, Long> {

    @Override
    public Person findById(Long id) {
        EntityManager em = getEntityManager();

        Person person;

        try {
            person = (Person) em.createQuery("SELECT person from Person person where person.id = ?1")
                    .setParameter(1, id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            person = null;
        }

        return person;
    }

    @Override
    public List<Person> findAll() {
        EntityManager em = getEntityManager();

        List<Person> persons = em.createQuery("SELECT person from Person person")
                .getResultList();

        return persons;
    }

    @Override
    public Person save(Person person) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.persist(person);

        em.getTransaction().commit();

        return person;
    }

    @Override
    public Person update(Person person) {
        EntityManager em = getEntityManager();

        Person existedPerson = findById(person.getId());

        em.detach(existedPerson);

        existedPerson.setName(person.getName());
        existedPerson.setTransactions(person.getTransactions());

        em.getTransaction().begin();

        em.merge(existedPerson);

        em.getTransaction().commit();

        return person;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Person person = em.find(Person.class, id);

        em.remove(person);

        em.getTransaction().commit();
    }

    @Override
    public void delete(Person person) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Person personForDelete = em.find(Person.class, person.getId());

        em.remove(personForDelete);

        em.getTransaction().commit();
    }
}
