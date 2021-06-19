package org.meme.corp.database.repository.impl;

import org.meme.corp.database.entity.Transaction;
import org.meme.corp.database.repository.AbstractRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class TransactionRepository extends AbstractRepository<Transaction, Long> {

    @Override
    public Transaction findById(Long id) {
        EntityManager em = getEntityManager();

        Transaction transaction = (Transaction) em.createQuery("SELECT transaction from Transaction transaction where transaction.id = ?1")
                .setParameter(1, id)
                .getSingleResult();

        return transaction;
    }

    @Override
    public List<Transaction> findAll() {
        EntityManager em = getEntityManager();

        List<Transaction> transactions = em.createQuery("SELECT transaction from Transaction transaction")
                .getResultList();

        return transactions;
    }

    @Override
    public Transaction save(Transaction transaction) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.persist(transaction);

        em.getTransaction().commit();

        return transaction;
    }

    @Override
    public Transaction update(Transaction transaction) {
        EntityManager em = getEntityManager();

        Transaction existedTransaction = findById(transaction.getId());

        em.detach(existedTransaction);

        existedTransaction.setName(transaction.getName());
        existedTransaction.setOwner(transaction.getOwner());
        existedTransaction.setPersons(transaction.getPersons());

        em.getTransaction().begin();

        em.merge(existedTransaction);

        em.getTransaction().commit();

        return transaction;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Transaction transaction = em.find(Transaction.class, id);

        em.remove(transaction);

        em.getTransaction().commit();
    }

    @Override
    public void delete(Transaction transaction) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Transaction transactionForDelete = em.find(Transaction.class, transaction.getId());

        em.remove(transactionForDelete);

        em.getTransaction().commit();
    }
}
