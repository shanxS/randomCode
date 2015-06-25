// Count the number of possible triangles
// code question 41

import java.util.Arrays;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {10, 21, 22, 28, 100};//, 101, 200, 300};
        Arrays.sort(array);

        Integer count = 0;
        Integer startIndex = 0;

        while(startIndex < array.length-2)
        {

            Integer endIndex = startIndex+1;
            Integer k = endIndex+1;
            while(endIndex < array.length-1)
            {

                System.out.println(array[startIndex] + array[endIndex]);
                while (k < array.length && array[startIndex] + array[endIndex] > array[k])
                {
                    ++k;

                }

                count += k-endIndex-1;
                ++endIndex;
            }

            ++startIndex;
        }

        System.out.print(count);
    }
}