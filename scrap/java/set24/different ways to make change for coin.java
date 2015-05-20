// different ways to make change for coin
// online q1, set 24

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{2,4};
        Counter c = new Counter(array);
        System.out.print(c.calculateCount(3));
    }
}

class Counter
{
    private Integer count;
    private Integer[] coins;

    public Counter(Integer[] coins)
    {
        this.coins = coins;
        this.count = 0;
    }

    public Integer calculateCount(Integer sum)
    {
        calculateCount(sum-coins[0], 0, coins.length);
        calculateCount(sum, 1, coins.length);
        return count;
    }

    private void calculateCount(Integer sum, Integer startIndex, Integer length)
    {
        if (sum == 0)
        {
            ++count;
            return;
        }

        if (sum < 0 || startIndex == length)
        {
            return;
        }

        calculateCount(sum-coins[startIndex], startIndex, length);
        calculateCount(sum, startIndex+1, length);
    }
}