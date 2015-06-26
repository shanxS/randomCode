// Maximum circular subarray sum
// code question: 118

public class Main
{
    private static Integer globalStartIndex, globalEndIndex, globalMax;
    private static Integer[] array = {50,25,25,-19,1};
            //{10, -3, -4, 7, 6, 5, -4, -1};
    // {50,25,25,-10,-1,1,-2};//{8, -8, 9, -9, 10, -11, 12};

    public static void main(String[] er)
    {

        computeKadane(0, array.length-1);
        Integer normalMax = globalMax;

        Integer arraySum = 0;
        for (Integer i=0; i<array.length; ++i)
        {
            arraySum += array[i];
            array[i] *= -1;
        }

        computeKadane(0, array.length-1);
        Integer maxNegativeSea = globalMax;

        if (arraySum + maxNegativeSea > normalMax)
        {
            System.out.print(arraySum + maxNegativeSea);
        }
        else
        {
            System.out.print(normalMax);
        }
    }

    private static void computeKadane(Integer startIndex, Integer endIndex)
    {
        globalMax = 0;
        Integer currentMax = 0;
        Integer currentStartIndex = startIndex;
        Integer currentEndIndex = startIndex;

        Integer cursor = startIndex;
        while(cursor <= endIndex)
        {
            if (array[cursor] < 0 && currentMax > globalMax)
            {
                globalMax = currentMax;
                globalStartIndex = currentStartIndex;
                globalEndIndex = currentEndIndex;
            }

            currentMax += array[cursor];
            if (currentMax < 0)
            {
                currentMax = 0;
                currentStartIndex = -1;
                currentEndIndex = -1;
            }
            else
            {
                if (currentStartIndex == -1)
                {
                    currentStartIndex = cursor;
                    currentEndIndex = cursor;
                }
                else
                {
                    currentEndIndex = cursor;
                }
            }

            ++cursor;
        }

        if (currentMax > globalMax)
        {
            globalMax = currentMax;
            globalStartIndex = currentStartIndex;
            globalEndIndex = currentEndIndex;
        }
    }
}