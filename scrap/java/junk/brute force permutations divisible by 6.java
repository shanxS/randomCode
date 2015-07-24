// brute force permutations divisible by 6

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {2,4,6,8};
//        Integer[] ar = {3,6};

        List<Integer> perm = getAllPerm(ar);
        Integer count = 0;
        for (Integer i : perm)
        {
            if (i%3 == 0 && i%2==0)
            {
                ++count;
                System.out.println(i + " ");
            }
        }

        System.out.print("--- " + count);
    }

    private static List<Integer> getAllPerm(Integer[] ar)
    {
        List<Integer> list = new ArrayList<>();

        List<Integer> arList = new ArrayList<>();
        for(Integer i : ar)
        {
            arList.add(i);
        }

        for (Integer i=1; i<=ar.length; ++ i)
        {
            list.addAll(permuteFor(arList, i));
        }

        return list;
    }

    private static List<Integer> permuteFor(List<Integer> arList, Integer n)
    {
        if(n == 1)
        {
            return arList;
        }

        List<Integer> thisResults = new ArrayList<>();
        for (Integer i=0; i<arList.size(); ++i)
        {
            List<Integer> bag = new ArrayList<>(arList);
            Integer thisValue = bag.remove((int) i) * ((int)Math.pow(10, n-1));

            List<Integer> thisPermuted = permuteFor(bag, n-1);
            for (Integer value : thisPermuted)
            {
                thisResults.add(thisValue + value);
            }
        }

        return thisResults;
    }
}