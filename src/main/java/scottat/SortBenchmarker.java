package scottat;

import Sorting.*;

import java.io.IOException;

/**
 * User: atscott
 * Date: 3/13/14
 * Time: 6:56 PM
 */
public class SortBenchmarker
{
  private CsvFileWriter lowResultWriter;
  private CsvFileWriter highResultWriter;
  private CsvFileWriter combinedResultWriter;

  public SortBenchmarker(String lowEndFileName, String highEndFileName, String combinedFileName)
  {
    lowResultWriter = new CsvFileWriter(lowEndFileName);
    highResultWriter = new CsvFileWriter(highEndFileName);
    combinedResultWriter = new CsvFileWriter(combinedFileName);
  }

  public void benchmarkAllSorts(int[] someNumbers) throws IOException
  {
    WriteHeaderForResultFiles();
    NumberSorter bubbleSorter = new BubbleSort();
    Long bubbleTime = benchmarkSort(bubbleSorter, someNumbers);
    String[] results = {"" + someNumbers.length, bubbleTime.toString()};
    if (someNumbers.length < 1000)
    {
      lowResultWriter.WriteResults(results);
    }
    else
    {
      highResultWriter.WriteResults(results);
    }
    combinedResultWriter.WriteResults(results);
  }

  private void WriteHeaderForResultFiles() throws IOException
  {
    String[] header = {"numbers sorted", "bubble sort time (ns)"};
    lowResultWriter.WriteHeader(header);
    highResultWriter.WriteHeader(header);
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
