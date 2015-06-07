// Subset Sum backtracing
// code question 56

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[] {10, 7, 5, 18, 12, 20, 15};
        List<Integer> bag = new ArrayList<>();
        for (Integer i : array)
        {
            bag.add(i);
        }

        SubSetCalculator ssc = new SubSetCalculator();
        ssc.findSubSet(bag, 35).stream().forEach(x -> System.out.println(x));
    }
}

class SubSetCalculator
{
    private Integer target;

    public List<Integer> findSubSet(List<Integer> bag, Integer target)
    {
        this.target = target;

        List<Integer> subset = new ArrayList<>();
        for (Integer cursor = 0; cursor<bag.size(); ++cursor)
        {
            Integer thisSum = bag.get(cursor);
            List<Integer> newBag = new ArrayList<>(bag.subList(cursor+1, bag.size()));
            List<Integer> recusriveSum = find(thisSum, newBag);
            if (recusriveSum != null)
            {
                subset.addAll(recusriveSum);
                subset.add(bag.get(cursor));
                break;
            }
        }

        return subset;
    }

    private List<Integer> find(Integer previousSum, List<Integer> bag)
    {
        if (bag.size() == 1)
        {
            if (previousSum + bag.get(0) == target)
            {
                return bag;
            }
            else
            {
                return null;
            }
        }

        List<Integer> thisList = new ArrayList<>();
        for (Integer cursor = 0; cursor<bag.size(); ++cursor)
        {
            Integer thiaSum = previousSum + bag.get(cursor);
            List<Integer> newBag = new ArrayList<>(bag.subList(cursor+1, bag.size()));
            if (thiaSum == target)
            {
                thisList.add(bag.get(cursor));
                return thisList;
            }

            List<Integer> recuriveSum = find(thiaSum, newBag);
            if (recuriveSum != null)
            {
                thisList.addAll(recuriveSum);
                thisList.add(bag.get(cursor));
                return thisList;
            }
        }

        return null;
    }
}