// Maximum Sum Increasing Subsequence

public class Main
{
    private static Integer[] array = {1, 101, 2, 3, 100, 4, 5};
    private static Integer largestSoFar = Integer.MIN_VALUE;
    private static Integer[] dp = new Integer[array.length];

    public static void main(String[] er)
    {
        for (Integer i=0; i<array.length; ++i)
        {
            testForIndex(i);
        }

        System.out.println(largestSoFar);
    }

    private static Integer testForIndex(final Integer startIndex)
    {
        if (startIndex >= array.length)
        {
            return Integer.MIN_VALUE;
        }

        if (dp[startIndex] != null)
        {
            return dp[startIndex];
        }

        Integer childMax = 0;
        Integer cursor = startIndex + 1;
        while (cursor < array.length)
        {
            if (array[startIndex] < array[cursor])
            {
                Integer tmp = testForIndex(cursor);
                if (tmp > childMax)
                {
                    childMax = tmp;
                }
            }
            ++cursor;
        }

        if (largestSoFar < array[startIndex] + childMax)
        {
            largestSoFar = array[startIndex] + childMax;
        }

        dp[startIndex] = array[startIndex] + childMax;

        return dp[startIndex];
    }


}