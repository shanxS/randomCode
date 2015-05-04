// r3, q3, set 6, amazon
// Given an array, find three numbers a, b and c such that a^2 + b^2 = c^2
// WRONG: does not work well with duplicates
// this method does work with duplicates
// see set10, r2, q3


import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        Integer[] array = new Integer[] {4,3,5,1,2};
        List<Integer> list = new ArrayList<>();

        Arrays.stream(array).forEach(x -> list.add((int) Math.pow(x, 2)));
        Collections.sort(list);
        list.stream().forEach(x -> System.out.println(x));

        findTriplets_iteration(list);
        findTriplets_map(list);
    }

    private static void findTriplets_map(List<Integer> list) {
        System.out.println("using map");
        Set<Integer> set = new HashSet<>();

        list.forEach(x -> set.add(x));


        for (Integer i=list.size()-1; i>1; --i)
        {
            set.remove(list.get(i));
            for (Integer j=0; j<i; ++j)
            {
                if (set.contains(list.get(i) - list.get(j)))
                {
                    if ((list.get(i) - list.get(j)) > list.get(j))
                    {
                        System.out.println(Math.pow(list.get(j), 0.5) + " "
                                + Math.pow(list.get(i), 0.5) + " "
                                + Math.pow(list.get(i) - list.get(j), 0.5));
                    }
                }
            }
        }
    }


    private static void findTriplets_iteration(List<Integer> list) {
        System.out.println("using iteration");
        for (Integer i=list.size()-1; i>1; --i)
        {
            Integer target = list.get(i);
            Integer starIndex = 0;
            Integer endIndex = i-1;
            while(starIndex < endIndex)
            {
                Integer sum = list.get(starIndex) + list.get(endIndex);
                if (sum == target)
                {
                    System.out.println("found: " + Math.pow(list.get(starIndex), 0.5)
                                        + " " + Math.pow(list.get(endIndex), 0.5)
                                        + " " + Math.pow(target, 0.5));

                    --endIndex;
                }
                else if (sum > target)
                {
                    --endIndex;
                }
                else
                {
                    ++starIndex;
                }
            }
        }
    }
}
