// max sum of subsequence so that no 2 numbers are alternate
// r1, q2, set36
// code question 31

public class Main
{
    public static void main(String[] er)
    {
        //Integer[] array = new Integer[]{3,2,5,10,7};
        Integer[] array = new Integer[]{3, 2, 7, 10};

        AlternateSummer as = new AlternateSummer();
        System.out.print(as.evaluate(array));
    }
}

class AlternateSummer
{
    private Integer[] dp;
    final private Integer INVALID = -1;
    private Integer[] array;


    public Integer evaluate(Integer[] array)
    {
        dp = new Integer[array.length];
        this.array = array;
        resetDp();

        return Math.max(evaluateIndex(0), evaluateIndex(1));
    }

    private Integer evaluateIndex(Integer index)
    {
        if (!isValidIndex(index))
        {
            return 0;
        }

        if (dp[index] != INVALID)
        {
            return dp[index];
        }

        Integer next = index+2;
        Integer next2 = next+1;

        if (isValidIndex(next) && isValidIndex(next2))
        {
            dp[index] = array[index] + Math.max(evaluateIndex(next), evaluateIndex(next2));
            return dp[index];
        }
        else if (isValidIndex(next))
        {
            dp[index] = array[index] + array[next];
            return dp[index];
        }
        else if (isValidIndex(next2))
        {
            dp[index] = array[index] + array[next2];
            return dp[index];
        }

        dp[index] = array[index];
        return dp[index];

    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < array.length);
    }

    private void resetDp()
    {
        for (Integer i=0; i<dp.length; ++i)
        {
            dp[i] = INVALID;
        }
    }
}