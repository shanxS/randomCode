// r2, q2, set 5, amazon
// Write all possible permutations of a array of size z.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static List<Integer[]> arrays = new ArrayList<>();

    public static void main(String[] args)
    {
        Integer[] array = {1,2,3,4};
        List<Integer> partialArray = new ArrayList<>();
        List<Integer> bag = new ArrayList<>(Arrays.asList(array));
        Integer newArrayLength = 3;
        permute(bag, partialArray, newArrayLength);

        Integer outputCount = 1;
        for (Integer i=0; i<newArrayLength; ++i)
        {
            outputCount *= (array.length - i);
        }
        assert arrays.size() == outputCount;

        for (Integer[] a : arrays)
                {
                    for (Integer value : a)
                    {
                        System.out.print(value + " ");
            }

            System.out.println();
        }

    }

    private static void permute(List<Integer> bag, List<Integer> partialArray, Integer size)
    {
        if (partialArray.size() == size-1)
        {
            for (Integer value : bag)
            {
                List<Integer> newPartialArray = new ArrayList<>(partialArray);
                newPartialArray.add(value);
                arrays.add(newPartialArray.toArray(new Integer[newPartialArray.size()]));
            }
            return;
        }

        for (Integer value : bag)
        {
            List<Integer> newPartialArray = new ArrayList<>(partialArray);
            newPartialArray.add(value);

            List<Integer> newBag = new ArrayList<>(bag);
            newBag.remove(value);

            permute(newBag, newPartialArray, size);
        }

    }

}