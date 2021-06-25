package org.meme.corp.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.meme.corp.model.Transaction;
import org.meme.corp.model.User;

class GraphUtilTest {

  @Test
  void shouldCalculateEmptyTransactions() {
    //given
    final List<Transaction> givenTransactionList = List.of();
    final Map<User, Map<User, Float>> expectedResult = new HashMap<>();

    //when
    final Map<User, Map<User, Float>> result = GraphUtil.calculateTransactions(givenTransactionList);

    //then
    Assertions.assertEquals(expectedResult, result);
  }

  @Test
  void shouldCalculateTransactions() {
    //given
    final User userA = new User("A");
    final User userB = new User("B");
    final User userC = new User("C");
    final User userD = new User("D");
    final User userE = new User("E");
    final List<Transaction> givenTransactionList = List.of(Transaction.builder()
            .sum(500)
            .owner(userA)
            .participants(List.of(userA, userB, userC, userD, userE))
            .build(),
        Transaction.builder()
            .sum(300)
            .owner(userB)
            .participants(List.of(userA, userB, userC, userD))
            .build());
    final Map<User, Map<User, Float>> expectedResult = Map.of(
        userC, Map.of(userA, 175f),
        userD, Map.of(userA, 150f, userB, 25f),
        userE, Map.of(userB, 100f));

    //when
    final Map<User, Map<User, Float>> result = GraphUtil.calculateTransactions(givenTransactionList);

    //then
    Assertions.assertEquals(expectedResult, result);
  }
}