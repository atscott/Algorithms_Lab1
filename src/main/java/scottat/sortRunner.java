package scottat;

import Sorting.*;
import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * User: atscott
 * Date: 3/12/14
 * Time: 1:25 PM
 */
public class sortRunner
{
  public static void main(String args[])
  {
    int[] someNumbers = getRandomListOfNumbers(100000);
    NumberSorter mergeSorter = new MergeSort();
    NumberSorter insertionSorter = new InsertionSort();
    System.out.println("" + benchmarkSort(mergeSorter, someNumbers));
    System.out.println("" + benchmarkSort(insertionSorter, someNumbers));

  }

  private static int[] getRandomListOfNumbers(int numbersToGenerate)
  {
    int[] numbers = new int[numbersToGenerate];
    Random rand = new Random();
    for (int i = 0; i < numbersToGenerate; i++)
    {
      numbers[i] = rand.nextInt();
    }
    return numbers;
  }

  private static long benchmarkSort(NumberSorter sorter, int[] numbersToSort)
  {
    int[] copy = new int[numbersToSort.length];
    System.arraycopy(numbersToSort, 0, copy, 0, numbersToSort.length);
    long start = new Date().getTime();
    sorter.Sort(copy);
    return new Date().getTime() - start;
  }

}
