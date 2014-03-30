package main.java.Sorting;

/**
 * Created by atscott on 3/29/2014.
 * http://www.sanfoundry.com/java-program-implement-heap-sort/
 */
public class HeapSort implements NumberSorter {
  private int N;

  /* sort Function */
  public void sort(int[] listOfNumbers) {
    heapify(listOfNumbers);
    for (int i = N; i > 0; i--) {
      swap(listOfNumbers, 0, i);
      N = N - 1;
      maxheap(listOfNumbers, 0);
    }
  }

  /* Function to build a heap */
  private void heapify(int arr[]) {
    N = arr.length - 1;
    for (int i = N / 2; i >= 0; i--)
      maxheap(arr, i);
  }

  /* Function to swap largest element in heap */
  private void maxheap(int arr[], int i) {
    int left = 2 * i;
    int right = 2 * i + 1;
    int max = i;
    if (left <= N && arr[left] > arr[i])
      max = left;
    if (right <= N && arr[right] > arr[max])
      max = right;

    if (max != i) {
      swap(arr, i, max);
      maxheap(arr, max);
    }
  }

  /* Function to swap two numbers in an array */
  private void swap(int arr[], int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
