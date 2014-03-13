package scottat;

import Sorting.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: atscott
 * Date: 3/12/14
 * Time: 1:25 PM
 */
public class sortRunner
{
  private static List<Integer> numbersToGenerate = new ArrayList<>();
  private static final String lowFileName = "resultsUnder1000.csv";
  private static final String highFileName = "resultsOver1000.csv";
  private static final String combinedFileName = "resultsAll.csv";
  private static CsvFileWriter lowResultWriter = new CsvFileWriter(lowFileName);
  private static CsvFileWriter highResultWriter = new CsvFileWriter(highFileName);
  private static CsvFileWriter combinedResultWriter = new CsvFileWriter(combinedFileName);

  private static void getListOfHowManyNumbersToGenerate()
  {
    for (int i = 200; i < 2000; i += 10)
    {
      numbersToGenerate.add(i);
    }
    for (int i = 2000; i < 2000000; i *= 2)
    {
      numbersToGenerate.add(i);
    }
  }

  public static void main(String args[]) throws IOException
  {
    NumberGenerator gen = new NumberGenerator();
    getListOfHowManyNumbersToGenerate();
    String[] header = {"numbers sorted", "merge sort time (ns)", "insertion sort time (ns)", "merge-insertion sort time (ns)"};
    lowResultWriter.WriteHeader(header);
    highResultWriter.WriteHeader(header);
    combinedResultWriter.WriteHeader(header);
    for (Integer number : numbersToGenerate)
    {
      int[] someNumbers = gen.GetRandomListOfNumbers(number);
      NumberSorter mergeSorter = new MergeSort();
      NumberSorter insertionSorter = new InsertionSort();
      NumberSorter miSorter = new MergeInsertionSort();
      Long mergeTime = benchmarkSort(mergeSorter, someNumbers);
      Long insertionTime = benchmarkSort(insertionSorter, someNumbers);
      Long miTime = benchmarkSort(miSorter, someNumbers);
      String[] results = {number.toString(), mergeTime.toString(), insertionTime.toString(), miTime.toString()};
      if (number < 1000)
      {
        lowResultWriter.WriteResults(results);
      } else
      {
        highResultWriter.WriteResults(results);
      }
      combinedResultWriter.WriteResults(results);
    }

  }

  private static long benchmarkSort(NumberSorter sorter, int[] numbersToSort)
  {
    int[] copy = new int[numbersToSort.length];
    System.arraycopy(numbersToSort, 0, copy, 0, numbersToSort.length);
    long start = System.nanoTime();
    sorter.Sort(copy);
    long end = System.nanoTime();
    return end - start;
  }

}
