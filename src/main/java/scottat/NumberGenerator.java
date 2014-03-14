package scottat;

/**
 * User: atscott
 * Date: 3/13/14
 * Time: 6:18 PM
 */
public class NumberGenerator
{
  public int[] GetRandomListOfNumbers(int numbersToGenerate)
  {
    int[] numbers = new int[numbersToGenerate];
    for (int i = 0; i < numbersToGenerate; i++)
    {
      numbers[i] = (int) (Math.random() * 1000);
    }
    return numbers;
  }

  public int[] GetNumbersInOrderAsc(int numbersToGenerate)
  {
    int[] numbers = new int[numbersToGenerate];
    for (int i = 0; i < numbersToGenerate; i++)
    {
      numbers[i] = i;
    }
    return numbers;
  }

  public int[] GetNumbersInOrderDesc(int numbersToGenerate)
  {
    int[] numbers = new int[numbersToGenerate];
    for (int i = 0; i < numbersToGenerate; i++)
    {
      numbers[i] = numbersToGenerate-i;
    }
    return numbers;
  }
}
