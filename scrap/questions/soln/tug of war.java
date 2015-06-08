// divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets is same
// code question: 60

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] a = {3, 4, 5, -3, 100, 1, 89, 54, 23, 20};
        TugOfWar tw = new TugOfWar();
        Integer[] res = tw.compute(a);
        for (Integer r=0; r<res.length; ++r)
        {
                System.out.print(res[r] + " ");
        }
    }
}

class TugOfWar
{
    private Integer[] result;
    private Integer limit;
    private final Integer INVALID = Integer.MIN_VALUE;

    public Integer[] compute(Integer[] numbers)
    {
        result = new Integer[numbers.length];
        if (result.length % 2 == 0)
        {
            limit = (result.length/2);
        }
        else
        {
            limit = ((result.length-1)/2);
        }

        List<Integer> bag = new ArrayList<>();
        for (Integer i : numbers)
        {
            bag.add(i);
        }

        Integer thisIndex = 0;
        for (Integer bagCursor=0; bagCursor<bag.size(); ++bagCursor)
        {
            result[thisIndex] = bag.get(bagCursor);
            List<Integer> newBag = new ArrayList<>(bag);
            newBag.remove((int) bagCursor);

            if (contemplate(thisIndex+1, newBag))
            {
                return result;
            }
        }


        return null;
    }

    private boolean contemplate(Integer thisIndex, List<Integer> bag)
    {
        if (thisIndex == limit)
        {
            for (Integer value : bag)
            {
                result[thisIndex] = value;
                ++thisIndex;
            }

            return testResult();
        }

        for (Integer bagCursor=0; bagCursor<bag.size(); ++bagCursor)
        {
            result[thisIndex] = bag.get(bagCursor);
            List<Integer> newBag = new ArrayList<>(bag);
            newBag.remove((int) bagCursor);

            if (contemplate(thisIndex+1, newBag))
            {
                return true;
            }
        }

        return false;
    }

    private boolean testResult()
    {
        Integer firstSum = 0;
        for (Integer i=0; i<limit; ++i)
        {
//            System.out.print(result[i] + " ");
            firstSum += result[i];
        }

        Integer secondSum = 0;
//        System.out.println();
        for (Integer i=limit; i<result.length; ++i)
        {
//            System.out.print(result[i] + " ");
            secondSum += result[i];
        }

//        System.out.println();
//        System.out.println();

        return (firstSum.intValue() == secondSum.intValue());

    }
}