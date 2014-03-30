package main.java.Sorting;

import java.util.Arrays;

/**
 * Created by atscott on 3/12/14.
 */
public class MergeInsertionSort implements NumberSorter
{
  public void sort(int[] listOfNumbers)
  {
    if (listOfNumbers.length > 1)
    {
      if (listOfNumbers.length < 50)
      {
        NumberSorter sorter = new InsertionSort();
        sorter.sort(listOfNumbers);
      } else
      {
        int q = listOfNumbers.length / 2;

        int[] leftArray = Arrays.copyOfRange(listOfNumbers, 0, q);
        int[] rightArray = Arrays.copyOfRange(listOfNumbers, q, listOfNumbers.length);

        sort(leftArray);
        sort(rightArray);

        merge(listOfNumbers, leftArray, rightArray);
      }
    }
  }

  private void merge(int[] a, int[] l, int[] r)
  {
    int totElem = l.length + r.length;
    //int[] a = new int[totElem];
    int i, li, ri;
    i = li = ri = 0;
    while (i < totElem)
    {
      if ((li < l.length) && (ri < r.length))
      {
        if (l[li] < r[ri])
        {
          a[i] = l[li];
          i++;
          li++;
        } else
        {
          a[i] = r[ri];
          i++;
          ri++;
        }
      } else
      {
        if (li >= l.length)
        {
          while (ri < r.length)
          {
            a[i] = r[ri];
            i++;
            ri++;
          }
        }
        if (ri >= r.length)
        {
          while (li < l.length)
          {
            a[i] = l[li];
            li++;
            i++;
          }
        }
      }
    }
  }
}
