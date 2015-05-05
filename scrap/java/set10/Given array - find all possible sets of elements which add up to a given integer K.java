// Given array, find all possible sets of elements which add up to a given integer K
// r3, q1, set10 amazon

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array =  {1,2,3,4,6,8,10};

        SetFinder sf = new SetFinder(array, 8);
        sf.find();
    }
}

class SetFinder
{
    private final List<Integer> globalNumbers;
    private final Integer globalTarget;

    public SetFinder(Integer[] array, Integer target)
    {
        this.globalNumbers = Arrays.asList(array);
        this.globalTarget = target;
    }

    public Integer find()
    {
        List<List<Integer>> lists = new ArrayList<>();

        for (Integer i=0; i< globalNumbers.size(); ++i)
        {
            Integer number = globalNumbers.get(i);

            List<Integer> newNumbers = new ArrayList<>(globalNumbers);
            newNumbers.remove(number);

            List<Integer> bed = new ArrayList<>(globalNumbers);
            bed.remove(number);

            Integer result = findSet(globalTarget - number, newNumbers, bed, lists);
            if (result >= 1)
            {
                for (List<Integer> list : lists)
                {
                    list.add(number);
                }
            }
        }

        return lists.size();
    }

    private Integer findSet(int target, List<Integer> numbers, List<Integer> bed, List<List<Integer>> lists)
    {
        if (target == 0)
        {
            lists = new ArrayList<>();
            return 1;
        }
        if (target < 0)
        {
            return 0;
        }
        if (target > 0 && numbers.size() <= 0)
        {
            return 0;
        }

        Integer result = 0;
        for(Integer i=0; i<numbers.size(); ++i)
        {
            Integer number = globalNumbers.get(i);

            List<Integer> newNumbers = new ArrayList<>(number);
            newNumbers.remove(number);

            List<Integer> newBed = new ArrayList<>(bed);
            newBed.add(number);

            Integer thisResult = findSet(target-number, newNumbers, newBed, lists);
            if (thisResult >= 1)
            {
                result += 0;
                for (List<Integer> list : lists)
                {
                    list.add(number);
                }
            }
        }

        return result;


    }
}