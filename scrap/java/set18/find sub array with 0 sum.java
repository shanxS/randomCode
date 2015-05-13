// find sub array with 0 sum
// r2, q4, set18

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{5,-1,2,-3,1,4};
        Map<Integer, Integer> sumIndex = new HashMap<>();
        Map<Integer, Integer> indexSum = new HashMap<>();

        sumIndex.put(array[0], 0);
        indexSum.put(0, array[0]);
        for (Integer cursor=1; cursor<array.length; ++cursor)
        {
            Integer previousSum = indexSum.get(cursor-1);
            Integer sum = previousSum+array[cursor];

            if (sumIndex.containsKey(sum))
            {
                System.out.print("foudn at " + sumIndex.get(sum) + " to " + cursor);
            }

            sumIndex.put(sum, cursor);
            indexSum.put(cursor, sum);
        }
    }
}