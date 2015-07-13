// rod cutter

public class Main
{
    public static void main(String[] er)
    {
        RodCutter rc = new RodCutter();
//        System.out.print(rc.maxPrice(8, new Integer[]{1,5,8,9,10,17,17,20}));
        System.out.print(rc.maxPrice(8, new Integer[]{3,5,8,9,10,17,17,20}));
    }
}

class RodCutter
{
    private Integer[] prices;

    public Integer maxPrice(Integer L, Integer[] prices)
    {
        this.prices = prices;

        Integer size = 1;
        return Math.max(contemplate(L, size+1),
                prices[size-1] + contemplate(L-size, size));
    }

    private Integer contemplate(Integer L, Integer size)
    {
        if (L == 0 || (size-1)>=prices.length)
        {
            return 0;
        }
        else if (L < 0)
        {
            return Integer.MIN_VALUE;
        }

        return Math.max(contemplate(L, size+1),
                prices[size-1] + contemplate(L-size, size));
    }
}