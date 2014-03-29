package main.java.Sorting;

/**
 * Created by atscott on 3/20/14.
 * http://www.careerbless.com/samplecodes/java/beginners/sorting/BubbleSortAscending.php
 */
public class BubbleSort implements NumberSorter
{
  @Override
  public void Sort(int[] listOfNumbers)
  {
    int temp;

    for (int i = 0; i < listOfNumbers.length; i++)
    {
      for (int j = 1; j < (listOfNumbers.length - i); j++)
      {
        if (listOfNumbers[j - 1] > listOfNumbers[j])
        {
          temp = listOfNumbers[j - 1];
          listOfNumbers[j - 1] = listOfNumbers[j];
          listOfNumbers[j] = temp;
        }
      }
    }
  }
}
