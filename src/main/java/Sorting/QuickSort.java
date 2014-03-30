package main.java.Sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by atscott on 3/29/2014.
 * http://www.javacodegeeks.com/2012/06/all-you-need-to-know-about-quicksort.html
 */
public class QuickSort implements NumberSorter {

  public void sort(int[] listOfNumbers) {
    basicQuickSort(listOfNumbers, 0, listOfNumbers.length);
  }

  public void basicQuickSort(int[] arr, int beginIdx, int len) {
    if (len <= 1)
      return;

    final int endIdx = beginIdx + len - 1;
    final int pivotIdx = getPivot(beginIdx, endIdx);
    final long pivot = arr[pivotIdx];

    swap(arr, pivotIdx, endIdx);
    int p = partition(arr, beginIdx, len, pivot);
    swap(arr, p, endIdx);

    basicQuickSort(arr, beginIdx, p - beginIdx);
    basicQuickSort(arr, p + 1, endIdx - p);
  }

  protected int getPivot(int beginIdx, int endIdx) {
    return beginIdx + (endIdx - beginIdx + 1) / 2;
  }

  protected void swap(int[] array, int pivotIndex, int endIndex) {
    int temp = array[pivotIndex];
    array[pivotIndex] = array[endIndex];
    array[endIndex] = temp;
  }

  protected int partition(int[] arr, int beginIdx, int len, long pivot) {
    final int endIdx = beginIdx + len - 1;
    int p = beginIdx;
    for (int i = beginIdx; i != endIdx; ++i) {
      if (arr[i] <= pivot) {
        swap(arr, i, p++);
      }
    }
    return p;
  }
}
