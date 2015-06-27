// divide and conquer to find max sum of sub array

public class Main
{
    private static Integer[] array = {-2, -5, 6, -2, -3, 1, 5, -6};

    public static void main(String[] er)
    {
        System.out.print(getMaxSumForRange(0, array.length-1));
    }

    private static Integer getMaxSumForRange(Integer startIndex, Integer endIndex)
    {
        if (startIndex == endIndex)
        {
            return array[startIndex];
        }

        Integer midIndex = startIndex + (endIndex-startIndex)/2;

        return Math.max(getMaxSumForRange(startIndex, midIndex),
                Math.max(getMaxSumForRange(midIndex+1, endIndex), getSumAcrossMid(startIndex, midIndex, endIndex)));
    }

    private static Integer getSumAcrossMid(Integer startIndex, Integer midIndex, Integer endIndex)
    {
        Integer leftSum = Integer.MIN_VALUE;
        Integer sum = 0;
        for(Integer i=midIndex; i>=startIndex; --i)
        {
            sum += array[i];
            if (sum > leftSum)
            {
                leftSum = sum;
            }
        }

        Integer rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (Integer i=midIndex+1; i<=endIndex; ++i)
        {
            sum += array[i];
            if (sum > rightSum)
            {
                rightSum = sum;
            }
        }

        return leftSum + rightSum;
    }
}