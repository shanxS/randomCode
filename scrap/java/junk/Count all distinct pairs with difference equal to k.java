// Count all distinct pairs with difference equal to k

import java.util.Arrays;

public class Main
{
    private static Integer[] array = {8, 12, 16, 4, 0, 20};//{1, 5, 3, 4, 2};
    final private static Integer K = 4;//3;

    public static void main(String[] er)
    {
        Arrays.sort(array);
        Integer startCursor = 0;
        Integer endCursor = 1;
        Integer count = 0;

        while (startCursor < endCursor && endCursor < array.length)
        {
            Integer thisDiff = array[endCursor] - array[startCursor];

            if (thisDiff == K)
            {
                ++count;
                ++startCursor;
                ++endCursor;
            }
            else if (thisDiff > K)
            {
                if (startCursor + 1 == endCursor)
                {
                    ++startCursor;
                    ++endCursor;
                }
                else
                {
                    ++startCursor;
                }
            }
            else if (thisDiff < K)
            {
                ++endCursor;
            }
        }

        System.out.print(count);
    }
}