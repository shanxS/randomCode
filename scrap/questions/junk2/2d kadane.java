2d kadane

public class Main
{
    public static void main(String[] er)
    {
        int[][] ar = {{1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };

        (new Kadane2D()).compute(ar);

    }
}

class Kadane2D
{
    private Integer maxValue, maxRStart, maxREnd, maxCStart, maxCEnd;

    public void compute(int[][] ar)
    {
        maxValue = Integer.MIN_VALUE;
        Kadane k = new Kadane();

        for (int startC=0; startC<ar[0].length; ++startC)
        {
            int[] rowSum = new int[ar.length];
            for (int endC = startC; endC<ar[0].length; ++endC)
            {
                for (int r=0; r<rowSum.length; ++r)
                {
                    rowSum[r] += ar[r][endC];
                }

                k.compute(rowSum);
                if (k.maxValue > maxValue)
                {
                    maxValue = k.maxValue;
                    maxCEnd = endC;
                    maxCStart = startC;
                    maxREnd = k.maxEnd;
                    maxRStart = k.maxStart;
                }
            }
        }

        System.out.print(maxValue + " " + maxCStart + " " + maxCEnd + " " + maxRStart + " " + maxREnd);
    }
}

class Kadane
{
    public Integer maxValue, maxStart, maxEnd;
    public void compute(int[] ar)
    {
        int end = 0;
        int start = 0;
        int max = ar[0];
        maxValue = max;
        maxStart = start;
        maxEnd = end;

        for (int i=1; i<ar.length; ++i)
        {
            if (max < 0 && ar[i] > 0)
            {
                max = ar[i];
                start = i;
                end = i;
            }
            else
            {
                max += ar[i];
                end = i;
            }

            if (max > maxValue)
            {
                maxValue = max;
                maxStart = start;
                maxEnd = end;
            }
        }
    }
}