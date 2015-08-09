sub array with 0 sum

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {-3, 2, 3, 1, 6};
        Map<Integer, Integer> sumIndex = new HashMap<>();
        int sum = 0;
        for (int i=0; i<ar.length; ++i)
        {
            sum += ar[i];
            if (sumIndex.keySet().contains(sum))
            {
                System.out.print((sumIndex.get(sum) + 1) + " to " + i);
            }
            sumIndex.put(sum, i);
        }
    }
}