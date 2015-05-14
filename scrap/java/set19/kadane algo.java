// kadane algo
// r2, q1, set19

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{-2,1,-3,4,-1,2,1,-5,4};

        Integer maxSoFar = Integer.MIN_VALUE;
        Integer start = 0;
        Integer end = 0;
        Integer currentMax = 0;
        Integer previousCurrentMax = currentMax;

        for(Integer cursor=0; cursor<array.length; ++cursor)
        {
            previousCurrentMax = currentMax;
            currentMax += array[cursor];
            if (currentMax < 0)
            {
                currentMax = 0;
            }

            if (maxSoFar < currentMax)
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
        System.out.println(maxSoFar);
    }
}