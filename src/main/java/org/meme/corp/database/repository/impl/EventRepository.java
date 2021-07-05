package org.meme.corp.database.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.meme.corp.database.entity.Event;
import org.meme.corp.database.repository.AbstractRepository;

public class EventRepository extends AbstractRepository<Event, Long> {

    @Override
    public Event findById(Long id) {
        EntityManager em = getEntityManager();

        Event event;

        try {
            event = (Event) em.createQuery("SELECT event from Event event where event.id = ?1")
                    .setParameter(1, id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            event = null;
        }

        return event;
    }

    @Override
    public List<Event> findAll() {
        EntityManager em = getEntityManager();

        List<Event> events = em.createQuery("SELECT event from Event event")
            .getResultList();

        return events;
    }

    //TODO
    public List<Event> findAllByChatId(Long chatId) {
        EntityManager em = getEntityManager();

        List<Event> events = em.createQuery("SELECT event from Event event")
            .getResultList();

        return events;
    }

    @Override
    public Event save(Event event) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.persist(event);

        em.getTransaction().commit();

        return event;
    }

    @Override
    public Event update(Event event) {
        EntityManager em = getEntityManager();

        Event existedEvent = findById(event.getId());

        em.detach(existedEvent);

        existedEvent.setName(event.getName());

        em.getTransaction().begin();

        em.merge(existedEvent);

        em.getTransaction().commit();

        return event;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Event event = em.find(Event.class, id);

        em.remove(event);

        em.getTransaction().commit();
    }

    @Override
    public void delete(Event event) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Event eventForDelete = em.find(Event.class, event.getId());

        em.remove(eventForDelete);

        em.getTransaction().commit();
    }
}
