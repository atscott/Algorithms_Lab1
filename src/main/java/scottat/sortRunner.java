package scottat;

import Sorting.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * User: atscott
 * Date: 3/12/14
 * Time: 1:25 PM
 */
public class sortRunner
{
  private static int[] numbersToGenerate = new int[]{100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000,
      1000000};
  private static final String resultFileName = "results.csv";

  public static void main(String args[]) throws IOException
  {
    writeCsvHeader();
    for (int number : numbersToGenerate)
    {
      int[] someNumbers = getRandomListOfNumbers(number);
      NumberSorter mergeSorter = new MergeSort();
      NumberSorter insertionSorter = new InsertionSort();
      NumberSorter miSorter = new MergeInsertionSort();
      long mergeTime = benchmarkSort(mergeSorter, someNumbers);
      long insertionTime = benchmarkSort(insertionSorter, someNumbers);
      long miTime = benchmarkSort(miSorter, someNumbers);
      writeResultsToFile(number, mergeTime, insertionTime, miTime);
    }

  }

  private static int[] getRandomListOfNumbers(int numbersToGenerate)
  {
    int[] numbers = new int[numbersToGenerate];
    for (int i = 0; i < numbersToGenerate; i++)
    {
      numbers[i] = (int) (Math.random() * 1000);
    }
    return numbers;
  }

  private static long benchmarkSort(NumberSorter sorter, int[] numbersToSort)
  {
    int[] copy = new int[numbersToSort.length];
    System.arraycopy(numbersToSort, 0, copy, 0, numbersToSort.length);
    long start = System.currentTimeMillis();
    sorter.Sort(copy);
    long end = System.currentTimeMillis();
    return end - start;
  }

  private static void writeResultsToFile(int numbers, long mergeTime, long insertionTime, long mergeInsertionTime) throws IOException
  {
    File file = new File(resultFileName);
    FileWriter fileWriter = new FileWriter(file, true);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    fileWriter.append("\n");
    String dataLine = numbers + "," + mergeTime + "," + insertionTime + "," + mergeInsertionTime;
    fileWriter.append(dataLine);
    bufferedWriter.close();
  }

  private static void writeCsvHeader() throws IOException
  {
    File file = new File(resultFileName);
    FileWriter fileWriter = new FileWriter(file, false);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    fileWriter.write("numbers sorted, merge sort time, insertion sort time, merge-insertion sort time");
    bufferedWriter.close();
  }

}
