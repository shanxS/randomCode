// Partition problem

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        //Integer[] a = {1, 5, 11, 5};
        Integer[] a = {1, 5, 3};
        List<Integer> l = new ArrayList<>();
        for (Integer i : a)
        {
            l.add(i);
        }
        PossibilityFinder pf = new PossibilityFinder();
        System.out.print(pf.find(l));
    }
}

class PossibilityFinder
{
    public boolean find(List<Integer> list)
    {
        Integer thisSum = sum(list);
        if (thisSum%2 != 0)
        {
            return false;
        }

        for (Integer i=0; i<list.size(); ++i)
        {
            List<Integer> bag = new ArrayList<>();
            bag.addAll(list);
            bag.remove((int) i);
            if (contempalte(bag, thisSum/2))
            {
                System.out.print(list.get(i) + " ");
                return true;
            }
        }

        return false;
    }

    private boolean contempalte(List<Integer> list, Integer targetSum)
    {
        Integer thisSum = sum(list);
        if (thisSum == targetSum)
        {
            for (Integer i : list)
            {
                System.out.print(i + " ");
            }
            System.out.println();

            return true;
        }

        if (thisSum % 2 != 0)
        {
            return false;
        }

        for (Integer i=0; i<list.size(); ++i)
        {
            List<Integer> bag = new ArrayList<>();
            bag.addAll(list);
            bag.remove((int) i);
            if (contempalte(bag, targetSum))
            {
                System.out.print(list.get(i) + " ");

                return true;
            }
        }

        return false;
    }

    private Integer sum(List<Integer> list)
    {
        Integer sum = 0;
        for (Integer s : list)
        {
            sum += s;
        }

        return sum;
    }


}