// divide array into 2 halves so that the sum is same

import java.util.ArrayList;
import java.util.List;

public class Main
{
    private static Integer[] values = {3, 1, 1, 2, 2, 1};//{3, 1, 5, 9, 12};//{1, 5, 11, 5};

    public static void main(String[] er)
    {
        Integer sum = 0;
        for (Integer i=0; i<values.length; ++i)
        {
            sum += values[i];
        }

        if (sum % 2 != 0)
        {
            System.out.print("cnat divide");
            return;
        }

        List<Integer> half = findHalf(0, sum/2);
        if (half != null)
        {
            half.stream().forEach(x -> System.out.print(x + " "));
        }
        else
        {
            System.out.print("cant find");
        }
    }

    private static List<Integer> findHalf(Integer startIndex, Integer sum)
    {
        if (sum == 0)
        {
            return new ArrayList<>();
        }

        if (startIndex == values.length)
        {
            return null;
        }

        List<Integer> inclusion = findHalf(startIndex+1, sum-values[startIndex]);
        if (inclusion != null)
        {
            inclusion.add(values[startIndex]);
            return inclusion;
        }
        else
        {
            return findHalf(startIndex+1, sum);
        }
    }
}