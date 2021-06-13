package org.meme.corp.database.repository;

import java.util.List;

public interface Repository<T, ID> {

    T findById(ID id);

    List<T> findAll();

    T save(T t);

    T update(T t);

    void deleteById(ID id);

    void delete(T t);

}
