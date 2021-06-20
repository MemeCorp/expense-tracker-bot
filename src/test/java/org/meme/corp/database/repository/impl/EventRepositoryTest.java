package org.meme.corp.database.repository.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.meme.corp.database.entity.Event;
import org.meme.corp.database.repository.AbstractRepositoryTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventRepositoryTest extends AbstractRepositoryTest {

    private static final EventRepository eventRepository = new EventRepository();

    private static final String EVENT_TEST_NAME = "test_event";

    @AfterEach
    public void tearDown() {
        for (Event event : eventRepository.findAll()) {
            eventRepository.delete(event);
        }
    }

    @Test
    public void FindByIdTest() {
        //prepare
        Event event = Event.builder()
                .name(EVENT_TEST_NAME)
                .transactions(Collections.emptySet())
                .build();

        Event created = eventRepository.save(event);

        //execute
        Event found = eventRepository.findById(created.getId());

        //assert
        Assertions.assertEquals(created, found);
    }

    @Test
    public void FindAllTest() {
        //prepare
        List<Event> createdEvents = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Event event = Event.builder()
                    .name(EVENT_TEST_NAME + i)
                    .transactions(Collections.emptySet())
                    .build();

            createdEvents.add(eventRepository.save(event));
        }

        //execute
        List<Event> foundEvents = eventRepository.findAll();

        //assert
        Assertions.assertEquals(foundEvents, createdEvents);
    }

    @Test
    public void SaveTest() {
        //prepare
        Event event = Event.builder()
                .name(EVENT_TEST_NAME)
                .transactions(Collections.emptySet())
                .build();

        //execute
        Event created = eventRepository.save(event);

        //assert
        Event found = eventRepository.findById(created.getId());

        Assertions.assertEquals(found, created);
    }

    @Test
    public void UpdateTest() {
        //prepare
        Event event = Event.builder()
                .name(EVENT_TEST_NAME)
                .transactions(Collections.emptySet())
                .build();

        Event created = eventRepository.save(event);

        created.setName(EVENT_TEST_NAME + "updated");
        //execute

        Event updated = eventRepository.update(created);

        //assert
        Assertions.assertEquals(created, updated);
    }

    @Test
    public void DeleteByIdTest() {
        //prepare
        Event event = Event.builder()
                .name(EVENT_TEST_NAME)
                .transactions(Collections.emptySet())
                .build();

        Event created = eventRepository.save(event);

        //execute
        eventRepository.deleteById(created.getId());

        //assert
        Assertions.assertNull(eventRepository.findById(created.getId()));
    }

    @Test
    public void DeleteTest() {
        //prepare
        Event event = Event.builder()
                .name(EVENT_TEST_NAME)
                .transactions(Collections.emptySet())
                .build();

        Event created = eventRepository.save(event);

        //execute
        eventRepository.delete(created);

        //assert
        Assertions.assertNull(eventRepository.findById(created.getId()));
    }
}
