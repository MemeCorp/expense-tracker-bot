package org.meme.corp.client;

import java.util.List;
import java.util.Map;
import org.meme.corp.dto.EventDto;
import org.meme.corp.dto.TransactionDto;
import org.meme.corp.dto.UserDto;

public interface Client {

  //================Events===================
  EventDto createNewEvent(String name, List<Long> participantIdList);

  List<EventDto> listEvents(Long chatId);

  EventDto addEventParticipants(Long eventId, List<Long> participantIdList);

  EventDto removeEventParticipants(Long eventId, List<Long> participantIdList);

  Map<UserDto, Map<UserDto, Float>> calculateEvent(Long eventId);

  //=============Transactions================
  //userExpenses - <userId, $$$>
  TransactionDto createNewTransaction(Long eventId, Float sum, Map<Long, Float> userExpenses, Long ownerId);

  List<TransactionDto> listTransactions(Long eventId);

  TransactionDto editTransaction(Long transactionId, TransactionDto newTransaction);

}
