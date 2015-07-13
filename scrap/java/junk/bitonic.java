// bitonic

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] ar = {1, 11, 2, 10, 4, 5, 2, 1};
//        Integer[] ar = {12, 11, 40, 5, 3, 1};
        Integer[] ar = {80, 60, 30, 40, 20, 10};
        Bitonic bt = new Bitonic();
        System.out.print(bt.find(ar));
    }
}


class Bitonic
{
    private Integer[] LIS, LDS, ar;

    public Integer find(Integer[] ar)
    {
        this.ar = ar;
        computeLIS();
        computeLDS();

        Integer maxSum = Integer.MIN_VALUE;
        for (Integer i=0; i<ar.length; ++i)
        {
            maxSum = Math.max(LIS[i] + LDS[i], maxSum);
        }

        return maxSum-1;
    }

    private void computeLDS()
    {
        LDS = new Integer[ar.length];
        LDS[ar.length-1] = 1;
        for(Integer i=ar.length-2; i>=0; --i)
        {
            Integer previousIndex = findPreviousBigIndex(i);
            if (previousIndex == ar.length)
            {
                LDS[i] = 1;
            }
            else
            {
                LDS[i] = 1 + LDS[previousIndex];
            }
        }
    }

    private Integer findPreviousBigIndex(Integer cursor)
    {
        final Integer thisValue = ar[cursor];
        ++cursor;
        while (cursor < ar.length && thisValue < ar[cursor])
        {
            ++cursor;
        }

        return cursor;
    }

    private void computeLIS()
    {
        LIS = new Integer[ar.length];
        LIS[0] = 1;
        for (Integer i=1; i<ar.length; ++i)
        {
            Integer previousIndex = findPreviousSmallIndex(i);
            if (previousIndex == -1)
            {
                LIS[i] = 1;
            }
            else
            {
                LIS[i] = 1+ LIS[previousIndex];
            }
        }
    }

    private Integer findPreviousSmallIndex(Integer cursor)
    {
        final Integer thisValue = ar[cursor];
        --cursor;
        while (cursor >= 0 && ar[cursor] > thisValue)
        {
            --cursor;
        }

        return cursor;
    }
}