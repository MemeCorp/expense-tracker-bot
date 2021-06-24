package org.meme.corp.database.repository.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.meme.corp.database.entity.Chat;
import org.meme.corp.database.entity.ChatPK;
import org.meme.corp.database.entity.Event;
import org.meme.corp.database.repository.AbstractRepositoryTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatRepositoryTest extends AbstractRepositoryTest {

    private static final ChatRepository chatRepository = new ChatRepository();
    private static final EventRepository eventRepository = new EventRepository();

    private static final String CHAT_TEST_NAME = "test_chat";

    @AfterEach
    public void tearDown() {
        for (Chat chat : chatRepository.findAll()) {
            chatRepository.delete(chat);
        }
    }

    @Test
    public void FindByIdTest() {
        //prepare
        Chat chat = Chat.builder()
                .chatPK(new ChatPK(1L, CHAT_TEST_NAME))
                .events(Collections.emptySet())
                .build();

        Chat created = chatRepository.save(chat);

        //execute
        Chat found = chatRepository.findById(created.getChatPK());

        //assert
        Assertions.assertEquals(created, found);
    }

    @Test
    public void FindAllTest() {
        //prepare
        List<Chat> createdChats = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Chat chat = Chat.builder()
                    .chatPK(new ChatPK(1L + i, CHAT_TEST_NAME + i))
                    .events(Collections.emptySet())
                    .build();

            createdChats.add(chatRepository.save(chat));
        }

        //execute
        List<Chat> foundChats = chatRepository.findAll();

        //assert
        Assertions.assertEquals(foundChats, createdChats);
    }

    @Test
    public void SaveTest() {
        //prepare
        Chat chat = Chat.builder()
                .chatPK(new ChatPK(1L, CHAT_TEST_NAME))
                .events(Collections.emptySet())
                .build();

        //execute
        Chat created = chatRepository.save(chat);

        //assert
        Chat found = chatRepository.findById(created.getChatPK());

        Assertions.assertEquals(found, created);
    }

    @Test
    public void UpdateTest() {
        //prepare
        Chat chat = Chat.builder()
                .chatPK(new ChatPK(1L, CHAT_TEST_NAME))
                .events(Collections.emptySet())
                .build();

        Chat created = chatRepository.save(chat);

        Event newEvent = Event.builder().name("NewEvent").build();
        eventRepository.save(newEvent);
        created.setEvents(Collections.singleton(newEvent));

        //execute
        Chat updated = chatRepository.update(created);

        //assert
        Assertions.assertEquals(created, updated);
    }

    @Test
    public void DeleteByIdTest() {
        //prepare
        Chat chat = Chat.builder()
                .chatPK(new ChatPK(1L, CHAT_TEST_NAME))
                .events(Collections.emptySet())
                .build();

        Chat created = chatRepository.save(chat);

        //execute
        chatRepository.deleteById(created.getChatPK());

        //assert
        Assertions.assertNull(chatRepository.findById(created.getChatPK()));
    }

    @Test
    public void DeleteTest() {
        //prepare
        Chat chat = Chat.builder()
                .chatPK(new ChatPK(1L, CHAT_TEST_NAME))
                .events(Collections.emptySet())
                .build();

        Chat created = chatRepository.save(chat);

        //execute
        chatRepository.delete(created);

        //assert
        Assertions.assertNull(chatRepository.findById(created.getChatPK()));
    }
}
