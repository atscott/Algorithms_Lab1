package main.java.Sorting;


/**
 * User: atscott
 * Date: 3/12/14
 * Time: 1:18 PM
 */
public class InsertionSort implements NumberSorter
{
  @Override
  public void sort(int[] listOfNumbers)
  {

    int temp;
    int pos;

    for (int i = 1; i < listOfNumbers.length; i++)
    {
      temp = listOfNumbers[i];
      pos = i - 1;

      while ((pos >= 0) && (temp < listOfNumbers [pos]))
      {
        listOfNumbers[pos + 1] = listOfNumbers[pos];
        pos--;
      }
      listOfNumbers[pos + 1] = temp;
    }
  }
}
