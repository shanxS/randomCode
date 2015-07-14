// max sum sub array

public class Main
{
    public static void main(String[] er)
    {
        Integer[] a = {1, 101, 2, 3, 100, 4, 5};
        MaxSumSub mss = new MaxSumSub();
        System.out.print(mss.find(a));
    }
}

class MaxSumSub
{
    private Integer[] a, dp;
    private Integer max;


    public Integer find(Integer[] a)
    {
        this.a = a;
        this.dp = new Integer[a.length];
        this.max = Integer.MIN_VALUE;

        compute(0);

        return max;
    }

    private void compute(Integer index)
    {
        if (index == a.length - 1)
        {
            dp[index] = a[index];
            max = Math.max(max, dp[index]);
            return;
        }
        else
        {
            compute(index + 1);
        }

        Integer thisMax = a[index];
        for (Integer i = index + 1; i < a.length; ++i)
        {
            if (a[index] < a[i])
            {
                thisMax = Math.max(thisMax, a[index] + dp[i]);
            }
        }
        dp[index] = thisMax;
        max = Math.max(max, dp[index]);
    }
}