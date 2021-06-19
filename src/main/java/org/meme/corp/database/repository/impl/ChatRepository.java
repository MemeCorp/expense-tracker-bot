package org.meme.corp.database.repository.impl;

import org.meme.corp.database.entity.Chat;
import org.meme.corp.database.repository.AbstractRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class ChatRepository extends AbstractRepository<Chat, Long> {

    @Override
    public Chat findById(Long id) {
        EntityManager em = getEntityManager();

        Chat chat = (Chat) em.createQuery("SELECT chat from Chat chat where chat.id = ?1")
                .setParameter(1, id)
                .getSingleResult();

        return chat;
    }

    @Override
    public List<Chat> findAll() {
        EntityManager em = getEntityManager();

        List<Chat> chats = em.createQuery("SELECT chat from Chat chat")
                .getResultList();

        return chats;
    }

    @Override
    public Chat save(Chat chat) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.persist(chat);

        em.getTransaction().commit();

        return chat;
    }

    @Override
    public Chat update(Chat chat) {
        EntityManager em = getEntityManager();

        Chat existedChat = findById(chat.getId());

        em.detach(existedChat);

        existedChat.setClientName(chat.getClientName());
        existedChat.setEvents(chat.getEvents());

        em.getTransaction().begin();

        em.merge(existedChat);

        em.getTransaction().commit();

        return chat;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Chat chat = em.find(Chat.class, id);

        em.remove(chat);

        em.getTransaction().commit();
    }

    @Override
    public void delete(Chat chat) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Chat chatForDelete = em.find(Chat.class, chat.getId());

        em.remove(chatForDelete);

        em.getTransaction().commit();
    }
}
