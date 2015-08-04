Maximum sum rec

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = {{1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };

        MaxSumRectangle msr = new MaxSumRectangle();
        msr.find(ar);
    }
}

class MaxSumRectangle
{
    public Integer max, maxCStart, maxCEnd, maxRStart, maxREnd;
    public void find(Integer[][] ar)
    {
        max = Integer.MIN_VALUE;
        maxCStart = null;
        maxCEnd = null;
        maxRStart = null;
        maxREnd = null;

        Kadane kadane = new Kadane();


        for (Integer cStart = 0; cStart < ar[0].length; ++cStart)
        {
            int[] rowSum = new int[ar.length];
            for (Integer cEnd = cStart; cEnd < ar[0].length; ++cEnd)
            {
                for (Integer r=0; r<rowSum.length; ++r)
                {
                    rowSum[r] += ar[r][cEnd];
                }

                kadane.analyse(rowSum);
                if (kadane.maxVal > max)
                {
                    max = kadane.maxVal;
                    maxREnd = kadane.maxEnd;
                    maxRStart = kadane.maxStart;
                    maxCEnd = cEnd;
                    maxCStart = cStart;
                }
            }
        }

        System.out.print(maxCStart + " " + maxCEnd + " " + maxRStart + " " + maxREnd + " " + max);
    }
}

class Kadane
{
    public Integer maxStart, maxEnd, maxVal;

    public void analyse(int[] ar)
    {
        Integer currentMax = ar[0];
        Integer start = 0;
        Integer end = 0;
        maxEnd = end;
        maxStart = start;
        maxVal = currentMax;

        for (Integer i=1; i<ar.length; ++i)
        {
            if (currentMax < 0 && ar[i] > currentMax)
            {
                currentMax = ar[i];
                start = i;
                end = i;
            }
            else
            {
                currentMax += ar[i];
                ++end;
            }

            if (currentMax > maxVal)
            {
                maxVal = currentMax;
                maxStart = start;
                maxEnd = end;
            }
        }

//        System.out.print(maxEnd + " " + maxStart + " " + maxVal);
    }
}