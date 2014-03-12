package scottat;

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
    int[] someNumbers = getRandomListOfNumbers(1000000000);


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

}
