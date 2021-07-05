package org.meme.corp.database.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.meme.corp.database.entity.Transaction;
import org.meme.corp.database.repository.AbstractRepository;

public class TransactionRepository extends AbstractRepository<Transaction, Long> {

  @Override
  public Transaction findById(Long id) {
    EntityManager em = getEntityManager();

    Transaction transaction;

    try {
      transaction = (Transaction) em.createQuery(
              "SELECT transaction from Transaction transaction where transaction.id = ?1")
          .setParameter(1, id)
          .getSingleResult();
    } catch (NoResultException ex) {
      transaction = null;
    }

    return transaction;
  }

  public List<Transaction> findByEventId(Long eventId) {
    EntityManager em = getEntityManager();

    List<Transaction> resultList = em.createQuery("SELECT t from Transaction t where t.eventId = ?1")
        .setParameter(1, eventId)
        .getResultList();

    return resultList;
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
