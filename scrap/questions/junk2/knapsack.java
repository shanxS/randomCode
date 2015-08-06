// knapsack

public class Main
{
    public static void main(String[] er)
    {
        int val[] = {60, 100, 120};
        int wt[] = {10, 20, 30};
        int  W = 50;
        System.out.print((new Knapsack()).find(val, wt, W));
    }
}

class Knapsack
{
    private int[] val, wt;
    private int w;
    public Integer find(int[] val, int[] wt, int w)
    {
        this.val = val;
        this.wt = wt;
        this.w = w;

        return contemplate(0, 0, w);
    }

    private Integer contemplate(int valIndex, int wtIndex, int thisW)
    {
        if (thisW < 0)
        {
            return Integer.MIN_VALUE;
        }
        if (valIndex >= val.length || wtIndex >= wt.length)
        {
            return 0;
        }

        return Math.max(val[valIndex] + contemplate(valIndex+1, wtIndex+1, thisW-wt[wtIndex]),
                contemplate(valIndex+1, wtIndex+1, thisW));
    }
}