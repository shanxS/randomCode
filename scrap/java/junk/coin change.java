// coin change

import java.util.Arrays;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] change = {2, 5, 3, 6};//{1,2,3};
        CoinChanger cc = new CoinChanger();
        System.out.print(cc.count(change, 10));
    }
}

class CoinChanger
{
    private Integer count;
    public Integer count (Integer[] changes, Integer N)
    {
        count = 0;

        return contemplate(N, Arrays.copyOfRange(changes, 1, changes.length))
                + contemplate(N-changes[0], changes);
    }

    private Integer contemplate(Integer N, Integer[] changes)
    {
        if (N == 0)
        {
            return 1;
        }
        else if (N < 0 || changes.length == 0)
        {
            return 0;
        }

        return contemplate(N, Arrays.copyOfRange(changes, 1, changes.length))
                + contemplate(N-changes[0], changes);
    }
}