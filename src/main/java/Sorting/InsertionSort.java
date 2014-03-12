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
  public void Sort(int[] listOfNumbers)
  {

    for (int j = 1; j < listOfNumbers.length; j++)
    {
      int key = listOfNumbers[j];
      int i;
      for (i = j - 1; (i >= 0) && (listOfNumbers[i] < key); i--)
      {
        listOfNumbers[i + 1] = listOfNumbers[i];
      }
      listOfNumbers[i + 1] = key;
    }
  }
}
