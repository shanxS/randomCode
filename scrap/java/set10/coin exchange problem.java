// coin exchange problem

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[] {2,5,3,6};//{1,2,3};
        CoinChanger cc = new CoinChanger(array, 10);
        System.out.print("sum " + cc.change());
    }
}

class CoinChanger
{
    private final Integer target;
    Integer[] set;
    List<List<Integer>> ans;
    
    public CoinChanger(Integer[] array, Integer target)
    {
        this.set = array;
        this.target = target;
        this.ans = new ArrayList<>();
    }
    
    public Integer change()
    {
        List<List<Integer>> lists = new ArrayList<>();
        Integer result = change(set.length, target, lists);

        for (List<Integer> list : lists)
        {
            for (Integer i : list)
            {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        return result;
    }

    private Integer change(int size, int currentTarget, List<List<Integer>> lists)
    {
        if (currentTarget == 0)
        {
            ArrayList<Integer> list = new ArrayList<>();
            lists.add(list);
            return 1;
        }
        if (size <= 0 && currentTarget != 0)
        {
            return 0;
        }
        if (currentTarget < 0)
        {
            return 0;
        }

        List<List<Integer>> exclusionLists = new ArrayList<>();
        Integer exclusionResult = change(size-1, currentTarget, exclusionLists);

        List<List<Integer>> inclusionLists = new ArrayList<>();
        Integer inclusionResult = change(size, currentTarget - set[size - 1], inclusionLists);
        if (inclusionResult >= 1)
        {
            for (List<Integer> list : inclusionLists)
            {
                list.add(set[size - 1]);
            }
        }

        lists.addAll(inclusionLists);
        lists.addAll(exclusionLists);

        Integer result = inclusionResult + exclusionResult;

        return result;
    }
}
