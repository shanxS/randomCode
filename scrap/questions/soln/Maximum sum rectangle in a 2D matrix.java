// Maximum sum rectangle in a 2D matrix
// code question: 128

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = {
                {1,2,-1,-4,-20},
                {-8,-3,4,2,1},
                {3,8,10,1,3},
                {-4,1,1,7,-6}
        };

        Max2DArray m2a = new Max2DArray();
        m2a.compute(ar);
    }
}

class Max2DArray
{
    private Integer maxRStart, maxCStart, maxREnd, maxCEnd;
    private Integer maxSum;
    private Kadane kadane;
    private Integer[][] ar;

    public void compute(Integer[][] ar)
    {
        reset();

        this.ar = ar;
        final Integer R = ar.length;
        final Integer C = ar[0].length;

        for (Integer cStart=0;cStart<C;++cStart)
        {
            int[] rowSum = new int[R];
            for (Integer cEnd=cStart;cEnd<C; ++cEnd)
            {
                populateFor(rowSum, cEnd);
                kadane.compute(rowSum);

                if (kadane.getMaxSoFar() > maxSum)
                {
                    maxSum = kadane.getMaxSoFar();
                    maxCStart = cStart;
                    maxCEnd = cEnd;
                    maxRStart = kadane.getStartIndex();
                    maxREnd =  kadane.getEndIndex();
                }
            }
        }

        System.out.print("max:" + maxSum
                + " cStart:" + maxCStart + " cEnd:" + maxCEnd
                + " maxRStart:" + maxRStart + " maxREnd:" + maxREnd);
    }

    private void populateFor(int[] rowSum, Integer c)
    {
        for (Integer r=0; r<ar.length; ++r)
        {
            rowSum[r] += ar[r][c];
        }
    }

    private void reset()
    {
        maxSum = Integer.MIN_VALUE;
        maxRStart = null;
        maxCStart = null;
        maxREnd = null;
        maxCEnd = null;
        kadane = new Kadane();
    }


}

class Kadane
{
    private Integer maxSoFar;
    private Integer startIndex;
    private Integer endIndex;
    private Integer cursor;
    private Integer currentStart, currentEnd;

    public void compute(int[] ar)
    {
        reset();

        Integer currnetMax = ar[0];
        while(cursor < ar.length)
        {
            if (currnetMax < 0)
            {
                currnetMax = ar[cursor];
                currentEnd = cursor;
                currentStart = cursor;
            }
            else
            {
                currnetMax += ar[cursor];
                currentEnd = cursor;
            }

            if (maxSoFar < currnetMax)
            {
                maxSoFar = currnetMax;
                startIndex = currentStart;
                endIndex = currentEnd;
            }

            ++cursor;
        }

    }

    public Integer getMaxSoFar()
    {
        return maxSoFar;
    }

    public Integer getStartIndex()
    {
        return startIndex;
    }

    public Integer getEndIndex()
    {
        return endIndex;
    }

    private void reset()
    {
        maxSoFar = Integer.MIN_VALUE;
        startIndex = null;
        endIndex = null;
        cursor = 0;
        currentStart = 0;
        currentEnd = 0;
    }
}