// Matrix Chain Multiplication

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Main obj = new Main();
        System.out.print(obj.computeMinCost(new Integer[] {40, 20, 30, 10, 30}));
    }

    private Integer computeMinCost(Integer[] integers)
    {
        List<Integer> list = new ArrayList<>();
        for (Integer i:integers)
        {
            list.add(i);
        }

        return computeMinCostFor(list);
    }

    private Integer computeMinCostFor(List<Integer> list)
    {
        if (list.size() == 3)
        {
            return computeCost(list);
        }

        Integer thisCost = Integer.MAX_VALUE;
        for (Integer i=0; i<list.size()-2; ++i)
        {
            List<Integer> bag = new ArrayList<>(list);
            bag.remove((int) (i+1));

            Integer newCost = computeCost(list.get(i), list.get(i+1), list.get(i+2))
                              + computeMinCostFor(bag);

            thisCost = Math.min(thisCost, newCost);
        }

        return thisCost;
    }

    private Integer computeCost(List<Integer> list)
    {
        return computeCost(list.get(0), list.get(1), list.get(2));
    }

    private Integer computeCost(Integer num1, Integer num2, Integer num3)
    {
        return num1 * num2 * num3;
    }
}