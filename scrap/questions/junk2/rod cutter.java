// rod cutter

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {3 ,  5 ,  8 ,  9 , 10,  17 , 17 , 20};
        System.out.print((new RodCutter()).find(ar));
    }
}

class RodCutter
{
    private int[] cost;
    private int len;

    public Integer find(int[] cost)
    {
        this.cost = cost;
        len = cost.length;

        Integer max = 0;
        for (Integer i=0; i<len; ++i)
        {
            max = Math.max(max, Math.max(cost[i] + contemplate(len-(i+1)),
                    cost[len-1]));
        }

        return max;
    }

    private Integer contemplate(int len)
    {
        Integer max = 0;
        for (Integer i=0; i<len; ++i)
        {
            max = Math.max(max, Math.max(cost[i] + contemplate(len-(i+1)),
                    cost[len-1]));
        }

        return max;
    }
}