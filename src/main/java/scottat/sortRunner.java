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
    getListOfHowManyNumbersToGenerate();
    writeCsvHeader(lowFileName);
    writeCsvHeader(highFileName);
    writeCsvHeader(combinedFileName);
    for (int number : numbersToGenerate)
    {
      int[] someNumbers = getRandomListOfNumbers(number);
      NumberSorter mergeSorter = new MergeSort();
      NumberSorter insertionSorter = new InsertionSort();
      NumberSorter miSorter = new MergeInsertionSort();
      long mergeTime = benchmarkSort(mergeSorter, someNumbers);
      long insertionTime = benchmarkSort(insertionSorter, someNumbers);
      long miTime = benchmarkSort(miSorter, someNumbers);
      if(number < 1000){
        writeResultsToFile(lowFileName, number, mergeTime, insertionTime, miTime);
      }else{
        writeResultsToFile(highFileName, number, mergeTime, insertionTime, miTime);
      }
      writeResultsToFile(combinedFileName, number, mergeTime, insertionTime, miTime);
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
    long start = System.nanoTime();
    sorter.Sort(copy);
    long end = System.nanoTime();
    return end - start;
  }

  private static void writeResultsToFile(String resultFileName, int numbers, long mergeTime, long insertionTime, long mergeInsertionTime) throws IOException
  {
    File file = new File(resultFileName);
    FileWriter fileWriter = new FileWriter(file, true);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    fileWriter.append("\n");
    String dataLine = numbers + "," + mergeTime + "," + insertionTime + "," + mergeInsertionTime;
    fileWriter.append(dataLine);
    bufferedWriter.close();
  }

  private static void writeCsvHeader(String resultFileName) throws IOException
  {
    File file = new File(resultFileName);
    if (file.exists())
      return;
    FileWriter fileWriter = new FileWriter(file, false);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    fileWriter.write("numbers sorted, merge sort time (ns), insertion sort time (ns), merge-insertion sort time (ns)");
    bufferedWriter.close();
  }

}
