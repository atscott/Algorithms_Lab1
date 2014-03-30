package main.java.Sorting;

import java.util.Random;

/**
 * Created by atscott on 3/29/2014.
 */
public class RandomPivotQuickSort extends QuickSort{
  @Override
  protected int getPivot(int beginIdx, int endIdx) {
    Random rand = new Random();
    return beginIdx + Math.abs(rand.nextInt()) % (endIdx-beginIdx+1);
  }
}
