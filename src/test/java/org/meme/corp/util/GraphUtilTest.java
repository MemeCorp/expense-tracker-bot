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
            .participants(Map.of(userA, 0f, userB, 0f, userC, 0f, userD, 0f, userE, 0f))
            .build(),
        Transaction.builder()
            .sum(300)
            .owner(userB)
            .participants(Map.of(userA, 0f, userB, 0f, userC, 0f, userD, 0f))
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

  @Test
  void shouldCalculateTransactionsWithPartialAmounts() {
    //given
    final User userA = new User("A");
    final User userB = new User("B");
    final User userC = new User("C");
    final User userD = new User("D");
    final List<Transaction> givenTransactionList = List.of(
        Transaction.builder()
            .sum(596.2f)
            .owner(userA)
            .participants(Map.of(userA, 65.26f, userB, 60.17f, userC, 86.96f, userD, 87.36f))
            .build(),
        Transaction.builder()
            .sum(114.2f)
            .owner(userB)
            .participants(Map.of(userA, 0f, userB, 0f, userC, 0f, userD, 0f))
            .build(),
        Transaction.builder()
            .sum(636 + (60 * 4))
            .owner(userC)
            .participants(Map.of(userA, 636f, userB, 0f, userC, 0f, userD, 0f))
            .build());
    final Map<User, Map<User, Float>> expectedResult = Map.of(
        userA, Map.of(userC, 267.72244f),
        userB, Map.of(userC, 108.63249f),
        userD, Map.of(userC, 250.0225f));

    //when
    final Map<User, Map<User, Float>> result = GraphUtil.calculateTransactions(givenTransactionList);

    //then
    Assertions.assertEquals(expectedResult, result);
  }
}