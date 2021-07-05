package org.meme.corp.client;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.meme.corp.database.entity.Event;
import org.meme.corp.database.entity.Transaction;
import org.meme.corp.database.repository.impl.EventRepository;
import org.meme.corp.database.repository.impl.PersonRepository;
import org.meme.corp.database.repository.impl.TransactionRepository;
import org.meme.corp.dto.EventDto;
import org.meme.corp.dto.TransactionDto;
import org.meme.corp.dto.UserDto;

@RequiredArgsConstructor
public abstract class AbstractClient implements Client {

  private final TransactionRepository transactionRepository;
  private final PersonRepository personRepository;
  private final EventRepository eventRepository;

  //TODO participantIdList required?
  public EventDto createNewEvent(String name, List<Long> participantIdList) {
    eventRepository.save(Event.builder()
        .name(name)
        .build());
    //TODO mapper from entity to dto
    return null;
  }

  public List<EventDto> listEvents(Long chatId) {
    eventRepository.findAllByChatId(chatId);
    //TODO mapper from entity to dto
    return null;
  }

  //TODO
  public EventDto addEventParticipants(Long eventId, List<Long> participantIdList) {
    return null;
  }

  //TODO
  public EventDto removeEventParticipants(Long eventId, List<Long> participantIdList) {
    return null;
  }

  public Map<UserDto, Map<UserDto, Float>> calculateEvent(Long eventId) {
    final Event event = eventRepository.findById(eventId);

//  FIXME with mapper return GraphUtil.calculateTransactions(mapper(event));
    return null;
  }

  public TransactionDto createNewTransaction(Long eventId, String name, Float sum, Map<Long, Float> userExpenses,
      Long ownerId) {
    final Transaction transaction = transactionRepository.save(Transaction.builder()
        .name(name)
        .owner(personRepository.findById(ownerId))
        .build());
    //TODO mapper from entity to dto
    return null;
  }

  public List<TransactionDto> listTransactions(Long eventId) {
    transactionRepository.findByEventId(eventId);
    //TODO mapper from entity to dto
    return null;
  }

  public TransactionDto editTransaction(Long transactionId, TransactionDto newTransaction) {
    //TODO mapper newTransaction -> entity
    final Transaction newTransactionEntity = Transaction.builder().build();
    newTransactionEntity.setId(transactionId);
    transactionRepository.save(newTransactionEntity);
    //TODO mapper from entity to dto
    return null;
  }
}
