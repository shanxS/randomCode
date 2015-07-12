// LIS using dp

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = { 10, 22, 9, 33, 21, 50, 41, 60 };
        LIS lis = new LIS();
        System.out.print(lis.find(ar));
    }
}

class LIS
{
    private Integer[] ar;
    private int[] dp;
    public Integer find(Integer[] ar)
    {
        dp = new int[ar.length];
        this.ar = ar;

        contemplate(0);

        Integer max = Integer.MIN_VALUE;
        for (Integer i=0; i<dp.length; ++i)
        {
            if (max < dp[i])
            {
                max = dp[i];
            }
        }

        return max;
    }

    private void contemplate(int thisIndex)
    {
        if (thisIndex < ar.length-1)
        {
            contemplate(thisIndex + 1);
        }
        else if(thisIndex == ar.length-1)
        {
            dp[thisIndex] = 1;
            return;
        }

        Integer cussor = thisIndex+1;
        while (cussor < ar.length && ar[thisIndex] > ar[cussor])
        {
            ++cussor;
        }

        if (cussor<ar.length)
        {
            dp[thisIndex] = 1 + dp[cussor];
        }
        else
        {
            dp[thisIndex] = 1;
        }
    }
}