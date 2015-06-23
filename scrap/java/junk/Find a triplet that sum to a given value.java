// Find a triplet that sum to a given value

import java.util.Arrays;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {12, 3, 4, 1, 6, 9};
        final Integer sum = 24;

        Arrays.sort(array);

        for (Integer endIndex = array.length-1; endIndex >= 2; --endIndex)
        {
            Integer target = sum - array[endIndex];
            for (Integer fwd = 0, rev = endIndex-1; fwd < rev;)
            {
                if (array[fwd] + array[rev] == target)
                {
                    System.out.print("triplet " + array[endIndex] + " " + array[fwd] + " " + array[rev]);
                    break;
                }
                else if (array[fwd] + array[rev] < target)
                {
                    ++fwd;
                } else
                {
                    --rev;
                }
            }
        }
    }
}