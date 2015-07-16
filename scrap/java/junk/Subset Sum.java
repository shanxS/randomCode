// Subset Sum

public class Main
{
    private static Integer[] set = {1,200,201,600};//{3, 34, 4, 12, 5, 2};
    public static void main(String[] er)
    {
        final Integer sum = 209;//9;
        System.out.print(contemplate(0, sum));
    }

    private static boolean contemplate(Integer thisIndex, Integer sum)
    {
        if (sum == 0)
        {
            return true;
        }
        else if (thisIndex == set.length)
        {
            return false;
        }

        return contemplate(thisIndex+1, sum-set[thisIndex])
                || contemplate(thisIndex+1, sum);
    }
}