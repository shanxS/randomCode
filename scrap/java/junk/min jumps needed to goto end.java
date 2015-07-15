// min jumps needed to goto end

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        MinJumpFinder mjf = new MinJumpFinder();
        System.out.print(mjf.find(ar));
    }
}

class MinJumpFinder
{
    private Integer[] dp;
    private Integer[] ar;

    public Integer find(Integer[] ar)
    {
        this.dp = new Integer[ar.length];
        this.ar = ar;

        Integer min = Integer.MAX_VALUE;
        Integer thisCursor = 0;
        for (Integer i = 1; i<=ar[thisCursor]; ++i)
        {
            min = Math.min(min, contemplate(thisCursor + i));
        }

        return min+1;
    }

    private Integer contemplate(Integer thisCursor)
    {
        if (thisCursor == ar.length-1)
        {
            dp[thisCursor] = 0;
            return dp[thisCursor];
        }
        else if(thisCursor >= ar.length)
        {
            return Integer.MAX_VALUE;
        }

        Integer thisMin = Integer.MAX_VALUE;
        for (Integer i = 1; i<=ar[thisCursor]; ++i)
        {
            thisMin = Math.min(thisMin, contemplate(thisCursor + i));
        }

        return thisMin == Integer.MAX_VALUE ? (Integer.MAX_VALUE) : (thisMin + 1);
    }
}