package main.java.scottat;

import main.java.Sorting.BubbleSort;
import main.java.Sorting.NumberSorter;

import java.io.IOException;

/**
 * User: atscott
 * Date: 3/13/14
 * Time: 6:56 PM
 */
public class SortBenchmarker
{
  private CsvFileWriter combinedResultWriter;

  public SortBenchmarker(String outputFile)
  {
    combinedResultWriter = new CsvFileWriter(outputFile);
  }

  public void benchmarkAllSorts(int[] someNumbers) throws IOException
  {
    WriteHeaderForResultFiles();
    NumberSorter bubbleSorter = new BubbleSort();
    Long bubbleTime = benchmarkSort(bubbleSorter, someNumbers);
    String[] results = {"" + someNumbers.length, bubbleTime.toString()};

    combinedResultWriter.WriteResults(results);
  }

  private void WriteHeaderForResultFiles() throws IOException
  {
    String[] header = {"numbers sorted", "bubble sort time (ns)"};
    combinedResultWriter.WriteHeader(header);
  }

  private long benchmarkSort(NumberSorter sorter, int[] numbersToSort)
  {
    int[] copy = new int[numbersToSort.length];
    System.arraycopy(numbersToSort, 0, copy, 0, numbersToSort.length);
    long start = System.nanoTime();
    sorter.Sort(copy);
    long end = System.nanoTime();
    return end - start;
  }
}
