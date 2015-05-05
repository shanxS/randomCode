// Given array, find all possible sets of elements which add up to a given integer K
// r3, q1, set10 amazon

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = {1,1,2,3,4,6,8,10};//{2,3,5,6,4};

        SetFinder sf = new SetFinder(array, 8);
        System.out.println(sf.find());
    }
}

class SetFinder
{
    List<Integer> globalBag;
    Integer globalTarget;

    public SetFinder(Integer[] array, Integer target)
    {
        this.globalBag = Arrays.asList(array);
        this.globalTarget = target;
    }

    public Integer find()
    {
        List<Integer> newBag = new ArrayList<>(globalBag);
        Integer number = newBag.get(0);
        newBag.remove(number);

        List<List<Integer>> lists = new ArrayList<>();

        List<List<Integer>> inclusionList = new ArrayList<>();
        Integer inclusionResult  = findSet(globalTarget-number, newBag, inclusionList);
        if (inclusionResult >= 1)
        {
            for (List<Integer> list : inclusionList)
            {
                list.add(number);
            }
        }

        List<List<Integer>> exlcusionList = new ArrayList<>();
        Integer exclusionResult= findSet(globalTarget, newBag, exlcusionList);

        lists.addAll(exlcusionList);
        lists.addAll(inclusionList);

        print(lists);

        return exclusionResult + inclusionResult;
    }

    private void print(List<List<Integer>> lists)
    {
        for (List<Integer> list : lists)
        {
            list.stream().forEach(x -> System.out.print(" " + x));
            System.out.println();
        }
    }

    private Integer findSet(Integer thisTarget, List<Integer> thisBag, List<List<Integer>> lists)
    {

        if (thisTarget < 0)
        {
            return 0;
        }

        if (thisTarget == 0)
        {
            lists.add(new ArrayList<>());
            return 1;
        }

        if (thisBag.size() == 0)
        {
            return 0;
        }

        List<Integer> newBag = new ArrayList<>(thisBag);
        Integer number = newBag.get(0);
        newBag.remove(number);

        List<List<Integer>> inclusionList = new ArrayList<>();
        Integer inclusionResult = findSet(thisTarget - number, newBag, inclusionList);
        if (inclusionResult >= 1)
        {
            for (List<Integer> list : inclusionList)
            {
                list.add(number);
            }
        }

        List<List<Integer>> exlusionList = new ArrayList<>();
        Integer exclusionResult = findSet(thisTarget, newBag, exlusionList);

        lists.addAll(inclusionList);
        lists.addAll(exlusionList);

        return inclusionResult + exclusionResult;
    }
}