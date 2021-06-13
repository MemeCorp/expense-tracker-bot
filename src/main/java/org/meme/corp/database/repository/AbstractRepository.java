package org.meme.corp.database.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractRepository<T, ID> implements Repository<T, ID> {

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("com.meme.corp.expense_tracker");
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
