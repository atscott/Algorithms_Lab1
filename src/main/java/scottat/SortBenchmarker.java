package main.java.scottat;

import main.java.Sorting.*;

import java.io.IOException;

/**
 * User: atscott
 * Date: 3/13/14
 * Time: 6:56 PM
 */
public class SortBenchmarker {
  private CsvFileWriter combinedResultWriter;

  public SortBenchmarker(String outputFile) {
    combinedResultWriter = new CsvFileWriter(outputFile);
  }

  public void benchmarkAllSorts(int[] someNumbers) throws IOException {
    WriteHeaderForResultFiles();
//    NumberSorter mergeSorter = new MergeSort();
//    Long mergeTime = benchmarkSort(mergeSorter, someNumbers);
    NumberSorter multiMerge = new MultiThreadedMergeSort();
    ((MultiThreadedMergeSort) multiMerge).setMaxThreads(0);
    Long multiMergeTime0 = benchmarkSort(multiMerge, someNumbers);
    ((MultiThreadedMergeSort) multiMerge).setMaxThreads(2);
    Long multiMergeTime2 = benchmarkSort(multiMerge, someNumbers);
    ((MultiThreadedMergeSort) multiMerge).setMaxThreads(4);
    Long multiMergeTime4 = benchmarkSort(multiMerge, someNumbers);
    ((MultiThreadedMergeSort) multiMerge).setMaxThreads(6);
    Long multiMergeTime6 = benchmarkSort(multiMerge, someNumbers);
    ((MultiThreadedMergeSort) multiMerge).setMaxThreads(8);
    Long multiMergeTime8 = benchmarkSort(multiMerge, someNumbers);
//    NumberSorter quickSorter = new QuickSort();
//    Long quickTime = benchmarkSort(quickSorter, someNumbers);
//    NumberSorter randquickSorter = new RandomPivotQuickSort();
//    Long randquickTime = benchmarkSort(randquickSorter, someNumbers);
    NumberSorter multiQuickSorter = new MultiThreadedQuickSort();
    ((MultiThreadedQuickSort) multiQuickSorter).setMaxThreads(0);
    Long multiQuickTime0 = benchmarkSort(multiQuickSorter, someNumbers);
    ((MultiThreadedQuickSort) multiQuickSorter).setMaxThreads(2);
    Long multiQuickTime2 = benchmarkSort(multiQuickSorter, someNumbers);
    ((MultiThreadedQuickSort) multiQuickSorter).setMaxThreads(4);
    Long multiQuickTime4 = benchmarkSort(multiQuickSorter, someNumbers);
    ((MultiThreadedQuickSort) multiQuickSorter).setMaxThreads(6);
    Long multiQuickTime6 = benchmarkSort(multiQuickSorter, someNumbers);
    ((MultiThreadedQuickSort) multiQuickSorter).setMaxThreads(8);
    Long multiQuickTime8 = benchmarkSort(multiQuickSorter, someNumbers);
    String[] results = {"" + someNumbers.length, multiMergeTime0.toString(), multiMergeTime2.toString(), multiMergeTime4.toString(),
        multiMergeTime6.toString(), multiMergeTime8.toString(), multiQuickTime0.toString(), multiQuickTime2.toString(), multiQuickTime4.toString(), multiQuickTime6.toString(), multiQuickTime8.toString()};

    combinedResultWriter.WriteResults(results);
  }

  private void WriteHeaderForResultFiles() throws IOException {
    String[] header = {"numbers sorted", "merge 0 threads", "merge 2 threads", "merge 4 threads", "merge 6 threads", "merge 8 threads", "quick 0 threads", "quick 2 threads", "quick 4 threads", "quick 6 threads", "quick 8 threads"};
    combinedResultWriter.WriteHeader(header);
  }

  private long benchmarkSort(NumberSorter sorter, int[] numbersToSort) {
    int[] copy = new int[numbersToSort.length];
    System.arraycopy(numbersToSort, 0, copy, 0, numbersToSort.length);
    long start = System.nanoTime();
    sorter.sort(copy);
    long end = System.nanoTime();
    return end - start;
  }
}
