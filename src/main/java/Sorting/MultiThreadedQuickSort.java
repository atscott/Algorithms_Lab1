package main.java.Sorting;

/**
 * Created by atscott on 3/29/2014.
 */
public class MultiThreadedQuickSort extends QuickSort {
  private int threadCount = 0;
  private int maxThreads = 2;

  public void setMaxThreads(int threads) {
    maxThreads = threads;
  }

  @Override
  public void sort(int[] listOfNumbers) {
    threadCount = 0;
    basicQuickSort(listOfNumbers, 0, listOfNumbers.length);
  }

  @Override
  public void basicQuickSort(final int[] arr, final int beginIdx, int len) {
    if (len <= 1)
      return;

    final int endIdx = beginIdx + len - 1;
    final int pivotIdx = getPivot(beginIdx, endIdx);
    final long pivot = arr[pivotIdx];

    swap(arr, pivotIdx, endIdx);
    final int p = partition(arr, beginIdx, len, pivot);
    swap(arr, p, endIdx);

    boolean spawnThreads = false;
    synchronized (this) {
      if (threadCount < maxThreads) {
        spawnThreads = true;
      }

    }
    if (spawnThreads) {
      sortInThreads(arr, beginIdx, endIdx, p);
    } else {
      basicQuickSort(arr, beginIdx, p - beginIdx);
      basicQuickSort(arr, p + 1, endIdx - p);
    }
  }


  private void sortInThreads(final int[] arr, final int beginIdx, final int endIdx, final int p) {
    Thread leftPart = new Thread() {
      public void run() {
        basicQuickSort(arr, beginIdx, p - beginIdx);
      }
    };

    Thread rightPart = new Thread() {
      public void run() {
        basicQuickSort(arr, p + 1, endIdx - p);
      }
    };

    synchronized (this) {
      threadCount += 2;
    }
    leftPart.start();
    rightPart.start();
    try {
      leftPart.join();
      rightPart.join();
      synchronized (this) {
        threadCount -= 2;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
