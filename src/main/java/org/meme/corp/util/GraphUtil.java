package org.meme.corp.util;

public final class GraphUtil {

  public static float[] calculateBalances(float[][] expenses) {
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

}