// max sum rectangle

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = {
                {1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };

        MaxSumRectanlge msr = new MaxSumRectanlge();
        System.out.print(msr.sum(ar) +
                "\n C: " + msr.getMaxCStart() + " " + msr.getMaxCEnd() +
                "\n R: " + msr.getMaxRStart() + " " + msr.getMaxREnd());
    }
}

class MaxSumRectanlge
{
    private Integer maxSoFar, maxCStart, maxCEnd, maxRStart, maxREnd;

    public Integer getMaxCStart()
    {
        return maxCStart;
    }

    public Integer getMaxCEnd()
    {
        return maxCEnd;
    }

    public Integer getMaxRStart()
    {
        return maxRStart;
    }

    public Integer getMaxREnd()
    {
        return maxREnd;
    }

    public Integer sum(Integer[][] ar)
    {
        maxSoFar = Integer.MIN_VALUE;

        Integer C = ar[0].length;
        Integer R = ar.length;

        Kadane kadane = new Kadane();

        for (Integer cStart=0; cStart<C; ++cStart)
        {
            int[] sumAr = new int[ar.length];
            for (Integer cEnd = cStart; cEnd<C; ++cEnd)
            {
                for (Integer r = 0; r < R; ++r)
                {
                    sumAr[r] += ar[r][cEnd];
                }

                Integer thisMax = kadane.maxSum(sumAr);
                if (thisMax > maxSoFar)
                {
                    maxSoFar = thisMax;
                    maxCEnd = cEnd;
                    maxCStart = cStart;
                    maxREnd = kadane.getMaxEnd();
                    maxRStart = kadane.getMaxStart();
                }
            }
        }

        return maxSoFar;
    }
}

class Kadane
{
    private Integer maxSoFar;
    private Integer maxStart, maxEnd;

    public Integer maxSum(int[] ar)
    {
        Integer currentMax = ar[0];
        Integer currentStart = 0;
        Integer currentEnd = 0;

        maxSoFar = currentMax;
        maxStart = currentStart;
        maxEnd = currentEnd;

        for (Integer i=1; i<ar.length; ++i)
        {
            if (currentMax < 0)
            {
                currentMax = ar[i];
                currentEnd = i;
                currentStart = i;
            }
            else
            {
                currentMax += ar[i];
                currentEnd = i;
            }

            if (maxSoFar < currentMax)
            {
                maxSoFar = currentMax;
                maxEnd = currentEnd;
                maxStart = currentStart;
            }
        }

        return maxSoFar;
    }

    public Integer getMaxStart()
    {
        return maxStart;
    }

    public Integer getMaxEnd()
    {
        return maxEnd;
    }
}