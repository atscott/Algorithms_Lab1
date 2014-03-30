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
//    NumberSorter multiMerge = new MultiThreadedMergeSort();
//    ((MultiThreadedMergeSort) multiMerge).setMaxThreads(2);
//    Long multiMergeTime2 = benchmarkSort(multiMerge, someNumbers);
//    NumberSorter quickSorter = new QuickSort();
//    Long quickTime = benchmarkSort(quickSorter, someNumbers);
//    NumberSorter multiQuickSorter = new MultiThreadedQuickSort();
//    ((MultiThreadedQuickSort) multiQuickSorter).setMaxThreads(2);
//    Long multiQuickTime2 = benchmarkSort(multiQuickSorter, someNumbers);
    NumberSorter hybridSort = new MergeQuickSortHybrid();
    ((MergeQuickSortHybrid) hybridSort).setMaxThreads(2);
    Long hybridTime = benchmarkSort(hybridSort, someNumbers);
    String[] results = {"" + someNumbers.length,hybridTime.toString()};

    combinedResultWriter.WriteResults(results);
  }

  private void WriteHeaderForResultFiles() throws IOException {
    String[] header = {"numbers sorted",  "hybrid time (ns)"};
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
