sort according to given pattern

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {2, 1, 8, 3};
        CustomComparator cc = new CustomComparator(ar);

        Integer[] ar2 = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8};
        Arrays.sort(ar2, cc);
        for (int i : ar2)
        {
            System.out.print(i + " ");
        }
    }
}

class CustomComparator implements Comparator<Integer>
{
    private Map<Integer, Integer> valueIndex;
    public CustomComparator(Integer[] ar)
    {
        valueIndex = new HashMap<>();
        for(int i=0; i<ar.length; ++i)
        {
            valueIndex.put(ar[i], i);
        }
    }

    @Override
    public int compare(Integer o1, Integer o2)
    {
        Integer num1 = (Integer) o1;
        Integer num2 = (Integer) o2;

        if (valueIndex.keySet().contains(num1) && valueIndex.keySet().contains(num2))
        {
            if (valueIndex.get(num1) > valueIndex.get(num2))
            {
                return 1;
            }
            else if (valueIndex.get(num1) < valueIndex.get(num2))
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            if (num1 > num2)
            {
                return 1;
            }
            else if (num1 < num2)
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }
}