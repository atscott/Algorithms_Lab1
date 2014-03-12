package Sorting;

import java.util.List;

/**
 * User: atscott
 * Date: 3/12/14
 * Time: 1:18 PM
 */
public class InsertionSort implements NumberSorter
{
  @Override
  public int[] Sort(int[] listOfNumbers)
  {
    for (int j = 2; j < listOfNumbers.length; j++)
    {
      int key = listOfNumbers[j];
      int i = j - 1;
      while (i > 0 && listOfNumbers[i] > key)
      {
        listOfNumbers[i + 1] = listOfNumbers[i];
        i = i - 1;
      }
      listOfNumbers[i + 1] = key;
    }
    return null;
  }
}
