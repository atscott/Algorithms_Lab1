package main.java.Sorting;

import java.util.Arrays;

/**
 * Created by atscott on 3/29/2014.
 */
public class MultiThreadedMergeSort extends MergeSort {
  protected int threadCount;
  protected int maxThreads = 2;

  public void setMaxThreads(int threads) {
    maxThreads = threads;
  }

  public void sort(int[] listOfNumbers) {
    threadCount = 0;
    doSort(listOfNumbers);
  }

  protected void doSort(int[] listOfNumbers) {
    if (listOfNumbers.length > 1) {

      int q = listOfNumbers.length / 2;

      final int[] leftArray = Arrays.copyOfRange(listOfNumbers, 0, q);
      final int[] rightArray = Arrays.copyOfRange(listOfNumbers, q, listOfNumbers.length);

      boolean spawnThreads = false;
      synchronized (this) {
        if (threadCount < maxThreads) {
          spawnThreads = true;
        }

      }

      if (spawnThreads) {
        sortInThreads(leftArray, rightArray);
      } else {
        doSort(leftArray);
        doSort(rightArray);
      }

      merge(listOfNumbers, leftArray, rightArray);
    }
  }

  protected void sortInThreads(final int[] leftArray, final int[] rightArray) {
    Thread left = new Thread() {
      public void run() {
        doSort(leftArray);
      }
    };

    Thread right = new Thread() {
      public void run() {
        doSort(rightArray);
      }
    };

    synchronized (this) {
      threadCount += 2;
    }
    left.start();
    right.start();
    try {
      left.join();
      right.join();
      synchronized (this) {
        threadCount -= 2;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


}
