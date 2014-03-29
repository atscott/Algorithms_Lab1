package scottat;

import Sorting.*;

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

  public static void main(String args[]) throws IOException
  {
    NumberGenerator gen = new NumberGenerator();
    getListOfHowManyNumbersToGenerate();
    SortBenchmarker randomBenchmarker = new SortBenchmarker("RandomResultsAll.csv");
    SortBenchmarker worstBenchmarker = new SortBenchmarker("WorstResultsAll.csv");
    SortBenchmarker bestBenchmarker = new SortBenchmarker("BestResultsAll.csv");
    for (Integer number : numbersToGenerate)
    {
      int[] randomListOfNumbers = gen.GetRandomListOfNumbers(number);
      int[] sortedAsc = gen.GetNumbersInOrderAsc(number);
      int[] sortedDesc = gen.GetNumbersInOrderDesc(number);
      randomBenchmarker.benchmarkAllSorts(randomListOfNumbers);
      bestBenchmarker.benchmarkAllSorts(sortedAsc);
      worstBenchmarker.benchmarkAllSorts(sortedDesc);
    }
  }


  private static void getListOfHowManyNumbersToGenerate()
  {
    for (int i = 200; i < 2000; i += 10)
    {
      numbersToGenerate.add(i);
    }
    for (int i = 2000; i < 200000; i *= 2)
    {
      numbersToGenerate.add(i);
    }
    for (int i = 200000; i <= 1000000; i += 100000)
    {
      numbersToGenerate.add(i);
    }
  }

}
