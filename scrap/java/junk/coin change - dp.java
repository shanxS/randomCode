// coin change

public class Main
{
    private static Integer[] coins = {2, 5, 3, 6};//{1,2,3};
    static private Integer N = 10;//4;
    static private int[] T;

    public static void main(String[] er)
    {
        Integer sum = 0;
        for (Integer i : coins)
        {
            sum += i;
        }

        T = new int[sum+1];
        T[0] = 1;
        for (Integer i=0; i<coins.length; ++i)
        {
            for (Integer j=0; (j + coins[i]) < sum+1; ++j)
            {
                T[j + coins[i]] += T[j];
            }
        }

        for (Integer i=0; i<sum+1; ++i)
        {
            System.out.println(i + ": " + T[i]);
        }
    }
}