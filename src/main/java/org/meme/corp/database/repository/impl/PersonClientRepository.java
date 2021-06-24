package org.meme.corp.database.repository.impl;

import org.meme.corp.database.entity.PersonClient;
import org.meme.corp.database.entity.PersonClientPK;
import org.meme.corp.database.repository.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class PersonClientRepository extends AbstractRepository<PersonClient, PersonClientPK> {

    @Override
    public PersonClient findById(PersonClientPK id) {
        EntityManager em = getEntityManager();

        PersonClient personClient;

        try {
            personClient = (PersonClient) em.createQuery("SELECT personClient from PersonClient personClient where personClient.personClientPK = ?1")
                    .setParameter(1, id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            personClient = null;
        }

        return personClient;
    }

    @Override
    public List<PersonClient> findAll() {
        EntityManager em = getEntityManager();

        List<PersonClient> personClients = em.createQuery("SELECT personClient from PersonClient personClient")
                .getResultList();

        return personClients;
    }

    @Override
    public PersonClient save(PersonClient personClient) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.persist(personClient);

        em.getTransaction().commit();

        return personClient;
    }

    @Override
    public PersonClient update(PersonClient personClient) {
        EntityManager em = getEntityManager();

        PersonClient existedPersonClient = findById(personClient.getPersonClientPK());

        em.detach(existedPersonClient);

        existedPersonClient.setPerson(personClient.getPerson());

        em.getTransaction().begin();

        em.merge(existedPersonClient);

        em.getTransaction().commit();

        return personClient;
    }

    @Override
    public void deleteById(PersonClientPK id) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        PersonClient personClient = em.find(PersonClient.class, id);

        em.remove(personClient);

        em.getTransaction().commit();
    }

    @Override
    public void delete(PersonClient personClient) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        PersonClient personClientForDelete = em.find(PersonClient.class, personClient.getPersonClientPK());

        em.remove(personClientForDelete);

        em.getTransaction().commit();
    }
}
