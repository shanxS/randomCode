// kadane
// r2, q2, set15

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{-2,1,-3,4,-1,2,1,-5,4};
        MaxSubArraySum maxSubArraySum = new MaxSubArraySum(array);
        System.out.print(maxSubArraySum.getMaxSum());
    }
}

class MaxSubArraySum
{
    private Integer[] array;

    public MaxSubArraySum(Integer[] array)
    {
        this.array = array;
    }

    public Integer getMaxSum()
    {
        Integer maxSoFar = array[0];
        Integer currentMax = array[0];
        Integer start = 0;
        Integer end = 0;

        for (Integer cursor=1; cursor < array.length; ++cursor)
        {
            if (currentMax <= 0)
            {
                currentMax = 0;
            }

            Integer previousCurrentMax = currentMax;
            currentMax += array[cursor];
            if (currentMax > maxSoFar)
            {
                maxSoFar = currentMax;
                if (previousCurrentMax == 0)
                {
                    start = cursor;
                    end = cursor;
                }
                else
                {
                    end = cursor;
                }
            }
        }

        for (Integer i=start; i<=end; ++i)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        return maxSoFar;
    }
}