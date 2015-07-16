// Maximum Product Subarray

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] ar = {2,900,-1,100,2,4};
//        Integer[] ar = {2,9,-1,100,2,4};
//        Integer[] ar = {2,-1};
        Integer[] ar = {2,900,-1,100,2,4,-1};

        Integer currentMax = null;
        Integer currentStart = null;
        Integer currentEnd = null;

        Integer maxSoFar = null;
        Integer maxStart = null;
        Integer maxEnd = null;

        for (Integer i=0; i<ar.length; ++i)
        {
            if (i==0 || (ar[i] < 0 && currentMax > 0))
            {
                currentMax = ar[i];
                currentEnd = i;
                currentStart = i;
            }
            else
            {
                currentMax *= ar[i];
                currentEnd = i;
            }

            if (currentMax > 0)
            {
                if (maxSoFar == null)
                {
                    maxSoFar = currentMax;
                    maxEnd = currentEnd;
                    maxStart = currentStart;
                }
                else
                {
                    if (maxStart == currentStart)
                    {
                        maxSoFar = currentMax;
                        maxEnd = currentEnd;
                    }
                    else
                    {
                        maxSoFar *= currentMax;
                        currentStart = maxStart;
                        maxEnd = currentEnd;
                    }
                }
            }
        }

        if (currentMax < 0)
        {
            currentMax /= ar[currentStart];
            ++currentStart;
            if (maxSoFar == null)
            {
                maxSoFar = currentMax;
                maxEnd = currentEnd;
                maxStart = currentStart;
            }
            else
            {
                if (maxSoFar < currentMax)
                {
                    maxSoFar = currentMax;
                    maxEnd = currentEnd;
                    maxStart = currentStart;
                }
            }
        }

        System.out.print(maxSoFar);
    }
}