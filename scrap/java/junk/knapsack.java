// knapsack

import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Knapsack ks = new Knapsack();
        System.out.print(ks.maxValue(50, Arrays.asList(new Integer[]{60, 100, 120}), Arrays.asList(new Integer[]{10, 20, 30})));
    }
}

class Knapsack
{
    public Integer maxValue (Integer w, List<Integer> val, List<Integer> wt)
    {
        return Math.max(val.get(0) + contemplate(w-wt.get(0), val.subList(1, val.size()), wt.subList(1, wt.size())),
                contemplate(w, val.subList(1, val.size()), wt.subList(1, wt.size())));

    }

    private Integer contemplate(Integer w, List<Integer> val, List<Integer> wt)
    {
        if (w<0)
        {
            return Integer.MIN_VALUE;
        }
        else if (w==0)
        {
            return 0;
        }
        else if (val.size() == 0)
        {
            return 0;
        }

        return Math.max(val.get(0) + contemplate(w-wt.get(0), val.subList(1, val.size()), wt.subList(1, wt.size())),
                contemplate(w, val.subList(1, val.size()), wt.subList(1, wt.size())));
    }

}