// Find a pair with the given difference

import java.util.Arrays;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {5, 20, 3, 2, 50, 80};
        Integer diff = 15;

        Arrays.sort(array);

        Integer startIndex = 0;
        Integer endIndex = startIndex+1;

        while (startIndex < endIndex && endIndex < array.length)
        {
            if (array[endIndex] - array[startIndex] == diff)
            {
                System.out.print(array[startIndex] + " " + array[endIndex]);
                break;
            }
            else if (array[endIndex] - array[startIndex] < diff)
            {
                ++endIndex;
            }
            else
            {
                ++startIndex;
            }
        }
    }
}