package org.meme.corp.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.meme.corp.model.Event;
import org.meme.corp.model.Transaction;
import org.meme.corp.model.User;

public final class GraphUtil {

  public static Map<User, Map<User, Float>> calculateTransactions(Event event) {
    return Objects.isNull(event) ? new HashMap<>() : calculateTransactions(event.getTransactions());
  }

  public static Map<User, Map<User, Float>> calculateTransactions(List<Transaction> transactions) {
    if (Objects.isNull(transactions)) {
      return new HashMap<>();
    }
    final List<User> userList = getUserList(transactions);
    return userList.size() > 0
        ? calculateTransactions(calculateBalances(transactionsToExpenses(transactions, userList)), userList)
        : new HashMap<>();
  }

  private static float[][] transactionsToExpenses(List<Transaction> transactions, List<User> userList) {

    final int matrixSize = userList.size();
    final float[][] expenses = new float[matrixSize][matrixSize];
    transactions.forEach(transaction -> {
      final User owner = transaction.getOwner();
      final int ownerIndex = userList.indexOf(owner);
      final List<User> participants = transaction.getParticipants();
      final float expensePerParticipant = transaction.getSum() / participants.size();
      participants.forEach(participant -> {
        final int participantIndex = userList.indexOf(participant);
        expenses[ownerIndex][participantIndex] += expensePerParticipant;
      });
    });
    return expenses;
  }

  private static List<User> getUserList(List<Transaction> transactions) {
    final List<User> userList = transactions.stream()
        .flatMap(transaction -> Stream.concat(
            Stream.of(transaction.getOwner()),
            transaction.getParticipants().stream()))
        .distinct()
        .collect(Collectors.toList());
    return userList;
  }

  private static float[] calculateBalances(float[][] expenses) {
    final float[] result = new float[expenses.length];
    for (int col = 0; col < expenses[0].length; col++) {
      for (int row = 0; row < expenses.length; row++) {
        if (col == row) {
          continue;
        }
        result[col] -= expenses[row][col];
        result[row] += expenses[row][col];
      }
    }
    return result;
  }

  private static Map<User, Map<User, Float>> calculateTransactions(float[] balances, List<User> userList) {
    final int matrixSize = balances.length;
    final float[][] resultMatrix = new float[matrixSize][matrixSize];
    boolean finished = false;
    while (!finished) {
      int max = 0;
      int min = 0;
      for (int i = 0; i < matrixSize - 1; i++) {
        //similar values destruction
        final int nextI = i + 1;
        for (int j = nextI; j < matrixSize; j++) {
          if (balances[j] == 0f) {
            continue;
          }
          if (balances[i] + balances[j] == 0) {
            final float sum = Math.abs(balances[i]);
            if (balances[i] < 0) {
              resultMatrix[i][j] += sum;
            } else {
              resultMatrix[j][i] += sum;
            }
            balances[i] = 0f;
            balances[j] = 0f;
          }
        }
        if (balances[min] > balances[nextI]) {
          min = nextI;
        }
        if (balances[max] < balances[nextI]) {
          max = nextI;
        }
      }
      //paying from min to max
      if (balances[max] + balances[min] > 0) {
        balances[max] += balances[min];
        resultMatrix[min][max] += balances[min] * (-1);
        balances[min] = 0;
      } else if (balances[max] + balances[min] < 0) {
        balances[min] += balances[max];
        resultMatrix[min][max] += balances[max];
        balances[max] = 0;
      }
      //check if balances are already calculated
      for (float balance : balances) {
        if (balance != 0) {
          finished = false;
          break;
        }
        finished = true;
      }
    }
    final Map<User, Map<User, Float>> result = new HashMap<>();
    for (int i = 0; i < matrixSize; i++) {
      final User payer = userList.get(i);
      for (int j = 0; j < matrixSize; j++) {
        if (resultMatrix[i][j] != 0) {
          final User recipient = userList.get(j);
          final Map<User, Float> payerTransactions = result.getOrDefault(payer, new HashMap<>());
          Float payment = payerTransactions.getOrDefault(recipient, 0f);
          payment += resultMatrix[i][j];
          payerTransactions.put(recipient, payment);
          result.put(payer, payerTransactions);
        }
      }
    }
    return result;
  }

}
